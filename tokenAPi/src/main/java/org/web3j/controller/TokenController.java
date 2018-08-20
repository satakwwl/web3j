package org.web3j.controller;

import common.response.Response;
import common.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.web3j.service.TokenSerivce;

import java.util.Map;

/**
 * Created by tangjc on 2018/5/17.
 */
@Controller
@RequestMapping("/token")
public class TokenController
{
    @Autowired
    private TokenSerivce tokenSerivce;

    @RequestMapping(value = "getKey",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response getKey(@RequestBody Map map)
    {
        if (!map.containsKey("appKey"))
        {
            return Result.fail("appKey 不能为空");
        }
        String appKey = map.get("appKey").toString();
        if (!map.containsKey("appSecret"))
        {
            return Result.fail("appSecret不能为空");
        }
        String appSecret = map.get("appSecret").toString();
        return tokenSerivce.getKey(appKey, appSecret);
    }

    @RequestMapping(value = "transfer",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response transfer(@RequestBody Map map)
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
        return tokenSerivce.transfer(account, value);
    }

    @RequestMapping(value = "balance",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
        //Response balance(@RequestBody Map map,HttpServletRequest request)
    Response balance(@RequestBody Map map)
    {
//        String token = request.getParameter("token");
        if (!map.containsKey("account"))
        {
            return Result.fail("account 不能为空");
        }
        String account = map.get("account").toString();
        return tokenSerivce.getBalance(account);
    }

    @RequestMapping(value = "newAccount",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response newAccount(@RequestBody Map map)
    {
        //密码硬编码，方便以后批量导出私钥
//        if (!map.containsKey("passwd"))
//        {
//            return Result.fail("passwd不能为空");
//        }
//        String passwd = map.get("passwd").toString();
        return tokenSerivce.newAccount("s");
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
        return tokenSerivce.unlockCount(account, passwd);

    }

}
