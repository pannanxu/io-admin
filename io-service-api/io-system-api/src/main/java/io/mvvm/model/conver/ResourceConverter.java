package io.mvvm.model.conver;

import io.mvvm.model.domain.ResourceTab;
import io.mvvm.model.vo.ResourceVO;

import java.util.List;

/**
 * @program: io-admin
 * @description: 资源类型转换
 * @author: 潘南旭
 * @create: 2021-06-11 21:14
 **/
@org.mapstruct.Mapper
public interface ResourceConverter {

    ResourceConverter INSTANCE = org.mapstruct.factory.Mappers.getMapper(ResourceConverter.class);

    /**
     * 类型转换
     * @param tab  {@link ResourceTab}
     * @return      {@link ResourceVO}
     */
    ResourceVO converter(ResourceTab tab);

    /**
     * 类型转换
     * @param tabs  {@link List<ResourceTab>}
     * @return      {@link List<ResourceVO>}
     */
    List<ResourceVO> converter(List<ResourceTab> tabs);

}
