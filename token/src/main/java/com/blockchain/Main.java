package com.blockchain;

import com.blockchain.response.Response;

/**
 * Created by tangjc on 2018/6/6.
 */
public class Main
{
    public static void main(String[] agrs)
    {

 /*       //private chain

        Token token = new Token("http://192.168.1.189:8545", "948cb9bbd34371939675f4f212ade4722039e4d8d21b5d8d6c01cec13b393183", "0x372b97c17c176fed2a1f295372d9253b755885a7");
//        String fromAccount = "0x5ffb51ed47e3e763bc70918ecdad52ce0b742ea5";
//        String toAccount = "0x0581a3f433705d9b4e0577996995461159bed8ba";
//        Token token = new Token("http://192.168.1.189:8545", "s", "F:\\src\\UTC--2018-04-08T01-17-57.020931661Z--fd82fb0496801ea202382d4a314f9be25becb01f", "0xe62e5261ec41258dbce53c954ea01f540208eb5f");
        String fromAccount = "0xeda43311090bc252ea7fcae15aac3190c96e0751";
        String toAccount = "0x87ad1be8601077e6c10ced61cad14de9dd26f7a7";
        Response newAccountRs = token.newAccount("s");
        if (newAccountRs.getCode() == 0)
            System.out.println("new account address: " + newAccountRs.getResult());
        else
            System.out.println("create new account error:" + newAccountRs.getMsg());
        Response balanceRs = token.getBalance(fromAccount);
        if (balanceRs.getCode() == 0)
            System.out.println("balance of  " + fromAccount + "is " + balanceRs.getResult());
        else
            System.out.println("get balance error:" + balanceRs.getMsg());
        Response transferRs = token.transfer(toAccount, "309999999909999");
        if (transferRs.getCode() == 0)
            System.out.println("transfer success");
        else
            System.out.println("transfer error:" + transferRs.getMsg());
        balanceRs = token.getBalance(toAccount);
        if (balanceRs.getCode() == 0)
            System.out.println("balance of " + toAccount + " is " + balanceRs.getResult());
        else
            System.out.println("get balance error:" + balanceRs.getMsg());
        Response recylceRes= token.recylce(toAccount, "309999999909999");
        if (recylceRes.getCode() == 0)
            System.out.println("recylce success");
        else
            System.out.println("recyle error:" + recylceRes.getMsg());
        balanceRs = token.getBalance(toAccount);
        if (balanceRs.getCode() == 0)
            System.out.println("balance of " + toAccount + " is " + balanceRs.getResult());
        else
            System.out.println("get balance error:" + balanceRs.getMsg());
*/

        //public chain

        String url = "https://ropsten.infura.io/VRHOSLVjB65cmIB5Xusp";
        String mainNetUrl = "https://mainnet.infura.io/VRHOSLVjB65cmIB5Xusp";
//        Token token = new Token(url);
        Token coins = new Token(url, "4c0e4c4b6f2ae651eee1702808390407f9340ff3212ab8ee98880c43c5a398c5", "0x4175aab11893842491c1e4f4d349e3857483cf35");
        String fromAccount = "0x69dF86c3F3cca336d4b73a4E85Ff9f69B6d2E385";
        String toAccount = "0xeda43311090bc252ea7fcae15aac3190c96e0751";
//        Response balanceRs = coins.getBalance(fromAccount);
//        if (balanceRs.getCode() == 0)
//            System.out.println("balance of  " + fromAccount + "is " + balanceRs.getResult());
//        else
//            System.out.println("get balance error:" + balanceRs.getMsg());
//        Response transferRs = coins.transfer(toAccount, "1000000000000000000");
//        if (transferRs.getCode() == 0)
//            System.out.println("transfer success");
//        else
//            System.out.println("transfer error:" + transferRs.getMsg());
//        balanceRs = coins.getBalance(toAccount);
//        if (balanceRs.getCode() == 0)
//            System.out.println("balance of " + toAccount + " is " + balanceRs.getResult());
//        else
//            System.out.println("get balance error:" + balanceRs.getMsg());
//        Response newAccountRs = token.newAccount("s");
//        if (newAccountRs.getCode() == 0)
//            System.out.println("new account address: " + newAccountRs.getResult());
//        else
//            System.out.println("create new account error:" + newAccountRs.getMsg());
        Response recylceRes= coins.recylce(toAccount, "309999999909999");
        if (recylceRes.getCode() == 0)
            System.out.println("recylce success");
        else
            System.out.println("recyle error:" + recylceRes.getMsg());

    }

}
