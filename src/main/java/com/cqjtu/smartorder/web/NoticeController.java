package com.cqjtu.smartorder.web;

import cn.hutool.core.lang.UUID;
import com.cqjtu.smartorder.api.NoticeService;
import com.cqjtu.smartorder.api.UserService;
import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.dao.dataobject.NoticeDO;
import com.cqjtu.smartorder.dao.dataobject.UserNoticeDO;
import com.cqjtu.smartorder.dao.mapper.NoticeMapper;
import com.cqjtu.smartorder.dao.support.PageRequest;
import com.cqjtu.smartorder.util.EasyUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.security.Principal;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/29
 */
@RestController
@RequestMapping("/api/notice")
public class NoticeController {

    @Resource
    private UserService userService;

    @Resource
    private NoticeService noticeService;

    @PostMapping(value = "send")
    public Object send(@RequestBody Map map) throws UnsupportedEncodingException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String str = String.valueOf(map.get("content"));

        NoticeDO noticeDO = new NoticeDO();

        Optional.of(authentication)
                .map(Principal::getName)
                .map(userService::getByUsername)
                .map(User::getId)
                .ifPresent(noticeDO::setUserId);

        noticeDO.setCreateTime(new Date());
        noticeDO.setUid(EasyUtils.getLongId());
        noticeDO.setContent(str);

        return noticeService.save(noticeDO);
    }

    @GetMapping("fetch")
    public Object fetch() {
        return findUserId()
                .map(noticeService::fetch)
                .orElse(null);
    }

    @GetMapping("read")
    public Object read(@RequestParam("noticeId") Long noticeUid) {
        UserNoticeDO userNoticeDO = new UserNoticeDO();
        userNoticeDO.setNoticeUid(noticeUid);

        findUserId().ifPresent(userNoticeDO::setUserId);

        return noticeService.read(userNoticeDO);
    }

    public Optional<Integer> findUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return Optional.ofNullable(authentication)
                .map(Principal::getName)
                .map(userService::getByUsername)
                .map(User::getId);
    }
}
