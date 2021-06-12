package io.mvvm.handler;

import com.alibaba.fastjson.JSONObject;
import io.mvvm.mapper.IUserAccountMapper;
import io.mvvm.model.JwtStoreUserDetailsDTO;
import io.mvvm.model.UserAccountDetails;
import io.mvvm.utils.ConvertUtil;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @program: io-admin
 * @description: 用户信息认证器
 * @author: Mr. Pan
 * @create: 2021-06-12 17:06
 **/
@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

    @Resource
    private IUserAccountMapper iUserAccountMapper;
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = ConvertUtil.parseString(authentication.getPrincipal());
        String password = ConvertUtil.parseString(authentication.getCredentials());

        if ("".equals(username)) {
            throw new BadCredentialsException("用户名不能为空");
        }
        if ("".equals(password)) {
            throw new BadCredentialsException("密码不能为空");
        }

        UserAccountDetails user = iUserAccountMapper.selectUserAccountByUserName(username);

        if (null == user) {
            throw new BadCredentialsException("用户名不存在");
        }

        boolean matches = passwordEncoder.matches(password, user.getPassword());
        if (!matches) {
            throw new BadCredentialsException("用户名或密码不正确");
        }

        if (!user.isEnabled()) {
            throw new BadCredentialsException("用户不可用");
        }

        if (!user.isAccountNonExpired()) {
            throw new BadCredentialsException("账户已过期");
        }

        if (!user.isAccountNonLocked()) {
            throw new BadCredentialsException("账户已锁定");
        }

        if (!user.isCredentialsNonExpired()) {
            throw new BadCredentialsException("凭证已过期");
        }

        Set<String> roles = iUserAccountMapper.selectRoleByAccountId(user.getId());

        if (null == roles || roles.isEmpty()) {
            throw new BadCredentialsException("该用户无权限");
        }

        JwtStoreUserDetailsDTO dto = new JwtStoreUserDetailsDTO();
        dto.setUsername(username);
        dto.setId(user.getId());
        dto.setRoles(roles);

        AuthenticationTokenImpl result = new AuthenticationTokenImpl(dto,
                null,
                dto.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet()));
        result.setDetails(authentication.getDetails());
        return result;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (AuthenticationTokenImpl.class.isAssignableFrom(authentication));
    }

}
