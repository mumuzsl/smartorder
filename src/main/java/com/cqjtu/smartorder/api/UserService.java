package com.cqjtu.smartorder.api;

import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.dao.dataobject.UserDO;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import com.cqjtu.smartorder.service.AbstractService;

import java.util.Map;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/24
 */
public interface UserService extends BaseService<UserDO> {

    int saveModel(User entity);

    User getByUsername(String username);

    Page search(PageRequest pageRequest, Map<String, Object> map);

//    Optional<Integer> getUserId();
}
