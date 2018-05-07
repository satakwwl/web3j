package org.web3j;

/**
 * Created by tangjc on 2018/4/27.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.Contract;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

@Controller("/")
@EnableAutoConfiguration

public class boottest
{
    static final BigInteger GAS = BigInteger.valueOf(30_000);
    static final BigInteger GAS_PRICE = BigInteger.valueOf(20_000_000_000L);
    Web3j web3 = Web3j.build(new HttpService("http://192.168.1.189:8545/"));  // defaults to http://localhost:8545/
    Geth geth = Geth.build(new HttpService("http://192.168.1.189:8545/"));

    String baseAccount = "0xeb8847f387b16b5b528b9935f3bcd29b9e96baf4";
    String toAccount = "0xd6084aeba0fd47f7e0bcc687acfe6e835faeae70";
    String privateKey = "505a5d82843a834d3015737a3f20690755ab5a03943e2c4ffe81726acb9e007e";
    String contractAddress = "0x23c245045358689e6231371f834c938306fd289e";
    TokenERC20 tokenERC20 = TokenERC20.load(contractAddress, web3, Credentials.create(privateKey), GAS_PRICE, Contract.GAS_LIMIT);

    @RequestMapping(value = "transfer",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Object home()
    {

        BigInteger balanceTo = BigInteger.valueOf(0);
        BigInteger balanceFrom = BigInteger.valueOf(0);
        String account3 = "0x884e6485c557ab241632c451f63794839ca0c9bd";
        Map<String, Object> map = new HashMap<>();
        geth.personalUnlockAccount(baseAccount, "s");
        try
        {
            balanceTo = tokenERC20.balanceOf(toAccount).send();
            balanceFrom = tokenERC20.balanceOf(baseAccount).send();
            map.put("balanceFromBeforeTransfer", balanceFrom);
            map.put("balanceToBeforeTransfer", balanceTo);

            tokenERC20.transfer(toAccount,new BigInteger("8888888888888888888")).send();
            balanceTo = tokenERC20.balanceOf(toAccount).send();
            balanceFrom = tokenERC20.balanceOf(baseAccount).send();
            map.put("balanceFromAfterTransfer", balanceFrom);
            map.put("balanceToAfterTransfer", balanceTo);

//            geth.personalUnlockAccount(toAccount, "s");
            tokenERC20.recycling(toAccount,  new BigInteger("6666666666666666666")).send();
            balanceTo = tokenERC20.balanceOf(toAccount).send();
            balanceFrom = tokenERC20.balanceOf(baseAccount).send();
            map.put("balanceFromAfterRecycling", balanceFrom);
            map.put("balanceToAfterRecycling", balanceTo);


        } catch (Exception e)
        {
            e.printStackTrace();
        }


        return map;
    }

    @RequestMapping(value = "home",
            method = {RequestMethod.GET, RequestMethod.POST},
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Object homepage()
    {
        Map map = new HashMap();
        map.put("hell", "jesus");
        return map;
    }

    public static void main(String[] args) throws Exception
    {
        SpringApplication.run(boottest.class, args);
    }
}