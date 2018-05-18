package org.web3j.service;

import common.response.Response;

/**
 * Created by tangjc on 2018/5/17.
 */
public interface IntegralSerivce
{
    /**
     * @param passwd
     * @return
     */
    Response newAccount(String passwd);

    Response exchange(String account, String bigNum);

    Response recycle(String account, String bigNum);

    Response getBalance(String account);

}
