package io.mvvm.model;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @program: io-admin
 * @description: 公共字段
 * @author: Mr. Pan
 * @create: 2021-06-11 21:00
 **/
@Getter
@Setter
public class BaseDomain implements Serializable {

    @TableField(value = "CREATE_TIME")
    private long createTime;
    @TableField(value = "CREATE_BY")
    private long createBy;
    @TableField(value = "UPDATE_TIME")
    private long updateTime;
    @TableField(value = "UPDATE_BY")
    private long updateBy;

}
