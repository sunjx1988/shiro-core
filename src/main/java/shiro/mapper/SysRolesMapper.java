package shiro.mapper;

import java.util.List;
import shiro.entity.SysRoles;
import shiro.entity.SysRolesExample;

public interface SysRolesMapper {
    int insert(SysRoles record);

    int insertSelective(SysRoles record);

    List<SysRoles> selectByExample(SysRolesExample example);
}