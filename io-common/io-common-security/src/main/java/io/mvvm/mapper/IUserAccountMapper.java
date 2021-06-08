package io.mvvm.mapper;

import io.mvvm.model.ResourceApiDO;
import io.mvvm.model.UserAccountDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

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

    /**
     * 查询用户角色
     * @param id 账户ID
     * @return   角色列表
     */
    @Select("SELECT R.ROLE_NAME FROM SYS_ROLE_TAB R INNER JOIN SYS_USER_ROLE_MAPPING UR ON R.ID = UR.RID WHERE UR.UID = #{id}")
    Set<String> selectRoleByAccountId(Long id);

    /**
     * 按照角色名称查询可用的API资源
     * @param roleName  角色名称
     * @param method    请求方式
     * @return          API资源列表
     */
    List<ResourceApiDO> selectResourceApiByRoleName(@Param("method") Integer method, @Param("roleNames") Set<String> roleName);

    /**
     * 根据资源uri查询可管理的角色
     * @param uri   资源URI
     * @return      角色列表
     */
    @Select("SELECT RT.ROLE_NAME " +
            "FROM SYS_ROLE_TAB RT " +
            "         INNER JOIN SYS_RESOURCE_ROLE_MAPPING RRM " +
            "                    ON RRM.ROLE_ID = RT.ID " +
            "         INNER JOIN SYS_RESOURCE_TAB RES " +
            "                    ON RES.URI = #{uri} AND RES.ID = RRM.RESOURCE_ID " +
            "GROUP BY RT.ID;")
    Set<String> selectRoleNamesByResourceUri(String uri);

}
