package io.mvvm.handler;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.SpringSecurityCoreVersion;

import java.util.Collection;

/**
 * @program: io-admin
 * @description: 账号密码，用户信息认证凭据
 * @author: 潘南旭
 * @create: 2021-06-12 16:58
 **/
public class AuthenticationTokenImpl extends AbstractAuthenticationToken {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    private final Object principal;
    private final String credentials;
    private String type;
    private Collection<? extends GrantedAuthority> authorities;

    public AuthenticationTokenImpl(Object principal, String credentials) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.type = "common";
        setAuthenticated(false);
    }

    public AuthenticationTokenImpl(Object principal, String credentials, String type) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.type = null == type || "".equals(type) ? "common" : type;
        setAuthenticated(false);
    }

    public AuthenticationTokenImpl(Object principal, String credentials, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        setAuthenticated(false);
    }

    public AuthenticationTokenImpl(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = null;
        // must use super, as we override
        super.setAuthenticated(true);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        super.setAuthenticated(isAuthenticated);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
