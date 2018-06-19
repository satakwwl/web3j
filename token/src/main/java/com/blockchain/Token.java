package com.blockchain;


import com.blockchain.response.Response;
import com.blockchain.response.Result;
import com.blockchain.wraper.TokenERC20;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.math.BigInteger;

/**
 * Created by tangjc on 2018/6/6.
 */
public class Token
{
    static final BigInteger GAS = BigInteger.valueOf(30_000);
    static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    private String privateKey;
    private String url;
    private String contractAddress;

    private Web3j web3 = null;
    private Geth geth = null;
    private TokenERC20 tokenERC20 = null;

    public Token(String url, String privateKey, String contractAddress)
    {
        if (web3 == null)
            web3 = Web3j.build(new HttpService(url));  // defaults to http://localhost:8545/
        if (geth == null)
            geth = Geth.build(new HttpService(url));
        if (tokenERC20 == null)
            tokenERC20 = TokenERC20.load(contractAddress, web3, Credentials.create(privateKey), GAS_PRICE, Contract.GAS_LIMIT);
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
            TransactionReceipt response = tokenERC20.transfer(account, new BigInteger(bigNum)).send();
            return Result.success();
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
}
