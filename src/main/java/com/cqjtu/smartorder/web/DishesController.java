package com.cqjtu.smartorder.web;

import com.cqjtu.smartorder.api.DishesService;
import com.cqjtu.smartorder.dao.dataobject.DishesDO;
import com.cqjtu.smartorder.dao.dataobject.UserDO;
import com.cqjtu.smartorder.dao.mapper.DishesMapper;
import com.cqjtu.smartorder.dao.support.ColumnSort;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import com.cqjtu.smartorder.dao.support.Sort;
import com.cqjtu.smartorder.util.EasyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author mumu
 * @date 2020/7/24
 */
@RestController
@RequestMapping("/api/dishes")
public class DishesController {
    @Autowired
    private DishesService dishesService;

    @GetMapping("list")
    public Page list(@RequestParam(value = "page", defaultValue = "0") Integer page,
                     @RequestParam(value = "size", defaultValue = "10") Integer size,
                     @RequestParam(value = "name", required = false) String name) {
        if (name != null) {
            Map<String, Object> map = new HashMap<>();

            map.put("name", name);

            return dishesService.search(PageRequest.of(page, size), map);
        }

        List<ColumnSort> columnSorts = new ArrayList<>(1);
        columnSorts.add(new ColumnSort("recommend", Sort.DESC));
        return dishesService.pageBy(PageRequest.of(page, size, columnSorts));
    }

    @GetMapping("{id:\\d+}")
    public DishesDO get(@PathVariable("id") Integer id) {
        return dishesService.getById(id).orElseGet(DishesDO::new);
    }

    @PostMapping("save")
    public Object save(@RequestBody DishesDO dishesDO) {
        return dishesService.save(dishesDO);
    }

    @PostMapping("remove")
    public Object remove(@RequestBody List<DishesDO> dishes) {
        return dishesService.remove(dishes);
    }
}
