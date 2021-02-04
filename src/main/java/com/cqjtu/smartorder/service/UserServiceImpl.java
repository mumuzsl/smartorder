package com.cqjtu.smartorder.service;

import com.cqjtu.smartorder.api.UserService;
import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.dao.dataobject.UserDO;
import com.cqjtu.smartorder.dao.mapper.UserMapper;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.Map;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/24
 */
@Service
public class UserServiceImpl extends AbstractService<UserDO> implements UserService {

    private UserMapper userMapper;

    private static BeanCopier copier1 = BeanCopier.create(User.class, UserDO.class, false);
    private static BeanCopier copier2 = BeanCopier.create(UserDO.class, User.class, false);

    public UserServiceImpl(UserMapper userMapper) {
        super(userMapper);
        this.userMapper = userMapper;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Throwable.class})
    @Override
    public int saveModel(User entity) {
        UserDO userDO = new UserDO();
        copier1.copy(entity, userDO, null);
        userDO.setPassword(BCrypt.hashpw(entity.getPassword(), BCrypt.gensalt()));
        return save(userDO);
    }

    @Override
    public User getByUsername(String username) {
        User user = new User();
        UserDO userDO = userMapper.getByName(username);
        if (userDO == null) {
            return null;
        }
        copier2.copy(userDO, user, null);
        return user;
    }

    @Override
    public Page search(PageRequest pageRequest, Map<String, Object> map) {
        map.put("offset", pageRequest.getOffset());
        map.put("rows", pageRequest.getRows());
        return Page.of(pageRequest, userMapper.searchCount(map), userMapper.search(map));
    }

}
