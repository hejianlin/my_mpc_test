package org.hejianlin.my_mpc_test.bean_copy_demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hejianlin.my_mpc_test.security_demo.dto.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class SourceEntity extends BaseEntity{

    private Integer id;

    private String address;

    private User user;
}
