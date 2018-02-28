package shiro.mapper;

import java.util.List;
import shiro.entity.SysRolesPermissionsExample;
import shiro.entity.SysRolesPermissionsKey;

public interface SysRolesPermissionsMapper {
    int insert(SysRolesPermissionsKey record);

    int insertSelective(SysRolesPermissionsKey record);

    List<SysRolesPermissionsKey> selectByExample(SysRolesPermissionsExample example);
}