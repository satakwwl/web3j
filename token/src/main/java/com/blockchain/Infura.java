package com.blockchain;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.methods.response.NewAccountIdentifier;
import org.web3j.protocol.core.methods.response.Web3ClientVersion;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.parity.Parity;

import java.io.IOException;

/**
 * Created by tangjc on 2018/7/2.
 */
public class Infura
{
    public static void main(String[] agrs)
    {
        String testUrl = "https://ropsten.infura.io/VRHOSLVjB65cmIB5Xusp";
        String mainUrl = "https://mainnet.infura.io/VRHOSLVjB65cmIB5Xusp";
        HttpService httpService = new HttpService(testUrl);
        Web3j web3 = Web3j.build(httpService);
        Web3ClientVersion web3ClientVersion;
        {
            try
            {
                web3ClientVersion = web3.web3ClientVersion().send();
                System.out.println(web3ClientVersion.getWeb3ClientVersion());
                Parity parity = Parity.build(httpService);
                NewAccountIdentifier newAccountIdentifier = parity.personalNewAccount("pasd").send();
                System.out.println("new account is :" + newAccountIdentifier.getResult());

            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }


}
