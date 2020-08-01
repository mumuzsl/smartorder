package com.cqjtu.smartorder.web;

import com.cqjtu.smartorder.api.DishesService;
import com.cqjtu.smartorder.dao.dataobject.DishesDO;
import com.cqjtu.smartorder.dao.support.ColumnSort;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import com.cqjtu.smartorder.dao.support.Sort;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author mumu
 * @date 2020/7/28
 */
@Controller
public class ViewController {

    @Resource
    private DishesService dishesService;

//    @GetMapping("login")
//    public String login(Model model) {
//        return "";
//    }

    //    @GetMapping("order-food")
    @SuppressWarnings("unchecked")
    public String orderFood(@RequestParam(value = "page", defaultValue = "0") Integer page,
                            @RequestParam(value = "size", defaultValue = "8") Integer size,
                            Model model) {
        List<ColumnSort> columnSorts = new ArrayList<>(1);
        columnSorts.add(new ColumnSort("recommend", Sort.DESC));

        @SuppressWarnings("unchecked")
        List<Object> objects = dishesService.pageBy(PageRequest.of(page, size, columnSorts)).getElements();

        int len = objects.size() <= 4 ? objects.size() : 4;

        List<Object> upList = new ArrayList<>(objects.subList(0, len));
        List<Object> downList = new ArrayList<>(objects.subList(len, objects.size()));

        model.addAttribute("upList", upList);
        model.addAttribute("downList", downList);

        return "order-food";
    }

}
