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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            final String authToken = authHeader.substring(TOKEN_PREFIX_LENGTH);
            UserAccountDetails details = TokenUtil.parseToken(authToken);
            if (details != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                createAuthentication(details, request);
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
