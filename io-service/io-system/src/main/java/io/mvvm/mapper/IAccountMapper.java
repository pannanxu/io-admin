package io.mvvm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.mvvm.entity.domain.AccountTab;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: io-admin
 * @description: UserAccountMapper
 * @author: Mr. Pan
 * @create: 2021-05-23 21:01
 **/
@Mapper
public interface IAccountMapper extends BaseMapper<AccountTab> {

    void insert(List<AccountTab> tabs);
}
