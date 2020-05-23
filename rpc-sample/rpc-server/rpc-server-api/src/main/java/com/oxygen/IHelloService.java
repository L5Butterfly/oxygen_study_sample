package com.oxygen;

/**
 * @Description
 * @Date 2020/5/18 15:45
 * @Created by oxygen
 */
public interface IHelloService {

    /**
     * sayHello
     * @param content
     * @return
     */
    String sayHello(String content);

    /**
     * 保存用户
     * @param user
     * @return
     */
    String saveUser(User user);

}
