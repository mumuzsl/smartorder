package com.cqjtu.smartorder.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

/**
 * @author mumu
 * @date 2020/7/23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class User {
    private Integer id;

    private String username;

    private String password2;

    private String password;

    private Integer role;

    private String img;
}
