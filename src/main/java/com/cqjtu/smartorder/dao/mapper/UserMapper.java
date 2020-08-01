package com.cqjtu.smartorder.dao.mapper;

import com.cqjtu.smartorder.dao.dataobject.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author mumu
 * @date 2020/7/24
 */
@Mapper
public interface UserMapper extends MyBaseMapper<UserDO> {

    UserDO getByName(String name);

    String getRoleByName(String name);

    List<Integer> findIdByRoleUnequal(Integer roleId);

    List<UserDO> search(Map<String, Object> map);

    Integer searchCount(Map<String, Object> map);
}
