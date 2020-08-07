package com.cqjtu.smartorder.util;

import cn.hutool.core.util.StrUtil;
import com.cqjtu.smartorder.global.RoleEnum;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.util.StringUtils;

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
        System.out.println(StringUtils.getFilename("https://dtapp-pub.dingtalk.com/dingtalk-desktop/win_installer/Release/DingTalk_v5.1.15.8.exe"));

    }

}
