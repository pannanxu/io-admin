package io.mvvm.model.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.mvvm.model.BaseDomain;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: io-admin
 * @description: 资源表
 * @author: Mr. Pan
 * @create: 2021-06-11 20:48
 **/
@Getter
@Setter
@TableName("SYS_RESOURCE_TAB")
public class ResourceTab extends BaseDomain implements Serializable {

    @TableId(value = "ID", type = IdType.ASSIGN_ID)
    private Long id;
    @TableField(value = "PARENT_ID")
    private Long parentId;
    @TableField(value = "TITLE")
    private String title;
    @TableField(value = "DESCRIPTION")
    private String description;
    @TableField(value = "URI")
    private String uri;
    @TableField(value = "METHOD")
    private Integer method;
    @TableField(value = "MARK")
    private String mark;
    @TableField(value = "TARGET")
    private String target;
    @TableField(value = "ICON")
    private String icon;
    @TableField(value = "PARAMS")
    private String params;
    @TableField(value = "IS_SHOW")
    private Boolean isShow;
    @TableField(value = "IS_REFRESH")
    private Boolean isRefresh;
    @TableField(value = "MENU_SORT")
    private Integer menuSort;
    @TableField(value = "TYPE")
    private Integer type;
    @TableField(value = "STATUS")
    private Integer status;

}
