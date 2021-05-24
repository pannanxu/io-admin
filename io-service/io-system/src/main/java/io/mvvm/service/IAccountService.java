package io.mvvm.service;

import io.mvvm.entity.domain.AccountTab;

import java.util.List;

/**
 * @program: io-admin
 * @description: IUSerAccountService
 * @author: Mr. Pan
 * @create: 2021-05-23 21:01
 **/
public interface IAccountService {

    List<AccountTab> getAccountList();

    AccountTab getAccountById(String id);

    int saveAccount(AccountTab tab);

}
