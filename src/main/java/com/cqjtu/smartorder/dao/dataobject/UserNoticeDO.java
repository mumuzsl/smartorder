package com.cqjtu.smartorder.dao.dataobject;

import lombok.Data;
import sun.rmi.runtime.Log;

/**
 * @author mumu
 * @date 2020/7/29
 */
@Data
public class UserNoticeDO {
    private Integer id;

    private Long noticeUid;

    private Integer readStatus;

    private Integer userId;
}
