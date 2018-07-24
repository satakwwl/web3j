package org.web3j.tx;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.RawTransaction;
import org.web3j.crypto.TransactionEncoder;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.response.EthGetTransactionCount;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.tx.response.TransactionReceiptProcessor;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.SQLException;

/**
 * TransactionManager implementation using Ethereum wallet file to create and sign transactions
 * locally.
 * <p>
 * <p>This transaction manager provides support for specifying the chain id for transactions as per
 * <a href="https://github.com/ethereum/EIPs/issues/155">EIP155</a>.
 */
public class RawTransactionManager extends TransactionManager
{

    private final Web3j web3j;
    final Credentials credentials;

    private final byte chainId;

    public RawTransactionManager(Web3j web3j, Credentials credentials, byte chainId)
    {
        super(web3j, credentials.getAddress());

        this.web3j = web3j;
        this.credentials = credentials;

        this.chainId = chainId;
    }

    public RawTransactionManager(
            Web3j web3j, Credentials credentials, byte chainId,
            TransactionReceiptProcessor transactionReceiptProcessor)
    {
        super(transactionReceiptProcessor, credentials.getAddress());

        this.web3j = web3j;
        this.credentials = credentials;

        this.chainId = chainId;
    }

    public RawTransactionManager(
            Web3j web3j, Credentials credentials, byte chainId, int attempts, long sleepDuration)
    {
        super(web3j, attempts, sleepDuration, credentials.getAddress());

        this.web3j = web3j;
        this.credentials = credentials;

        this.chainId = chainId;
    }

    public RawTransactionManager(Web3j web3j, Credentials credentials)
    {
        this(web3j, credentials, ChainId.NONE);
    }

    public RawTransactionManager(
            Web3j web3j, Credentials credentials, int attempts, int sleepDuration)
    {
        this(web3j, credentials, ChainId.NONE, attempts, sleepDuration);
    }

    protected synchronized BigInteger getNonce() throws IOException
    {
        BigInteger bigNonce;
        NonceHandle nonceHandle = new NonceHandle();
        EthGetTransactionCount ethGetTransactionCount = web3j.ethGetTransactionCount(
                credentials.getAddress(), DefaultBlockParameterName.PENDING).send();
        bigNonce = ethGetTransactionCount.getTransactionCount();
        int intNonce = bigNonce.intValue();
        try
        {
            nonceHandle.conncet();
            int nonce = nonceHandle.getNonce(credentials.getAddress());
            if (nonce >= intNonce)
            {
                nonceHandle.saveNonce(credentials.getAddress(), nonce + 1);
                bigNonce = BigInteger.valueOf(nonce + 1);
            } else
            {
                nonceHandle.saveNonce(credentials.getAddress(), intNonce);
            }
            nonceHandle.close();
        } catch (ClassNotFoundException e)
        {
        } catch (SQLException s)
        {
        }

        return bigNonce;
    }

    @Override
    public EthSendTransaction sendTransaction(
            BigInteger gasPrice, BigInteger gasLimit, String to,
            String data, BigInteger value) throws IOException
    {

        BigInteger nonce = getNonce();

        RawTransaction rawTransaction = RawTransaction.createTransaction(
                nonce,
                gasPrice,
                gasLimit,
                to,
                value,
                data);

        return signAndSend(rawTransaction);
    }

    public EthSendTransaction signAndSend(RawTransaction rawTransaction)
            throws IOException
    {

        byte[] signedMessage;

        if (chainId > ChainId.NONE)
        {
            signedMessage = TransactionEncoder.signMessage(rawTransaction, chainId, credentials);
        } else
        {
            signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials);
        }

        String hexValue = Numeric.toHexString(signedMessage);

        return web3j.ethSendRawTransaction(hexValue).send();
    }
}
