package org.hejianlin.my_mpc_test.bean_copy_demo;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MapstructDemo {

    MapstructDemo INSTANCE = Mappers.getMapper(MapstructDemo.class);

    TargetEntity toTarget(SourceEntity sourceEntity);

    List<TargetEntity> toTargets(List<SourceEntity> sourceEntities);
}
