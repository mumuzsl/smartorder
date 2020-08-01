package com.cqjtu.smartorder.web;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.lang.UUID;
import com.cqjtu.smartorder.api.SysMenuService;
import com.cqjtu.smartorder.api.param.IMParam;
import com.cqjtu.smartorder.api.support.Result;
import com.cqjtu.smartorder.config.WebSecurityConfig;
import com.cqjtu.smartorder.global.RoleEnum;
import com.cqjtu.smartorder.util.IMUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/27
 */
@RestController
@RequestMapping("/api")
@CrossOrigin
public class IndexController {

    @Resource
    private SysMenuService sysMenuService;

    private final LineCaptcha lineCaptcha =
            CaptchaUtil.createLineCaptcha(100, 38, 4, 80);

    @GetMapping("captcha")
    public Object captcha(HttpServletRequest request, HttpServletResponse response) throws IOException {
        lineCaptcha.createCode();
        String uuid = UUID.randomUUID().toString(false);
        request.getSession().setAttribute(uuid, lineCaptcha.getCode());
        return Result
                .build()
                .ok()
                .message(uuid)
                .data("data:image/gif;base64," + Base64.encode(lineCaptcha.getImageBytes()));
    }

    @GetMapping("menu")
    public Map<String, Object> menu(HttpServletRequest request) {
        Object role = request.getSession().getAttribute("role");

        Integer roleId = Optional
                .ofNullable(role)
                .map(String::valueOf)
                .map(String::toUpperCase)
                .map(RoleEnum::valueOf)
                .map(RoleEnum::getValue)
                .orElse(WebSecurityConfig.DEV_MODE);

        return sysMenuService.menu(roleId);
    }


    @PostMapping("im/send")
    public Object send(@RequestBody IMParam imParam) {
        return IMUtils.add(imParam);
    }

    @GetMapping("im/fetch")
    public Object fetch() {
        return IMUtils.gets();
    }

//    @Scheduled(cron = "3 * * * * ?")
//    public Object update() {
//        return IMUtils.remove();
//    }

    @GetMapping
    public String a(String s) {
        return null;
    }

    public static String b(String s) {
        return null;
    }
}
