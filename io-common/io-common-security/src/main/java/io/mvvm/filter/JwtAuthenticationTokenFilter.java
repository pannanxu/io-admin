package io.mvvm.filter;

import io.mvvm.handler.AuthenticationTokenImpl;
import io.mvvm.model.JwtStoreUserDetailsDTO;
import io.mvvm.model.UserAccountDetails;
import io.mvvm.utils.ConvertUtil;
import io.mvvm.utils.TokenUtil;
import io.mvvm.utils.WebSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: io-admin
 * @description: Token 认证过滤器
 * @author: 潘南旭
 * @create: 2021-06-02 23:11
 **/
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final int TOKEN_PREFIX_LENGTH = TOKEN_PREFIX.length();
    private static final String TOKEN_SPLIT = "\\.";
    private static final int TOKEN_SPLIT_LENGTH = 3;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            final String authToken = authHeader.substring(TOKEN_PREFIX_LENGTH);
            if (!ConvertUtil.EMPTY.equals(authToken) && authToken.split(TOKEN_SPLIT).length == TOKEN_SPLIT_LENGTH) {
                JwtStoreUserDetailsDTO details = TokenUtil.parseToken(authToken);
                if (details != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    createAuthentication(TokenUtil.transform(details), request);
                }
            }
        }
        chain.doFilter(request, response);
    }

    private void createAuthentication(UserAccountDetails userDetails, HttpServletRequest request) {
        AuthenticationTokenImpl authentication =
                new AuthenticationTokenImpl(userDetails, userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        WebSecurityContextHolder.setAuthentication(authentication);
    }
}
