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
import org.web3j.service.KvSerivce;

import java.util.Map;

/**
 * Created by tangjc on 2018/5/17.
 */
@Controller
@RequestMapping("/user")
public class UserController
{
    @Autowired
    private KvSerivce kvSerivce;

    @RequestMapping(value = "getValue",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response getValue(@RequestBody Map map)
    {
        if (!map.containsKey("userId"))
        {
            return Result.fail("userId不能为空");
        }
        String userId = map.get("userId").toString();
        if (!map.containsKey("key"))
        {
            return Result.fail("key不能为空");
        }
        String key = map.get("key").toString();
        return kvSerivce.getValue(userId, key);
    }

    @RequestMapping(value = "setValue",
            method = {RequestMethod.POST},
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    Response setValue(@RequestBody Map map)
    {
        if (!map.containsKey("userId"))
        {
            return Result.fail("userId不能为空");
        }
        String userId = map.get("userId").toString();
        if (!map.containsKey("key"))
        {
            return Result.fail("key不能为空");
        }
        String key = map.get("key").toString();
        if (!map.containsKey("value"))
        {
            return Result.fail("value不能为空");
        }
        String value = map.get("value").toString();
        return kvSerivce.setValue(userId, key, value);
    }


}
