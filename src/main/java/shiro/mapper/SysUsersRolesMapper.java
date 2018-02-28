package shiro.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import shiro.entity.SysUsersRolesExample;
import shiro.entity.SysUsersRolesKey;

public interface SysUsersRolesMapper {
    int countByExample(SysUsersRolesExample example);

    int deleteByExample(SysUsersRolesExample example);

    int insert(SysUsersRolesKey record);

    int insertSelective(SysUsersRolesKey record);

    List<SysUsersRolesKey> selectByExample(SysUsersRolesExample example);

    int updateByExampleSelective(@Param("record") SysUsersRolesKey record, @Param("example") SysUsersRolesExample example);

    int updateByExample(@Param("record") SysUsersRolesKey record, @Param("example") SysUsersRolesExample example);
}