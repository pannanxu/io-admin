package io.mvvm.service.impl;

import io.mvvm.common.mybatis.BaseServiceImpl;
import io.mvvm.model.conver.AccountConverter;
import io.mvvm.model.domain.AccountTab;
import io.mvvm.model.dto.AccountDTO;
import io.mvvm.model.vo.AccountVO;
import io.mvvm.mapper.IAccountMapper;
import io.mvvm.service.IAccountService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @program: io-admin
 * @description:
 * @author: 潘南旭
 * @create: 2021-05-31 20:09
 **/
@Service
public class AccountServiceImpl extends BaseServiceImpl<IAccountMapper, AccountTab> implements IAccountService {

    @Override
    public AccountVO getAccountById(Long id) {
        AccountTab account = super.getById(id);
        return AccountConverter.INSTANCE.converter(account);
    }

    @Override
    public List<AccountVO> getAccount() {
        List<AccountTab> accounts = super.list();
        return AccountConverter.INSTANCE.converter(accounts);
    }

    @Override
    public boolean addAccount(AccountDTO dto) {
        AccountTab tab = AccountConverter.INSTANCE.converter(dto);
        return super.save(tab);
    }
}
