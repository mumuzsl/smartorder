package com.cqjtu.smartorder.api;

import com.cqjtu.smartorder.dao.dataobject.DishesDO;
import com.cqjtu.smartorder.dao.support.Page;
import com.cqjtu.smartorder.dao.support.PageRequest;

import java.util.Map;

/**
 * @author mumu
 * @date 2020/7/24
 */
public interface DishesService extends BaseService<DishesDO> {
    //    Page<DishesDO> pageOrderByRecommend(PageRequest pageRequest);
    Page search(PageRequest pageRequest, Map<String, Object> map);
}
