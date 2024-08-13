package org.hejianlin.my_mpc_test.security_demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hejianlin.my_mpc_test.security_demo.constant.RoleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private RoleType roleType;

}
