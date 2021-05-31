package io.mvvm.service.impl;

import io.mvvm.common.mybatis.BaseServiceImpl;
import io.mvvm.entity.domain.AccountTab;
import io.mvvm.entity.dto.AccountDTO;
import io.mvvm.entity.vo.AccountVO;
import io.mvvm.mapper.IAccountMapper;
import io.mvvm.service.IAccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: io-admin
 * @description:
 * @author: Mr. Pan
 * @create: 2021-05-31 20:09
 **/
@Service
public class AccountServiceImpl extends BaseServiceImpl<IAccountMapper, AccountTab> implements IAccountService {

    @Override
    public AccountVO getAccountById(Long id) {
        AccountTab account = super.getById(id);
        AccountVO vo = new AccountVO();
        BeanUtils.copyProperties(account, vo);
        return vo;
    }

    @Override
    public List<AccountVO> getAccount() {
        List<AccountTab> accounts = super.list();
        List<AccountVO> vos = new ArrayList<>();
        BeanUtils.copyProperties(accounts, vos);
        return vos;
    }

    @Override
    public boolean addAccount(AccountDTO dto) {
        AccountTab tab = new AccountTab();
        BeanUtils.copyProperties(dto, tab);
        return super.save(tab);
    }
}
