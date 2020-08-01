package com.cqjtu.smartorder.web;

import cn.hutool.core.util.StrUtil;
import com.cqjtu.smartorder.api.UserService;
import com.cqjtu.smartorder.api.model.Order;
import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.api.support.Result;
import com.cqjtu.smartorder.dao.dataobject.UserDO;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import com.cqjtu.smartorder.util.EasyUtils;
import com.cqjtu.smartorder.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author mumu
 * @date 2020/7/24
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("list")
    public Page list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                     @RequestParam(value = "size", defaultValue = "10") Integer size,
                     @RequestParam(value = "id", required = false) Integer id,
                     @RequestParam(value = "username", required = false) String username) {
        if (id != null || username != null) {
            Map<String, Object> map = new HashMap<>();

            map.put("id", id);
            map.put("username", username);

            return userService.search(PageRequest.of(page, size), map);
        }
        return userService.pageBy(PageRequest.of(page, size));
    }

    @GetMapping("{id:\\d+}")
    public UserDO get(@PathVariable("id") Integer id) {
        return userService.getById(id).orElseGet(UserDO::new);
    }

    @PostMapping("save")
    public Object save(@RequestBody User user) {
        if (!EasyUtils.verifyUserInfo(user)
                || user.getRole() == null
                || !user.getPassword().equals(user.getPassword2())) {
            return Result
                    .build()
                    .fail()
                    .message("请检查输入是否规范");
        }
        User user1 = userService.getByUsername(user.getUsername());
        Integer id = user.getId();
        if ((id == null && user1 != null) || (user1 != null && !user1.getId().equals(id))) {
            return Result
                    .build()
                    .fail()
                    .message("用户名已存在");
        }
        int i = userService.saveModel(user);
        return Result.build().ok().data(i);
    }

    @GetMapping("face")
    public void face(@RequestParam("username") String username,
                     HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
        User user = userService.getByUsername(username);
        String img = (user == null || StrUtil.isBlank(user.getImg())) ?
                "/api/file/downloadImage/face.png" : "/" + user.getImg();
        response.sendRedirect(img);
    }

    @PostMapping("remove")
    public Object remove(@RequestBody List<UserDO> users) {
        return userService.remove(users);
    }
}
