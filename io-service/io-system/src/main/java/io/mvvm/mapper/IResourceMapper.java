package io.mvvm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.mvvm.model.domain.ResourceRoleDO;
import io.mvvm.model.domain.ResourceTab;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Set;

/**
 * @program: io-admin
 * @description: 资源数据访问层
 * @author: 潘南旭
 * @create: 2021-06-11 21:06
 **/
@Mapper
public interface IResourceMapper extends BaseMapper<ResourceTab> {

    /**
     * 按照用户角色查询资源列表
     *
     * @param type  资源类型(0:API,1:菜单,-1:不筛选)
     * @param roles 角色列表
     * @return      {@link List<ResourceTab>}
     */
    List<ResourceTab> selectResourceListByType(@Param("type") int type, @Param("roles") Set<String> roles);

    /**
     * 查询资源和角色
     * @param type 0:api 1:menu
     * @return  资源所拥有的角色
     */
    @Select("SELECT RES.URI, ROLE.ROLE_NAME, RES.METHOD " +
            "FROM SYS_RESOURCE_TAB RES " +
            "LEFT JOIN SYS_RESOURCE_ROLE_MAPPING RRM " +
            "ON RES.ID = RRM.RESOURCE_ID " +
            "INNER JOIN SYS_ROLE_TAB ROLE " +
            "ON ROLE.ID = RRM.ROLE_ID " +
            "WHERE RES.TYPE = #{type} ")
    List<ResourceRoleDO> selectResourceAndRole(Integer type);

}
