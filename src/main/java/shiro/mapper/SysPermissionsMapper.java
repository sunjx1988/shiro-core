package shiro.mapper;

import java.util.List;
import shiro.entity.SysPermissions;
import shiro.entity.SysPermissionsExample;

public interface SysPermissionsMapper {
    int insert(SysPermissions record);

    int insertSelective(SysPermissions record);

    List<SysPermissions> selectByExample(SysPermissionsExample example);
}