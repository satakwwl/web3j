package org.web3j.controller;

import common.response.Response;
import common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.web3j.service.IntegralSerivce;

import java.util.Map;

/**
 * Created by tangjc on 2018/5/17.
 */
@Controller
@RequestMapping("/integral")
public class IntegralController
{
    @Autowired
    private IntegralSerivce integralSerivce;

    @RequestMapping(value = "exchange",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response exchange(@RequestBody Map map)
    {
        if (!map.containsKey("account"))
        {
            return Result.fail("account 不能为空");
        }
        String account = map.get("account").toString();
        if (!map.containsKey("value"))
        {
            return Result.fail("value不能为空");
        }
        String value = map.get("value").toString();
        return integralSerivce.exchange(account, value);
    }

    @RequestMapping(value = "balance",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response balance(@RequestBody Map map)
    {
        if (!map.containsKey("account"))
        {
            return Result.fail("account 不能为空");
        }
        String account = map.get("account").toString();
        return integralSerivce.getBalance(account);
    }

    @RequestMapping(value = "newAccount",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response newAccount(@RequestBody Map map)
    {
        if (!map.containsKey("passwd"))
        {
            return Result.fail("passwd不能为空");
        }
        String passwd = map.get("passwd").toString();
        return integralSerivce.newAccount(passwd);
    }

    @RequestMapping(value = "recylce",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response recylce(@RequestBody Map map)
    {
        if (!map.containsKey("account"))
        {
            return Result.fail("account 不能为空");
        }
        String account = map.get("account").toString();
        if (!map.containsKey("value"))
        {
            return Result.fail("value不能为空");
        }
        String value = map.get("value").toString();
        return integralSerivce.recycle(account, value);

    }

    @RequestMapping(value = "unlock",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response unlock(@RequestBody Map map)
    {
        if (!map.containsKey("account"))
        {
            return Result.fail("account 不能为空");
        }
        String account = map.get("account").toString();
        if (!map.containsKey("passwd"))
        {
            return Result.fail("passwd 不能为空");
        }
        String passwd = map.get("passwd").toString();
        return integralSerivce.unlockCount(account, passwd);

    }

}
