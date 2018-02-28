package shiro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import shiro.entity.SysRolesPermissionsExample;
import shiro.entity.SysRolesPermissionsKey;

public interface SysRolesPermissionsMapper {
    int countByExample(SysRolesPermissionsExample example);

    int deleteByExample(SysRolesPermissionsExample example);

    int insert(SysRolesPermissionsKey record);

    int insertSelective(SysRolesPermissionsKey record);

    List<SysRolesPermissionsKey> selectByExample(SysRolesPermissionsExample example);

    int updateByExampleSelective(@Param("record") SysRolesPermissionsKey record, @Param("example") SysRolesPermissionsExample example);

    int updateByExample(@Param("record") SysRolesPermissionsKey record, @Param("example") SysRolesPermissionsExample example);
}