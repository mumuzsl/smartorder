package com.cqjtu.smartorder.api;

import java.util.Map;

/**
 * @author mumu
 * @date 2020/7/26
 */
public interface SysMenuService {
    Map<String, Object> menu(int roleId);
}
