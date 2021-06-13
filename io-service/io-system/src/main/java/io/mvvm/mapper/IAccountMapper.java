package io.mvvm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.mvvm.model.domain.AccountTab;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: io-admin
 * @description: UserAccountMapper
 * @author: 潘南旭
 * @create: 2021-05-23 21:01
 **/
@Mapper
public interface IAccountMapper extends BaseMapper<AccountTab> {

}
