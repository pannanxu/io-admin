package io.mvvm.model.conver;

import io.mvvm.model.domain.AccountTab;
import io.mvvm.model.dto.AccountDTO;
import io.mvvm.model.vo.AccountVO;

import java.util.List;

/**
 * @program: io-admin
 * @description: 账户类型转换
 * @author: Mr. Pan
 * @create: 2021-06-05 21:04
 **/
@org.mapstruct.Mapper
public interface AccountConverter {

    AccountConverter INSTANCE = org.mapstruct.factory.Mappers.getMapper(AccountConverter.class);

    /**
     * 类型转换
     * @param tab {@link AccountTab}
     * @return    {@link AccountVO}
     */
    AccountVO converter(AccountTab tab);

    /**
     * 类型转换
     * @param tabs {@link AccountTab}
     * @return    {@link AccountVO}
     */
    List<AccountVO> converter(List<AccountTab> tabs);

    /**
     * 类型转换
     * @param dto {@link AccountDTO}
     * @return    {@link AccountTab}
     */
    AccountTab converter(AccountDTO dto);

}
