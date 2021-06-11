package io.mvvm.service.impl;

import io.mvvm.common.mybatis.BaseServiceImpl;
import io.mvvm.mapper.IResourceMapper;
import io.mvvm.model.JwtStoreUserDetailsDTO;
import io.mvvm.model.conver.ResourceConverter;
import io.mvvm.model.domain.ResourceTab;
import io.mvvm.model.vo.ResourceVO;
import io.mvvm.service.IResourceService;
import io.mvvm.utils.WebSecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @program: io-admin
 * @description:
 * @author: Mr. Pan
 * @create: 2021-06-11 21:12
 **/
@Service
public class ResourceServiceImpl extends BaseServiceImpl<IResourceMapper, ResourceTab> implements IResourceService {

    @Override
    public List<ResourceVO> getResourceListByType(int type) {
        JwtStoreUserDetailsDTO userDetails = WebSecurityContextHolder.getUserDetails();
        List<ResourceTab> tabs = super.baseMapper.selectResourceListByType(type, userDetails.getRoles());
        return ResourceConverter.INSTANCE.converter(tabs);
    }
}
