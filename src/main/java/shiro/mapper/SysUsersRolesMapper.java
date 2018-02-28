package shiro.mapper;

import java.util.List;
import shiro.entity.SysUsersRolesExample;
import shiro.entity.SysUsersRolesKey;

public interface SysUsersRolesMapper {
    int insert(SysUsersRolesKey record);

    int insertSelective(SysUsersRolesKey record);

    List<SysUsersRolesKey> selectByExample(SysUsersRolesExample example);
}