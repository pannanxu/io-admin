package io.mvvm.model.domain;

import lombok.Data;

/**
 * @program: io-admin
 * @description: 资源和角色查询实体
 * @author: 潘南旭
 * @create: 2021-06-13 20:42
 **/
@Data
public class ResourceRoleDO {

    private String uri;
    private String roleName;
    private Integer method;
    private String methodName;

}
