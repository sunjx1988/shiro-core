package shiro.mapper;

import java.util.List;
import shiro.entity.SysUsers;
import shiro.entity.SysUsersExample;

public interface SysUsersMapper {
    int insert(SysUsers record);

    int insertSelective(SysUsers record);

    List<SysUsers> selectByExample(SysUsersExample example);
}