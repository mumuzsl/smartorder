package com.cqjtu.smartorder.service;

import com.cqjtu.smartorder.dao.dataobject.UserDO;
import com.cqjtu.smartorder.dao.mapper.UserMapper;
import com.cqjtu.smartorder.global.RoleEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/25
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDO userDO = userMapper.getByName(s);
        List<GrantedAuthority> authorityList = new ArrayList<>();

        if (userDO == null) {
            throw new UsernameNotFoundException("username not found");
        }

        Optional.of(userDO)
                .map(UserDO::getRole)
                .map(RoleEnum::valueOf)
                .map(RoleEnum::getName)
                .map(SimpleGrantedAuthority::new)
                .ifPresent(authorityList::add);

        return new User(userDO.getUsername(), userDO.getPassword(), authorityList);
    }

    private static String getRole(int role) {
        switch (role) {
            case 1:
                return "ADMIN";
            case 2:
                return "WAITER";
            case 3:
                return "CHEF";
            default:
                return "";
        }
    }
}
