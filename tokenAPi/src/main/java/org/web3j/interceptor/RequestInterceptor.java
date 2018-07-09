package org.web3j.interceptor;

import com.alibaba.fastjson.JSON;
import common.response.Result;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;


/**
 * Created by luke on 17/3/14.
 */
@Component
public class RequestInterceptor implements HandlerInterceptor
{
    /**
     * logger
     */
    private final Logger logger = LogManager.getLogger(getClass());


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception
    {
        String key = request.getParameter("key");
        if (key == null || !key.equals("VRHOSLVjB65cmIB5Xusp"))
        {

            write(response, "key 非法！");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception
    {
//        logger.warn("Request Body:{}", getRequestBody(request));
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception
    {

    }

    private void write(HttpServletResponse response, String content) {
        response.setContentType("application/json;charset=UTF-8");
        Writer writer = null;
        try {
            writer = response.getWriter();
            writer.write(JSON.toJSONString(Result.error(content)));
            writer.flush();
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                writer.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }
}
