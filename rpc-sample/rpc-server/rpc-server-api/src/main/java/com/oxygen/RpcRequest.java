package com.oxygen;

import java.io.Serializable;

/**
 * @Description  RPC调用信息的封装
 * @Date 2020/5/18 15:45
 * @Created by oxygen
 */
public class RpcRequest implements Serializable {

    /**
     * 调用接口的类名称
     */
    private String className;

    /**
     * 调用的服务方法名称
     */
    private String methodName;


    /**
     * 调用的参数列表
     */
    private Object[] parameters;


    /**
     * 对外暴露接口的版本号
     */
    private String version;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
