package io.mvvm.service;

import io.mvvm.common.mybatis.BaseService;
import io.mvvm.entity.domain.AccountTab;
import io.mvvm.entity.dto.AccountDTO;
import io.mvvm.entity.vo.AccountVO;

import java.util.List;

/**
 * @program: io-admin
 * @description: IUSerAccountService
 * @author: Mr. Pan
 * @create: 2021-05-23 21:01
 **/
public interface IAccountService extends BaseService<AccountTab> {

    /**
     * 查询账户详情
     *
     * @param id ID
     * @return 账户详细信息
     */
    AccountDTO getAccountById(Long id);

    /**
     * 查询账户列表
     *
     * @return 账户列表信息
     */
    List<AccountDTO> getAccountList();

    /**
     * 添加账户信息
     * @param vo    账户信息
     * @return      添加成功后返回该账户详细信息
     */
    AccountDTO save(AccountVO vo);


}
