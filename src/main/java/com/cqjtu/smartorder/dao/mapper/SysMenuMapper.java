package com.cqjtu.smartorder.dao.mapper;

import com.cqjtu.smartorder.dao.dataobject.SysMenuDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author mumu
 * @date 2020/7/26
 */
@Mapper
public interface SysMenuMapper {
    List<SysMenuDO> findAllByRoleIdOrderBySort(Integer roleId);
}
