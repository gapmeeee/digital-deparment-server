package com.example.demo.moduls.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_USER, ROLE_ADMIN,ROLE_TEACH;

    @Override
    public String getAuthority() {
        return name();
    }
}
