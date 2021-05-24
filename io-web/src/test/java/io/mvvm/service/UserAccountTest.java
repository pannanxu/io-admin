package io.mvvm.service;

import io.mvvm.AdminApplication;
import io.mvvm.entity.domain.AccountTab;
import io.mvvm.mapper.IAccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @program: io-admin
 * @description: 账户测试
 * @author: Mr. Pan
 * @create: 2021-05-24 19:58
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AdminApplication.class)
public class UserAccountTest {
/*

    @Resource
    private IAccountService accountService;
    @Resource
    private IAccountMapper iAccountMapper;
    private Faker faker = new Faker(Locale.CHINA);

    @Test
    public void getAccountListTest() {
        List<AccountTab> accountList = accountService.getAccountList();
        System.out.println(accountList);
    }

    @Test
    public void saveAccount() {
        long l = System.currentTimeMillis();
        long phone = 20000299999L;
        for (int j = 0; j < 70; j++) {
            List<AccountTab> tabs = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                phone+=1;
                long p = phone;
                String s = String.valueOf(p);
                AccountTab build = AccountTab.builder().username(s)
                        .password(s)
                        .phone(s)
                        .sourceFrom(0).version(1).status(0)
                        .createTime(1621860929)
                        .createIp("127.0.0.1").isDel(0).build();
                tabs.add(build);
            }
            try {
                iAccountMapper.insert(tabs);
            } catch (Exception e) {
                System.out.println("...");
            }
        }
        long l1 = System.currentTimeMillis();
        Integer integer = iAccountMapper.selectCount(null);
        System.out.println("总条数：" + integer);
        System.out.println("使用时间：" + (l1 - l));
    }
*/

}
