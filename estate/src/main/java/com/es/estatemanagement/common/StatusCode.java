package com.es.estatemanagement.common;

/**
 * <p>
 * 分页-标准返回结果
 * </p>
 * @author yang
 * @date 2024/5/20
 */
public class StatusCode {

    public static final int OK=2000;//成功
    public static final int ERROR =2001;//失败
    public static final int LOGINERROR =2002;//用户名或密码错误
    public static final int ACCESSERROR =2003;//权限不足
    public static final int REMOTEERROR =2004;//远程调用失败
    public static final int REPERROR =2005;//重复操作

}
