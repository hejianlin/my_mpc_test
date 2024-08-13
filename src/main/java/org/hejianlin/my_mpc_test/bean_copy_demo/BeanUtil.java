package org.hejianlin.my_mpc_test.bean_copy_demo;

import cn.hutool.core.bean.copier.CopyOptions;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

public class BeanUtil {

    /**
     * 有条件拷贝对象的方法
     * @param current 被拷贝的对象
     * @param target  目标对象
     * @param <T> 这里指定两个对象都是某个类的子类
     */
    public static <T extends BaseEntity> void fillData(T current, T target) {
        if (current == null || target == null) {
            return;
        }
        //这里举个例子，以下的集合是模板对象不想被拷贝覆盖的字段集合
        Set<String> notCopyFields = Optional.ofNullable(target.getNotCopyFields()).orElse(Collections.emptySet());
        //这里的f,可以认为是对象的某个字段
        CopyOptions copyOptions = CopyOptions.create().setPropertiesFilter((f, v) -> {
            f.setAccessible(true);
            Object value;
            try {
                value = f.get(target);
            } catch (Exception e) {
                return false;
            }
            //只有目标对象对应字段的值为空，并且字段不在不想被拷贝覆盖的字段集合中，则可以拷贝
            return value == null && !notCopyFields.contains(f.getName());
        });
        cn.hutool.core.bean.BeanUtil.copyProperties(current, target, copyOptions);
    }
}
