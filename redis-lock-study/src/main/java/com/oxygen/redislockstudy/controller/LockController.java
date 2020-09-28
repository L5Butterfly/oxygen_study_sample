//package com.oxygen.redislockstudy.controller;
//
//
//import com.oxygen.redislockstudy.annotation.CacheLock;
//import com.oxygen.redislockstudy.annotation.CacheParam;
//import com.oxygen.redislockstudy.annotation.LocalLock;
//import com.oxygen.redislockstudy.entity.UserDTO;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author oxygen
// * @description 控制层
// * @date 2020/5/21 11:22
// **/
//@RestController
//@RequestMapping("/redis")
//public class LockController {
//
//
//    @CacheLock(prefix = "test")
//    @GetMapping("/test")
//    public String query(@CacheParam(name = "token") @RequestParam String token) {
//        return "success - " + token;
//    }
//
//
//    @CacheLock(prefix = "test2")
//    @GetMapping("/test2")
//    public String query2(@CacheParam(name = "token") UserDTO userDTO) {
//        return "success - " + userDTO.getName();
//    }
//
//
//    /**
//     * @param token
//     * @return
//     */
//    @LocalLock(key = "key:arg[0]")
//    @GetMapping("/test3")
//    public String query3(@RequestParam String token) {
//        return "success - " + token;
//    }
//}
