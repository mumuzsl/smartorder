package com.cqjtu.smartorder.dao.dataobject;

import lombok.Data;

import java.util.Date;

/**
 * @author mumu
 * @date 2020/7/26
 */
@Data
public class SysMenuDO {
    private Long id;
    private String title;
    private String href;
    private Long pid;
    private String icon;
    private String target;
    private Integer sort;
    private Boolean status;
    private String remark;
    private Date createAt;
    private Date updateAt;
    private Date deleteAt;
}
