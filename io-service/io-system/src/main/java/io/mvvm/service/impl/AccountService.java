package io.mvvm.service.impl;

import io.mvvm.common.mybatis.BaseServiceImpl;
import io.mvvm.entity.domain.AccountTab;
import io.mvvm.mapper.IAccountMapper;
import io.mvvm.service.IAccountService;
import org.springframework.stereotype.Service;

/**
 * @program: io-admin
 * @description: UserAccountService
 * @author: Mr. Pan
 * @create: 2021-05-23 21:02
 **/
@Service
public class AccountService extends BaseServiceImpl<IAccountMapper, AccountTab> implements IAccountService {

}
