package org.web3j.service;

import common.response.Response;
import common.response.Result;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.wraper.TokenERC20;

import java.math.BigInteger;

/**
 * Created by tangjc on 2018/5/17.
 */
@Service
@Configuration
@PropertySource("classpath:config.properties")
public class IntegralServiceImp implements IntegralSerivce
{
    private final Logger logger = LogManager.getLogger(getClass());
    static final BigInteger GAS = BigInteger.valueOf(30_000);
    static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    @Value("${web3j.privateKey}")
    private String privateKey;
    @Value("${web3j.gethUrl}")
    private String url;
    @Value("${web3j.contractTokenAddress}")
    private String contractAddress;

    private Web3j web3 = null;
    private Geth geth = null;
    private TokenERC20 tokenERC20 = null;

    private void initGeth() throws Exception
    {
        if (web3 == null)
            web3 = Web3j.build(new HttpService(url));  // defaults to http://localhost:8545/
        if (geth == null)
            geth = Geth.build(new HttpService(url));
        if (tokenERC20 == null)
            tokenERC20 = TokenERC20.load(contractAddress, web3, Credentials.create(privateKey), GAS_PRICE, Contract.GAS_LIMIT);
    }

    @Override
    public Response newAccount(String passwd)
    {
        try
        {
            initGeth();
            org.web3j.protocol.core.Response<String> response = geth.personalNewAccount(passwd).send();
            String account = response.getResult();
//            passwd = url + "," + privateKey + "," + contractAddress;
            return Result.resultSet(account);
        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }

    @Override
    public Response exchange(String account, String bigNum)
    {
        try
        {
            initGeth();
            TransactionReceipt response = tokenERC20.transfer(account, new BigInteger(bigNum)).send();
            return Result.success();
        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }

    @Override
    public Response recycle(String account, String bigNum)
    {
        try
        {
            initGeth();
            TransactionReceipt receipt = tokenERC20.recycling(account, new BigInteger(bigNum)).send();
            return Result.success();
        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }

    @Override
    public Response getBalance(String account)
    {
        try
        {
            initGeth();
            BigInteger balance = tokenERC20.balanceOf(account).send();
            return Result.resultSet(balance);
        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }

    @Override
    public Response unlockCount(String account, String passwd)
    {
        try
        {
            initGeth();
            org.web3j.protocol.core.Response<Boolean> response = geth.personalUnlockAccount(account, passwd).send();
            if (response.getError() != null && response.getError().getCode() != 0)
                return Result.fail("密码错误");
            return Result.success();

        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }
}
