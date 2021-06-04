package io.mvvm.service;

import io.mvvm.common.mybatis.BaseService;
import io.mvvm.model.domain.AccountTab;
import io.mvvm.model.dto.AccountDTO;
import io.mvvm.model.vo.AccountVO;

import java.util.List;

/**
 * @program: io-admin
 * @description: 账户业务
 * @author: Mr. Pan
 * @create: 2021-05-31 20:10
 **/
public interface IAccountService extends BaseService<AccountTab> {

    /**
     * 按照ID查询账户信息
     *
     * @param id ID
     * @return {@link AccountVO}
     */
    AccountVO getAccountById(Long id);

    /**
     * 查询账户信息列表
     *
     * @return {@link List<AccountVO>}
     */
    List<AccountVO> getAccount();

    /**
     * 注册账户信息
     *
     * @param dto 账户信息
     * @return {@link Boolean}
     */
    boolean addAccount(AccountDTO dto);

}
