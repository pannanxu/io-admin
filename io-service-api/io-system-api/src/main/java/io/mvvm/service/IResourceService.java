package io.mvvm.service;

import io.mvvm.common.mybatis.BaseService;
import io.mvvm.model.domain.ResourceTab;
import io.mvvm.model.vo.ResourceVO;

import java.util.List;

/**
 * @program: io-admin
 * @description: 资源业务
 * @author: Mr. Pan
 * @create: 2021-06-11 20:48
 **/
public interface IResourceService extends BaseService<ResourceTab> {

    /**
     * 按照类型查询资源列表
     * @param type  类型
     * @return      资源列表
     */
    List<ResourceVO> getResourceListByType(int type);

}
