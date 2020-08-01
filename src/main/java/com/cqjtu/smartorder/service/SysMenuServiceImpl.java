package com.cqjtu.smartorder.service;

import com.cqjtu.smartorder.api.SysMenuService;
import com.cqjtu.smartorder.api.model.Menu;
import com.cqjtu.smartorder.dao.dataobject.SysMenuDO;
import com.cqjtu.smartorder.dao.mapper.SysMenuMapper;
import com.cqjtu.smartorder.util.TreeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mumu
 * @date 2020/7/26
 */
@Service
public class SysMenuServiceImpl implements SysMenuService {
    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public Map<String, Object> menu(int roleId) {
        Map<String, Object> map = new HashMap<>(16);
        List<SysMenuDO> menuList = sysMenuMapper.findAllByRoleIdOrderBySort(roleId);
        List<Menu> menuInfo = new ArrayList<>();
        for (SysMenuDO e : menuList) {
            Menu menu = new Menu();
            menu.setId(e.getId());
            menu.setPid(e.getPid());
            menu.setHref(e.getHref());
            menu.setTitle(e.getTitle());
            menu.setIcon(e.getIcon());
            menu.setTarget(e.getTarget());
            menuInfo.add(menu);
        }
        map.put("menuInfo", TreeUtils.toTree(menuInfo, 0L));
        Info homeInfo = new Info("首页", "/page/home.html", "");
        Info logoInfo = new Info("SmortOrder", "" , "/images/logo.png");
//        map.put("homeInfo", "{title: '首页',href: '/ruge-web-admin/page/welcome.html'}}");
//        map.put("logoInfo", "{title: 'RUGE ADMIN',image: 'images/logo.png'}");
        map.put("homeInfo", homeInfo);
        map.put("logoInfo", logoInfo);
        return map;
    }

    public static class Info {
        private String title;
        private String href;
        private String image;

        private Info(String title, String href, String image) {
            this.title = title;
            this.href = href;
            this.image = image;
        }

        public String getTitle() {
            return title;
        }

        public String getHref() {
            return href;
        }

        public String getImage() {
            return image;
        }
    }
}
