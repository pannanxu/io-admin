package io.mvvm.controller;

import io.mvvm.common.controller.BaseController;
import io.mvvm.entity.Ret;
import io.mvvm.entity.dto.AccountDTO;
import io.mvvm.entity.vo.AccountVO;
import io.mvvm.service.IAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: io-admin
 * @description: UserAccountController
 * @author: Mr. Pan
 * @create: 2021-05-23 21:07
 **/
@Slf4j
@RestController
@RequestMapping("/user/account")
public class AccountController extends BaseController {

    @Resource
    private IAccountService accountService;

    @GetMapping("/{id}")
    public Ret getAccountById(@PathVariable Long id) {
        AccountDTO dto = accountService.getAccountById(id);
        return Ret.okData(dto);
    }

    @GetMapping("/list")
    public Ret getAccountList() {
        List<AccountDTO> dto = accountService.getAccountList();
        return Ret.okData(dto);
    }

    @PutMapping
    public Ret save(@RequestBody AccountVO vo) {
        AccountDTO dto = accountService.save(vo);
        return Ret.okData(dto);
    }
}
