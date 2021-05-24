package io.mvvm.service.impl;

import io.mvvm.entity.domain.AccountTab;
import io.mvvm.mapper.IAccountMapper;
import io.mvvm.service.IAccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: io-admin
 * @description: UserAccountService
 * @author: Mr. Pan
 * @create: 2021-05-23 21:02
 **/
@Service
public class AccountService implements IAccountService {

    @Resource
    private IAccountMapper accountMapper;

    @Override
    public List<AccountTab> getAccountList() {
        return accountMapper.selectList(null);
    }

    @Override
    public AccountTab getAccountById(String id) {
        return accountMapper.selectById(id);
    }

    @Override
    public int saveAccount(AccountTab tab) {
        return accountMapper.insert(tab);
    }

}
