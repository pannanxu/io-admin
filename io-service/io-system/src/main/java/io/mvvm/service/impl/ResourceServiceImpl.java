package io.mvvm.service.impl;

import io.mvvm.common.mybatis.BaseServiceImpl;
import io.mvvm.mapper.IResourceMapper;
import io.mvvm.model.JwtStoreUserDetailsDTO;
import io.mvvm.model.conver.ResourceConverter;
import io.mvvm.model.domain.ResourceTab;
import io.mvvm.model.vo.PermissionVO;
import io.mvvm.model.vo.ResourceVO;
import io.mvvm.service.IResourceService;
import io.mvvm.utils.WebSecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: io-admin
 * @description:
 * @author: 潘南旭
 * @create: 2021-06-11 21:12
 **/
@Service
public class ResourceServiceImpl extends BaseServiceImpl<IResourceMapper, ResourceTab> implements IResourceService {

    @Override
    public List<PermissionVO> getPermissionsByUserRole() {
        JwtStoreUserDetailsDTO userDetails = WebSecurityContextHolder.getUserDetails();
        List<ResourceTab> tabs = super.baseMapper.selectResourceListByType(-1, userDetails.getRoles());
        return ResourceConverter.INSTANCE.converterPermission(tabs);
    }
}
