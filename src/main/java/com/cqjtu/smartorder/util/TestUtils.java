package com.cqjtu.smartorder.util;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.hutool.core.util.StrUtil;
import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.dao.dataobject.UserDO;
import com.cqjtu.smartorder.global.RoleEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;

import javax.management.relation.Role;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author mumu
 * @date 2020/7/23
 */
public class TestUtils {

    public static void main(String[] args) {
//        System.out.println(BCrypt.hashpw("123", BCrypt.gensalt()));
//        System.out.println(EasyUtils.getLongId(333));
//        int countNum = 0;
//        countNum = ++countNum % 900;
//        System.out.println(countNum);
//        RoleEnum admin = RoleEnum.valueOf("ADMIN");
//        System.out.println(admin.getValue());
//        boolean r = StringUtils.isContainChinese("");
//        System.out.println(r);

//        List<String> strings = new ArrayList<>();
//        strings.add("a");
//        strings.add("a");
//        strings.add("a");
//        strings.add("a");

//        List<String> strings1 = new ArrayList<>(strings.subList(1, 2));
//
//        System.out.println(strings1);
//        System.out.println(EasyUtils.verifyUserInfo("aaaaa", "123"));
//        System.out.println(StringUtils.getFilename("https://dtapp-pub.dingtalk.com/dingtalk-desktop/win_installer/Release/DingTalk_v5.1.15.8.exe"));
//        User user = new User();
//        user.setUsername("zhao");
//        user.setPassword("123");
//
//        UserDO userDO = new UserDO();
//        userDO.setRole(RoleEnum.CHEF.getValue());
//
//        BeanUtils.copyProperties(user, userDO);
//        BeanUtil.copyProperties(user, userDO, CopyOptions.create().setIgnoreNullValue(false));
//
//        System.out.println(userDO);


//        BigDecimal bigDecimal = BigDecimal.valueOf(100.32 * 0.01396);
//        Float f = 100.32f * 0.01396f;

//        System.out.println(bigDecimal);
//        System.out.println(Math.);

        Float f = Float.valueOf("-0");
        Float ff = Float.valueOf("0");

        System.out.println(ff - f);
//        System.out.println(0.0 + f);
    }

}
