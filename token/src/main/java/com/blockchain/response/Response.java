package com.blockchain.response;


import java.io.Serializable;

/**
 * 标准返回集
 *
 * @param <T> 返回的范型类 创建方式请参考
 */
public class Response<T> implements Serializable {
    /**
     * 结果码 0表示成功   1表示失败
     */
    private int code;
    /**
     * 消息
     */
    private String msg;
    /**
     * 结果集
     */
    private T result;


    /**
     * <p>Title: </p>
     * <p>Description: </p>
     *
     * @param id   the id
     * @param desc the desc
     */
    Response(int id, String desc) {
        this.code = id;
        this.msg = desc;
    }

    /**
     * <p>Title: </p>
     * <p>Description: </p>
     *
     * @param id     the id
     * @param desc   the desc
     * @param result the result
     */
    Response(int id, String desc, T result) {
        this.code = id;
        this.msg = desc;
        this.result = result;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(int code) {
        this.code = code;
    }

    /**
     * Gets msg.
     *
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * Sets msg.
     *
     * @param msg the msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * Gets result.
     *
     * @return the result
     */
    public T getResult() {
        return result;
    }

    /**
     * Sets result.
     *
     * @param result the result
     */
    public void setResult(T result) {
        this.result = result;
    }
}

