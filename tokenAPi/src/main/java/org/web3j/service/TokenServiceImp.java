package org.web3j.service;

import common.response.Response;
import common.response.Result;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
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
import org.web3j.tx.NonceHandle;
import org.web3j.wraper.MyAdvancedToken;

import java.io.IOException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by tangjc on 2018/5/17.
 */
@Service("tokenSerivce")
@Configuration
@PropertySource("classpath:application.properties")
public class TokenServiceImp implements TokenSerivce
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
    private Geth geth = Geth.build(new HttpService());
    private MyAdvancedToken tokenERC20 = null;

    private void initGeth() throws Exception
    {
        if (web3 == null)
            web3 = Web3j.build(new HttpService(url));  // defaults to http://localhost:8545/
        if (tokenERC20 == null)
            tokenERC20 = MyAdvancedToken.load(contractAddress, web3, Credentials.create(privateKey), GAS_PRICE, Contract.GAS_LIMIT);
    }

    @Override
    public Response newAccount(String passwd)
    {
        try
        {
            initGeth();
            org.web3j.protocol.core.Response<String> response = geth.personalNewAccount(passwd).send();
            String account = response.getResult();
            return Result.resultSet(account);
        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }

    @Override
    public Response getKey(String appKey, String appSecret)
    {
        try
        {
            String sha1Str = DigestUtils.sha1Hex("I'm " + appKey + "king");
            if (!sha1Str.equals(appSecret))
            {
                return Result.fail("无效的appKey或appSecret!");
            }
            NonceHandle nonceHandle = new NonceHandle();
            nonceHandle.conncet();
            if (!nonceHandle.verifyAppKey(appKey, appSecret))
            {
                nonceHandle.close();
                return Result.fail("无效的appKey或appSecret!");
            }
            String key = nonceHandle.genKey(appKey);
            nonceHandle.close();
            return Result.resultSet(key);
        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }

    @Override
    public Response verifyKey(String key)
    {
        try
        {

            NonceHandle nonceHandle = new NonceHandle();
            nonceHandle.conncet();
            boolean isValid = nonceHandle.verifyKey(key);
            nonceHandle.close();
            if (isValid)
                return Result.resultSet(key);
            else
                return Result.fail("无效的key!");
        } catch (Exception e)
        {
            logger.error(e, e);
            return Result.fail(e.toString());
        }
    }

    @Override
    public Response transfer(String account, String bigNum)
    {
        try
        {
            initGeth();
            String id = UUID.randomUUID().toString();
            Threads worker = new Threads(id, account, bigNum);
            Thread thread = new Thread(worker);
            thread.start();
            return Result.resultSet(id);
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

    class Threads implements Runnable
    {
        private String id;
        private String account;
        private String bigNum;

        public Threads(String id, String account, String bigNum)
        {
            this.id = id;
            this.account = account;
            this.bigNum = bigNum;
        }

        @Override
        public void run()
        {
            String transHash = "";
            String msg = "转账成功";
            int code = 0;
            try
            {
                logger.info(id + " start transfer account:" + account + ",value:" + bigNum);
                TransactionReceipt response = tokenERC20.transfer(account, new BigInteger(bigNum)).send();
                transHash = response.getTransactionHash();
                logger.info(id + " end transfer account:" + account + ",value:" + bigNum + "transHash:" + transHash);

            } catch (Exception e)
            {
                logger.error(e, e);
                msg = e.toString();
                msg = URLEncoder.encode(msg);
                code = 1;
            } finally
            {
                String url = String.format("http://rpj520.com/index.php/tyy/health/notify?code=%s&msg=%s&result=%s&transHash=%s", code, msg, id, transHash);
                try
                {
                    sendGet(url);
                } catch (IOException e)
                {
                    logger.error(e, e);
                    e.printStackTrace();
                }

            }
        }
    }


    private String sendGet(String url) throws ParseException, IOException
    {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建HttpGet
        HttpGet httpGet = new HttpGet(url);
        // 创建参数队列
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();

        CloseableHttpResponse response = httpclient.execute(httpGet);
        try
        {
            HttpEntity entity = response.getEntity();
            if (entity != null)
            {
                String result = EntityUtils.toString(entity, "UTF-8");
                logger.info(String.format("url:%s,response:%s", url, result));
                return result;
            }
        } finally
        {
            response.close();
            // 关闭连接,释放资源
            try
            {
                httpclient.close();
            } catch (IOException e)
            {
                logger.error(e.getMessage());
            }
        }
        return null;
    }
}
