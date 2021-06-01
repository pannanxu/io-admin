package io.mvvm.service;

import io.mvvm.entity.UserAccountDetails;
import io.mvvm.mapper.IUserAccountMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @program: io-admin
 * @description:
 * @author: Mr. Pan
 * @create: 2021-06-02 00:33
 **/
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private IUserAccountMapper iUserAccountMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (null == username || "".equals(username)) {
            throw new UsernameNotFoundException("用户名不能为空");
        }
        UserAccountDetails details = iUserAccountMapper.selectUserAccountByUserName(username);
        if (null == details) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return details;
    }
}
