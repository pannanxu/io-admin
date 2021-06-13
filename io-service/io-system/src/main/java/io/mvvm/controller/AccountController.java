package io.mvvm.controller;

import io.mvvm.model.Ret;
import io.mvvm.model.dto.AccountDTO;
import io.mvvm.model.vo.AccountVO;
import io.mvvm.service.IAccountService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * @program: io-admin
 * @description:
 * @author: 潘南旭
 * @create: 2021-05-31 20:09
 **/
@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private IAccountService accountService;

    @GetMapping("/{id}")
    public Ret<AccountVO> getAccountById(@PathVariable Long id) {
        AccountVO account = accountService.getAccountById(id);
        return Ret.success(account);
    }

    @GetMapping
    public Ret<List<AccountVO>> getAccount() {
        List<AccountVO> accounts = accountService.getAccount();
        return Ret.success(accounts);
    }

    @PutMapping
    public Ret<Boolean> save(@Valid @RequestBody AccountDTO dto) {
        boolean result = accountService.addAccount(dto);
        return Ret.result(result);
    }

    @DeleteMapping("/{id}")
    public Ret<Boolean> delete(@PathVariable Long id) {
        boolean result = accountService.removeById(id);
        return Ret.result(result);
    }

}
