package com.cqjtu.smartorder.service;

import com.cqjtu.smartorder.api.DishesService;
import com.cqjtu.smartorder.dao.dataobject.DishesDO;
import com.cqjtu.smartorder.dao.mapper.DishesMapper;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mumu
 * @date 2020/7/24
 */
@Service
public class DishesServiceImpl extends AbstractService<DishesDO> implements DishesService {

    private DishesMapper dishesMapper;


    public DishesServiceImpl(DishesMapper dishesMapper) {
        super(dishesMapper);
        this.dishesMapper = dishesMapper;
    }

    @Override
    public Page search(PageRequest pageRequest, Map<String, Object> map) {
        map.put("offset", pageRequest.getOffset());
        map.put("rows", pageRequest.getRows());

        return Page.of(pageRequest, dishesMapper.searchCount(map), dishesMapper.search(map));
    }
}
