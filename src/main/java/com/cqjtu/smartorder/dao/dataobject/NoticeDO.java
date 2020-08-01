package com.cqjtu.smartorder.dao.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * @author mumu
 * @date 2020/7/29
 */
@Data
public class NoticeDO {

    private Integer id;

    private Long uid;

    private String title;

    private String content;

    private Integer userId;

    private Date createTime;
}
