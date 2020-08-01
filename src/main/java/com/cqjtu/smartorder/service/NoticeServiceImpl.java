package com.cqjtu.smartorder.service;

import com.cqjtu.smartorder.api.NoticeService;
import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.dao.dataobject.NoticeDO;
import com.cqjtu.smartorder.dao.dataobject.UserNoticeDO;
import com.cqjtu.smartorder.dao.mapper.NoticeMapper;
import com.cqjtu.smartorder.dao.mapper.UserMapper;
import com.cqjtu.smartorder.global.RoleEnum;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mumu
 * @date 2020/7/29
 */
@Service
public class NoticeServiceImpl extends AbstractService<NoticeDO> implements NoticeService {

    private UserMapper userMapper;
    private NoticeMapper noticeMapper;

    public NoticeServiceImpl(NoticeMapper noticeMapper, UserMapper userMapper) {
        super(noticeMapper);
        this.noticeMapper = noticeMapper;
        this.userMapper = userMapper;
    }

    @Transactional(propagation = Propagation.NESTED, rollbackFor = {Throwable.class})
    @Override
    public int save(NoticeDO entity) {
        List<Integer> ids = userMapper.findIdByRoleUnequal(RoleEnum.ADMIN.getValue());

        ids.forEach(id -> {
            UserNoticeDO userNoticeDO = new UserNoticeDO();
            userNoticeDO.setUserId(id);
            userNoticeDO.setNoticeUid(entity.getUid());

            noticeMapper.insertUnread(userNoticeDO);
        });

        return super.save(entity);
    }

    @Override
    public List<NoticeDO> fetch(Integer userid) {
        List<Long> noticeUids = noticeMapper.findUnreadByUserId(userid);
        List<NoticeDO> noticeDOs = new ArrayList<>();
        noticeUids.forEach(noticeUid -> noticeDOs.add(noticeMapper.getByUid(noticeUid)));
        return noticeDOs;
    }

    @Override
    public Integer read(UserNoticeDO userNoticeDO) {
        return noticeMapper.updateRead(userNoticeDO);
    }
}
