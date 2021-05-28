package io.mvvm.service.impl;

import io.mvvm.common.mybatis.BaseServiceImpl;
import io.mvvm.entity.domain.AccountTab;
import io.mvvm.entity.dto.AccountDTO;
import io.mvvm.entity.vo.AccountVO;
import io.mvvm.mapper.IAccountMapper;
import io.mvvm.service.IAccountService;
import io.mvvm.utils.DateUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: io-admin
 * @description: UserAccountService
 * @author: Mr. Pan
 * @create: 2021-05-23 21:02
 **/
@Service
public class AccountServiceImpl extends BaseServiceImpl<IAccountMapper, AccountTab> implements IAccountService {

    @Override
    public AccountDTO getAccountById(Long id) {
        AccountTab tab = super.getById(id);
        AccountDTO dto = new AccountDTO();
        BeanUtils.copyProperties(tab, dto);
        return dto;
    }

    @Override
    public List<AccountDTO> getAccountList() {
        List<AccountTab> list = super.list();
        List<AccountDTO> dto = new ArrayList<>();
        BeanUtils.copyProperties(list, dto);
        return dto;
    }

    @Override
    public AccountDTO save(AccountVO vo) {
        AccountTab tab = AccountTab.builder().username(vo.getUsername())
                .password(vo.getPassword())
                .email(vo.getEmail())
                .phone(vo.getPhone())
                .sourceFrom(0)
                .version(1)
                .status(0)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .createTime(DateUtil.getSecondTimestampTwo())
                .createIp("127.0.0.1")
                .lastLoginIp("")
                .lastLoginTime(0)
                .loginCount(0)
                .isDel(0).build();
        boolean result = super.save(tab);
        return result ? getAccountById(tab.getId()) : null;
    }
}
