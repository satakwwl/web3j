package com.blockchain;

import com.blockchain.response.Response;

/**
 * Created by tangjc on 2018/6/6.
 */
public class Main
{
    public static void main(String[] agrs)
    {
        Token token = new Token("http://192.168.1.189:8545", "cf2417ffa29977b35aef55558d823b26e2356f9b53abbe47b04a710c522ca0d5", "0xf00a7a865cfe686a9a330d29bb80e8e524e01fb9");
        String fromAccount = "0xfd82fb0496801ea202382d4a314f9be25becb01f";
        String toAccount = "0x4b1aa2f4c30d9748f759907f8bf21ffc6f071725";
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
    }

}
