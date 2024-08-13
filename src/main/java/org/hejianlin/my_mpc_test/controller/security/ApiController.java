package org.hejianlin.my_mpc_test.controller.security;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个简单的spring security的开发步骤如下：
 * 1）引入security、jwt(使用hutool工具类) 的依赖，配置文件配置账号密码
 * 2）定义用户、角色对象、登录请求对象、jwt的签名key(写死为key)
 * 3) 定义UserService并实现，这里其实就是在内存中写死返回一个用户对象（携带固定 用户名、密码、角色）
 * 4）实现UserDetails，构造方法需要一个用户对象，实现它相关的方法，其中getAuthorities方法，就是使用用户的角色名称，来生成权限（ new SimpleGrantedAuthority(role.getRoleType().getRoleName())）
 * 5）实现UserDetailsService，从UserService中，根据用户名称，获取用户对象，然后实例化UserDetails对象
 * 6）实现AuthenticationProvider，从UserDetailsService中，根据用户名称，获取UserDetails，校验密码，并重新构建UsernamePasswordAuthenticationToken(即凭证)
 * 7）定义JwtAuthenticationTokenFilter过滤器，从header中获取token、token签名校验、从token中获取用户名称，通过UserDetailsService，获取UserDetails，
 * 使用UserDetails（用户名、密码、权限）构建UsernamePasswordAuthenticationToken(即凭证)，最后将凭证放在security的上下文中，供程序使用
 * 8）实现AccessDeniedHandler，自定义禁止访问响应内容，实现AuthenticationEntryPoint，自定义认证失败响应内容
 * 9）配置WebSecurityConfig：
 *  9.1）配置AuthenticationManager的bean, 默认使用AuthenticationConfiguration的.getAuthenticationManager()方法
 *  9.2）配置PasswordEncoder的bean, 使用BCryptPasswordEncoder
 *  9.3）配置JwtAuthenticationTokenFilter的bean
 *  9.4) 配置JwtAuthenticationProvider的bean
 *  9.5) 配置SecurityFilterChain：url访问权限设置、配置Provider、配置JwtAuthenticationTokenFilter、UsernamePasswordAuthenticationFilter、配置定义禁止访问响应内容和自定义认证失败响应内容
 * 10）还可以配置如下的接口，规定只有固定角色权限的用户才能访问
 */

/**
 * 测试步骤：
 * 1、登录： 会先走JwtAuthenticationTokenFilter，因为没有token, 会直接放行，然后走JwtAuthenticationProvider，从userDetailsService获取用户（用户获取失败或者密码校验失败会直接报认证失败），如果没有抛出异常，在登录接口中，会直接生成并返回token
 * 2、接口访问：
 * 1）白名单接口访问：例如：/test/**， JwtAuthenticationTokenFilter -> 直接通过
 * 2）非白名单接口带token访问：例如：/auth/hello，先走JwtAuthenticationTokenFilter -> UserDetailsService -> UserDetails
 * 3）非白名单接口不带token访问：例如：/auth/hello，JwtAuthenticationTokenFilter -> 认证失败
 * 4）非白名单接口带错误token访问：例如：/auth/hello，JwtAuthenticationTokenFilter -> 认证失败
 * 5）校验管理员权限的带token访问，例如：如下的接口，只有 user角色的才能访问
 */
@RestController
@RequestMapping("/api")
public class ApiController {

    //@PreAuthorize("hasRole('user')") 这个好像不生效了？加上去就禁止访问了？
    @PreAuthorize("hasRole('user')")
    @GetMapping("/users/{id}")
    public String getUserDetail(@PathVariable String id){
        return "用户详情:" + id;
    }


}