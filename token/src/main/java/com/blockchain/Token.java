package com.blockchain;


import com.blockchain.response.Response;
import com.blockchain.response.Result;
import com.blockchain.wraper.MyAdvancedToken;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by tangjc on 2018/6/6.
 */
public class Token
{
    static final BigInteger GAS = BigInteger.valueOf(30_000);
    static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
//    private String privateKey;
//    private String url;
//    private String contractAddress;

    private Web3j web3;
    private MyAdvancedToken tokenERC20;
    private Geth geth = Geth.build(new HttpService());

    public Token(String url, String privateKey, String contractAddress)
    {
        web3 = Web3j.build(new HttpService(url));  // defaults to http://localhost:8545/
        tokenERC20 = MyAdvancedToken.load(contractAddress, web3, Credentials.create(privateKey), GAS_PRICE, Contract.GAS_LIMIT);
    }


    public Token(String url, String passwd, String walletFile, String contractAddress)
    {
        web3 = Web3j.build(new HttpService(url));  // defaults to http://localhost:8545/
        Credentials credentials = getCredentials(passwd, walletFile);
        tokenERC20 = MyAdvancedToken.load(contractAddress, web3, credentials, GAS_PRICE, Contract.GAS_LIMIT);
    }

    public Response newAccount(String passwd)
    {
        try
        {
            org.web3j.protocol.core.Response<String> response = geth.personalNewAccount(passwd).send();
            String account = response.getResult();
            return Result.resultSet(account);

        } catch (Exception e)
        {
            return Result.fail(e.toString());
        }
    }

    public Response transfer(String account, String bigNum)
    {
        try
        {
            Date date = new Date();
            String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            System.out.println(time + " start transfer");
            TransactionReceipt response = tokenERC20.transfer(account, new BigInteger(bigNum)).send();
            date = new Date();
            time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            System.out.println(time + " end transfer");
            return Result.resultSet(response.getTransactionHash());
        } catch (Exception e)
        {
            return Result.fail(e.toString());
        }
    }


    public Response getBalance(String account)
    {
        try
        {
            BigInteger balance = tokenERC20.balanceOf(account).send();
            return Result.resultSet(balance);
        } catch (Exception e)
        {
            return Result.fail(e.toString());
        }
    }

    public Response unlockCount(String account, String passwd)
    {
        try
        {
            org.web3j.protocol.core.Response<Boolean> response = geth.personalUnlockAccount(account, passwd).send();
            if (response.getError() != null && response.getError().getCode() != 0)
                return Result.fail("密码错误");
            return Result.success();

        } catch (Exception e)
        {
            return Result.fail(e.toString());
        }
    }

    private Credentials getCredentials(String passWd, String walletFile)
    {
        try
        {
            Credentials credentials = WalletUtils.loadCredentials(passWd, walletFile);
            return credentials;
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    public Response recylce(String account, String bigNum)
    {
        try
        {
            TransactionReceipt response = tokenERC20.recycle(account, new BigInteger(bigNum)).send();
            return Result.resultSet(response.getTransactionHash());
        } catch (Exception e)
        {
            return Result.fail(e.toString());
        }
    }

}
