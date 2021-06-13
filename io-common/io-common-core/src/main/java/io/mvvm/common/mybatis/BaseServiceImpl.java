package io.mvvm.common.mybatis;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @program: io-admin
 * @description: MyBatisPlus 通用 service 实现
 * @param <M>    Mapper 对象
 * @param <T>    实体
 * @author: 潘南旭
 * @create: 2021-05-28 19:03
 **/
public class BaseServiceImpl<M extends BaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

}
