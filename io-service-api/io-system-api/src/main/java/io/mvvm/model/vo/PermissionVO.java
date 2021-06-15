package io.mvvm.model.vo;

import lombok.Data;

/**
 * @program: io-admin
 * @description: 资源权限
 * @author: 潘南旭
 * @create: 2021-06-15 23:39
 **/
@Data
public class PermissionVO {

    private String uri;
    private String permission;
    private String method;
    /**
     * 0: Api, 1: 菜单
     */
    private Integer type;

}
