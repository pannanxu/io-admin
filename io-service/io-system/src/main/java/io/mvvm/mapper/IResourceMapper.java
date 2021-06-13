package io.mvvm.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
     * @param type  资源类型(0:API,1:菜单)
     * @param roles 角色列表
     * @return      {@link List<ResourceTab>}
     */
    List<ResourceTab> selectResourceListByType(@Param("type") int type, @Param("roles") Set<String> roles);

}
