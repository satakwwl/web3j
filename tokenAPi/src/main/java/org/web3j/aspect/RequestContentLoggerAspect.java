package org.web3j.aspect;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求日志打印切面类
 * Created by Dayong on 2016/10/12.
 */
@Aspect
public class RequestContentLoggerAspect
{
    /**
     * logger
     */
    private final Logger logger = LogManager.getLogger(getClass());
    /**
     * 开始时间
     */
    private long startTimeMillis = 0;
    /**
     * 结束时间
     */
    private long endTimeMillis = 0;

    /**
     * @param joinPoint 切入点
     */
    @Before("execution(* org.web3j.controller..*.*(..))")
    public void doBeforeMethod(JoinPoint joinPoint)
    {
        //记录方法开始执行的时间
        startTimeMillis = System.currentTimeMillis();
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("\nUrl: " + request.getRequestURI() + ", request: "+ JSON.toJSONString(joinPoint.getArgs()));
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
    }

    /**
     * @param joinPoint 切入点
     */
    @After("execution(* org.web3j.controller..*.*(..))")
    public void doAfterMethod(JoinPoint joinPoint)
    {
        //记录方法执行完成的时间
        endTimeMillis = System.currentTimeMillis();
        logger.info("Execution Time:" + (endTimeMillis - startTimeMillis));
    }

    /**
     * @param pjp 处理中的切入点
     * @return 处理结果
     * @throws Throwable 异常信息
     */
    @Around("execution(* org.web3j.controller..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable
    {
        /*RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String header = request.getHeader("User-Agent");
        String content = IOUtils.toString(request.getReader());*/
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Object result = pjp.proceed();
        //logger.info("【Execution Time:{}】\n【Header:{}】\n【Body:{}】\n【Response:{}】", endTimeMillis - startTimeMillis, header, content, JSON.toJSONString(result));
        logger.info("\nUrl: " + request.getRequestURI() + ", response:" + JSON.toJSONString(result));
        return result;
    }
}
