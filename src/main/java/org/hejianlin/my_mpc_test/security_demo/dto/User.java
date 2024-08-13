package org.hejianlin.my_mpc_test.security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private String userName;
    private String password;

    private List<Role> roles;
}
