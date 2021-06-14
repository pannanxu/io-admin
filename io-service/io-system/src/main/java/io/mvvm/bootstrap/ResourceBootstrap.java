package io.mvvm.bootstrap;

import io.mvvm.common.constants.RedisConstants;
import io.mvvm.enums.MethodEnum;
import io.mvvm.mapper.IResourceMapper;
import io.mvvm.model.domain.ResourceRoleDO;
import io.mvvm.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @program: io-admin
 * @description: 资源启动器
 * @author: 潘南旭
 * @create: 2021-06-13 20:45
 **/
@Slf4j
@Component
public class ResourceBootstrap implements IBootstrap {

    @Resource
    private IResourceMapper resourceMapper;
    @Resource
    private RedisUtil redisUtil;

    @Override
    public int order() {
        return 0;
    }

    @Override
    public void start(String... args) {
        List<ResourceRoleDO> apis = resourceMapper.selectResourceAndRole(0);
        apis.stream()
                .collect(Collectors.groupingBy(ResourceRoleDO::getUri))
                .forEach(
                        (key, value) -> apis.stream()
                                .filter(e -> e.getUri().equals(key))
                                .findFirst()
                                .ifPresent(
                                        rdo -> redisUtil.listPushLeft(
                                                RedisConstants.append(
                                                        RedisConstants.RESOURCE_URI_ROLE_API,
                                                        MethodEnum.getType(rdo.getMethod()),
                                                        StringUtils.replace(
                                                                key.substring(1),
                                                                "/",
                                                                ":")
                                                ),
                                                value.stream()
                                                        .map(ResourceRoleDO::getRoleName)
                                                        .collect(Collectors.toSet())
                                        )
                                )
                );
    }

    @Override
    public void shutdown() {

    }
}
