package org.hejianlin.my_mpc_test.security_demo.service.impl;

import org.hejianlin.my_mpc_test.security_demo.constant.RoleType;
import org.hejianlin.my_mpc_test.security_demo.dto.Role;
import org.hejianlin.my_mpc_test.security_demo.dto.User;
import org.hejianlin.my_mpc_test.security_demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public User getUserByName(String userName) {
        if (!"hello".equals(userName)) {
            throw new RuntimeException("用户不存在");
        }
        List<Role> roles = List.of( new Role(RoleType.USER));
        return new User(userName, passwordEncoder.encode("hello"), roles);
    }
}
