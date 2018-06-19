package org.web3j.service;

import common.response.Response;

/**
 * Created by tangjc on 2018/5/17.
 */
public interface KvSerivce
{



    Response getValue(String user,String key);

    Response setValue(String user , String key,String value);

}
