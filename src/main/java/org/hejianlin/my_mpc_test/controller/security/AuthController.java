package org.hejianlin.my_mpc_test.controller.security;

import cn.hutool.jwt.JWT;
import org.hejianlin.my_mpc_test.security.constant.MyConstant;
import org.hejianlin.my_mpc_test.security.dto.SignInReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;

/**
 * 一个请求过来，spring security会按照如下的方式走
 *  request -> Filter -> AuthenticationManager -> AuthenticationProvider -> UserDetailsService
 *  他们的默认实现如下：
 *  Filter： UsernamePasswordAuthenticationFilter 它拦截来自用户的登录请求，并从请求中提取用户名和密码信息
 *  AuthenticationManager： ProviderManager 会将请求委托给每个注册的 AuthenticationProvider 实例，使得 Spring Security 能够支持多种认证方式，如基于用户名密码的认证、基于令牌的认证等
 *  AuthenticationProvider： DaoAuthenticationProvider 通过从数据库或其他数据存储中获取用户的用户名、密码和权限信息，发起认证
 *  UserDetailsService： InMemoryUserDetailsManager 表示在内存中管理用户详细信息
 */
@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public String login(@RequestBody SignInReq req) {

        //在UserService中已经写死了账号密码是 hello
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
        authenticationManager.authenticate(authenticationToken);

        //上一步没有抛出异常说明认证成功，我们向用户颁发jwt令牌
        String token = JWT.create()
                .setPayload("username", req.getUsername())
                .setKey(MyConstant.JWT_SIGN_KEY.getBytes(StandardCharsets.UTF_8))
                .sign();

        return token;
    }

}
