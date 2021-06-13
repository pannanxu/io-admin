package io.mvvm.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @program: io-admin
 * @description:
 * @author: 潘南旭
 * @create: 2021-06-11 21:13
 **/
@Data
public class ResourceVO implements Serializable {

    private Long id;
    private Long parentId;
    private String title;
    private String description;
    private String uri;
    private String mark;
    private String target;
    private String icon;
    private String params;
    private Boolean isShow;
    private Boolean isRefresh;
    private Integer menuSort;

}
