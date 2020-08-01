package com.cqjtu.smartorder.dao.mapper;

import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.dao.dataobject.NoticeDO;
import com.cqjtu.smartorder.dao.dataobject.UserNoticeDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author mumu
 * @date 2020/7/29
 */
@Mapper
public interface NoticeMapper extends MyBaseMapper<NoticeDO> {
    Integer insertUnread(UserNoticeDO userNoticeDO);

    Integer updateRead(UserNoticeDO userNoticeDO);

    List<Long> findUnreadByUserId(Integer userId);

    NoticeDO getByUid(Long uid);
}
