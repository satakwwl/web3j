package org.web3j.service;

import common.response.Response;

/**
 * Created by tangjc on 2018/5/17.
 */
public interface TokenSerivce
{
    /**
     * @param passwd
     * @return
     */
    Response newAccount(String passwd);

    Response transfer(String account, String bigNum);

    Response getBalance(String account);

    Response unlockCount(String account, String passwd);

}
