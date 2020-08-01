package com.cqjtu.smartorder.dao.mapper;

import com.cqjtu.smartorder.api.model.OrderDetail;
import com.cqjtu.smartorder.dao.dataobject.DishesDO;
import com.cqjtu.smartorder.dao.dataobject.OrderDetailDO;
import com.cqjtu.smartorder.dao.support.PageRequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author mumu
 * @date 2020/7/24
 */
@Mapper
public interface DishesMapper extends MyBaseMapper<DishesDO> {
    Float getPriceById(Integer id);

    List<DishesDO> search(Map<String, Object> map);
//    List<DishesDO> findAllOrderByRecommend(PageRequest pageRequest);

    Integer searchCount(Map<String, Object> map);
}
