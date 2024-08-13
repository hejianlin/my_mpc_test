package org.hejianlin.my_mpc_test.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hejianlin.my_mpc_test.security.constant.RoleType;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private RoleType roleType;

}
