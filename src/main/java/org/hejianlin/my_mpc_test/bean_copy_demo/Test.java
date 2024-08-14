package org.hejianlin.my_mpc_test.bean_copy_demo;

import cn.hutool.json.JSONUtil;
import org.hejianlin.my_mpc_test.security_demo.constant.RoleType;
import org.hejianlin.my_mpc_test.security_demo.dto.Role;
import org.hejianlin.my_mpc_test.security_demo.dto.User;

import java.util.List;
import java.util.Set;

public class Test {

    public static void main(String[] args) {
        SourceEntity source1 = new SourceEntity();
        source1.setId(1);
        source1.setAddress("广州");
        User user = new User();
        user.setUserName("小白");
        user.setPassword("123456");
        Role role = new Role();
        role.setRoleType(RoleType.ADMIN);
        user.setRoles(List.of(role));
        source1.setUser(user);

        System.out.println("测试普通条件拷贝,他们类型应该要一致");

        SourceEntity source2 = new SourceEntity();
        source2.setNotCopyFields(Set.of("address"));
        BeanUtil.fillData(source1,source2);
        System.out.println(JSONUtil.toJsonStr(source2));


        System.out.println("测试类型不一致的深拷贝->单个拷贝：");
        TargetEntity target = MapstructDemo.INSTANCE.toTarget(source1);
        System.out.println(JSONUtil.toJsonStr(target));

        System.out.println("测试类型不一致的深拷贝->集合拷贝：");
        List<TargetEntity> targets = MapstructDemo.INSTANCE.toTargets(List.of(source1));
        System.out.println(JSONUtil.toJsonStr(targets));
    }
}
