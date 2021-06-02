package io.mvvm.mapper;

import io.mvvm.entity.UserAccountDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @program: io-admin
 * @description: 用户账户信息
 * @author: Mr. Pan
 * @create: 2021-06-01 19:45
 **/
@Mapper
public interface IUserAccountMapper {

    /**
     * 按照用户名查询用户账户信息
     * @param username 用户名
     * @return         账户信息
     */
    @Select("SELECT ID, USERNAME, PASSWORD, ACCOUNT_NON_EXPIRED, ACCOUNT_NON_LOCKED, CREDENTIALS_NON_EXPIRED, STATUS AS ENABLED FROM SYS_ACCOUNT_TAB WHERE USERNAME = #{username}")
    UserAccountDetails selectUserAccountByUserName(String username);



}
