package com.cqjtu.smartorder.dao.dataobject;

import lombok.Data;

/**
 * @author mumu
 * @date 2020/7/23
 */
@Data
public class UserDO {
    private Integer id;

    private String username;

    private String password;

    private Integer role;

    private String img;
}
