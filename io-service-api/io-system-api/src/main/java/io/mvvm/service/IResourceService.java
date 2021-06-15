package io.mvvm.service;

import io.mvvm.common.mybatis.BaseService;
import io.mvvm.model.domain.ResourceTab;
import io.mvvm.model.vo.PermissionVO;

import java.util.List;

/**
 * @program: io-admin
 * @description: 资源业务
 * @author: 潘南旭
 * @create: 2021-06-11 20:48
 **/
public interface IResourceService extends BaseService<ResourceTab> {

    /**
     * 按照角色查询所拥有的资源
     * @return  {@link List<PermissionVO>}
     */
    List<PermissionVO> getPermissionsByUserRole();
}
