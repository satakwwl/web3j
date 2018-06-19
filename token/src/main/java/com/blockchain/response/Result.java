package com.blockchain.response;


/**
 * 结果处理类
 */
public final class Result
{
    private Result()
    {

    }

    /**
     * 生成操作成功的返回对象
     *
     * @param <T> the type parameter
     * @return response
     */
    public static <T> Response<T> success()
    {
        return new Response(ErrorCode.SUCCESS, "操作成功");
    }


    /**
     * 生成操作成功的返回对象
     *
     * @param <T>  the type parameter
     * @param desc 自定义成功信息
     * @return response
     */
    public static <T> Response<T> success(String desc)
    {
        return new Response(ErrorCode.SUCCESS, desc);
    }

    /**
     * 生成操作失败的返回对象
     *
     * @param <T>  the type parameter
     * @param desc 错误信息
     * @return response
     */
    public static <T> Response<T> fail(String desc)
    {
        return new Response(ErrorCode.FAIL, desc);
    }

    /**
     * 生成操作失败的返回对象
     *
     * @param <T>  the type parameter
     * @param code 自定义错误码
     * @param desc 错误信息
     * @return response
     */
    public static <T> Response<T> fail(int code, String desc)
    {
        return new Response(code, desc);
    }

    /**
     * 生成操作错误的返回对象
     *
     * @param <T>  the type parameter
     * @param desc 错误信息
     * @return response
     */
    public static <T> Response<T> error(String desc)
    {
        return new Response(ErrorCode.ERROR, desc);
    }

    /**
     * 把结果对象返回到客户端
     *
     * @param <T>    the type parameter
     * @param result 结果集
     * @return response
     */
    public static <T> Response<T> resultSet(T result)
    {
        return new Response(ErrorCode.SUCCESS, "操作成功", result);
    }


    /**
     * 把结果对象返回到客户端
     *
     * @param <T>    the type parameter
     * @param msg    提示信息
     * @param result 结果集
     * @return response
     */
    public static <T> Response<T> resultSet(String msg, T result)
    {
        return new Response(ErrorCode.SUCCESS, msg, result);
    }

    /**
     * 把结果对象返回到客户端
     *
     * @param <T>    the type parameter
     * @param code   自定义代码
     * @param msg    自定义信息
     * @param result 结果集
     * @return response
     */
    public static <T> Response<T> resultSet(int code, String msg, T result)
    {
        return new Response(code, msg, result);
    }
}
