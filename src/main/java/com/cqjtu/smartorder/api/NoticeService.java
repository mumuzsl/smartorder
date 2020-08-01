package com.cqjtu.smartorder.api;

import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.dao.dataobject.NoticeDO;
import com.cqjtu.smartorder.dao.dataobject.UserNoticeDO;

import java.util.List;

/**
 * @author mumu
 * @date 2020/7/29
 */
public interface NoticeService extends BaseService<NoticeDO> {
    //    int saveModel(NoticeDO entity);
    List<NoticeDO> fetch(Integer userid);

    Integer read(UserNoticeDO userNoticeDO);
}
