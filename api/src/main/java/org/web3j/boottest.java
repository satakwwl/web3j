package org.web3j;

/**
 * Created by tangjc on 2018/4/27.
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;

import java.io.IOException;

@Controller
@EnableAutoConfiguration

public class boottest {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        Web3j web3 = Web3j.build(new HttpService("http://192.168.1.189:8545/"));  // defaults to http://localhost:8545/
    Web3ClientVersion web3ClientVersion = null;
        try
    {
        web3ClientVersion = web3.web3ClientVersion().send();
    } catch (IOException e)
    {
        e.printStackTrace();
    }
    String clientVersion = web3ClientVersion.getWeb3ClientVersion();
        return clientVersion;
}

    public static void main(String[] args) throws Exception {
        SpringApplication.run(boottest.class, args);
    }
}