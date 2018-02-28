package shiro.mapper.custom;

import shiro.entity.SysPermissions;
import shiro.mapper.SysUsersMapper;

import java.util.List;

/**
 * Created by sunjx on 2018/2/28.
 */
public interface CustomUsersMapper extends SysUsersMapper {

    List<SysPermissions> selectPermissionByUserName(String userName);
}
