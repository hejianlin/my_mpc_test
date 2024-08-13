package org.hejianlin.my_mpc_test.controller.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/***
 * 如果访问接口出现登录框：可以做如下处理：
 * 查看启动日志，有类似如下的日志：Using generated security password: d819a99c-f3c7-431a-bff6-6f01c14d586e
 * 即 用户名 是  user, 密码是随机生成的，登录即可
 * 在配置文件中，可以直接配置固定的用户和密码
 * 参考教程：https://shusheng007.top/2023/02/15/springsecurity/#google_vignette
 */
@Slf4j
@RestController
@RequestMapping("/auth")
public class SecurityTestController {

    @GetMapping("/hello")
    public String sayHello(){
        return "hello security";
    }
}
