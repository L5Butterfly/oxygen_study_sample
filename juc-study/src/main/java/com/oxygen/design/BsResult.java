package com.oxygen.design;

import java.io.Serializable;


/**
 *  数据响应结果构造
 *  @param <T> 任意返回数据对象
 *  @author xxx
 *
 *  前端或者中间等远程调用或者前后端JSON等交响应结果
 */
public class BsResult<T> implements Serializable {


    private static final long serialVersionUID = -5845265323389952436L;

    /**
     * lombok 的@Builder, 没有默认构造方法，可能会导致其他问题。
     */
    public static class BsResultBuilder<T> {
        private BsResult<T> ret = new BsResult<>();

        public BsResultBuilder<T> success(boolean success) {
            ret.setSuccess(success);
            if (success) {
                ret.setCode("00000");
            }
            return this;
        }


        /**
         *
         * @param result
         * @return
         */
        public BsResultBuilder<T> result(T result) {
            ret.setResult(result);
            return this;
        }


        /**
         *
         * @param msg
         * @return
         */
        public BsResultBuilder<T> msg(Object msg) {
            ret.setMsg(msg);
            return this;
        }


        /**
         *
         * @param code
         * @return
         */
        public BsResultBuilder<T> code(String code) {
            ret.setCode(code);
            return this;
        }


        /**
         *
         * @return
         */
        public BsResult<T> build() {
            return ret;
        }
    }


    // =====data info =====

    /** success **/
    private boolean success = true;

    /** code **/
    private String code;

    /** data **/
    private T  result;

    /** msg **/
    private Object msg;


    // =====get set =====
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }


    /**
     *
     * @param <T>
     * @return
     */
    public static <T> BsResultBuilder<T> builder() {
        return new BsResultBuilder<>();
    }


    /**
     *
     * @param <T>
     * @return
     */
    public static <T> BsResult<T> successResult() {
        BsResult<T> result = new BsResult<>();
        result.setSuccess(true);
        return result;
    }

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BsResult<T> successResult(T data) {
        BsResult<T> result = new BsResult<>();
        result.setSuccess(true);
        result.setResult(data);
        return result;
    }


    /**
     *
     * @param <T>
     * @return
     */
    public static <T> BsResult<T> errResult() {
        BsResult<T> result = new BsResult<>();
        result.setSuccess(false);
        return result;
    }


    /**
     *
     * @param code
     * @param <T>
     * @return
     */
    public static <T> BsResult<T> errResult(String code) {
        BsResult<T> result = new BsResult<>();
        result.setSuccess(false);
        result.setCode(code);
        return result;
    }


    /**
     *
     * @param bsResult
     * @param <T>
     * @return
     */
    public static <T> BsResult<T> errResult(BsResult bsResult) {
        if (null == bsResult) {
            bsResult = BsResult.errResult();
        }
        BsResult<T> result = new BsResult<>();
        result.setSuccess(false);
        result.setCode(bsResult.getCode());
        result.setMsg(bsResult.getMsg());
        return result;
    }


    /**
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> BsResult<T> errResult(String code, String msg) {
        BsResult<T> result = new BsResult<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

}
