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
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;
import org.web3j.wraper.User;

import java.math.BigInteger;

/**
 * Created by tangjc on 2018/5/17.
 */
@Service
@Configuration
@PropertySource("classpath:config.properties")
public class KvServiceImp implements KvSerivce
{
    private final Logger logger = LogManager.getLogger(getClass());
    static final BigInteger GAS = BigInteger.valueOf(30_000);
    static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    @Value("${web3j.privateKey}")
    private String privateKey;
    @Value("${web3j.gethUrl}")
    private String url;
    @Value("${web3j.contractUserAddress}")
    private String contractAddress;

    private Web3j web3 = null;
    private User userContract = null;

    private void initGeth() throws Exception
    {
        if (web3 == null)
            web3 = Web3j.build(new HttpService(url));  // defaults to http://localhost:8545/
        if (userContract == null)
            userContract = User.load(contractAddress, web3, Credentials.create(privateKey), GAS_PRICE, Contract.GAS_LIMIT);
    }


    @Override
    public Response getValue(String user, String key)
    {
        try
        {
            initGeth();
            String value = userContract.get(user,key).send();
            return Result.resultSet(value);
        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }

    @Override
    public Response setValue(String user, String key, String value)
    {
        try
        {
            initGeth();
            userContract.set(user,key,value).send();
            return Result.success();
        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }
}
