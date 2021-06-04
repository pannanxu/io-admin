package io.mvvm.filter;

import io.mvvm.model.UserAccountDetails;
import io.mvvm.mapper.IUserAccountMapper;
import io.mvvm.utils.TokenUtil;
import io.mvvm.utils.WebSecurityContextHolder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @program: io-admin
 * @description: Token 认证过滤器
 * @author: Mr. Pan
 * @create: 2021-06-02 23:11
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final int TOKEN_PREFIX_LENGTH = TOKEN_PREFIX.length();

    @Resource
    private IUserAccountMapper iUserAccountMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        UserAccountDetails userDetails;
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            final String authToken = authHeader.substring(TOKEN_PREFIX_LENGTH);

            String username = TokenUtil.parseToken(authToken);

            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                // 查询用户信息
                // TODO 后面将角色信息存入jwt中，避免多次查询数据库
                userDetails = iUserAccountMapper.selectUserAccountByUserName(username);

                if (userDetails != null) {
                    // 给用户写入角色，先写死，后面从jwt里取
                    Set<GrantedAuthority> roles = new HashSet<>();
                    roles.add(new SimpleGrantedAuthority("root1"));
                    userDetails.setAuthorities(roles);
                    createAuthentication(userDetails, request);
                }
            }
        }
        chain.doFilter(request, response);
    }

    private void createAuthentication(UserAccountDetails userDetails, HttpServletRequest request) {
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        WebSecurityContextHolder.setAuthentication(authentication);
    }
}
