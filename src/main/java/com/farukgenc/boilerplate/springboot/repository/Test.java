package com.farukgenc.boilerplate.springboot.repository;

import com.farukgenc.boilerplate.springboot.model.CustomUserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class Test extends User implements CustomUserDetails {
    private UUID userId;

    public Test(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    @Override
    public UUID getUserId() {
        return this.userId;
    }

    @Override
    public void setUserId(UUID userId) {
        this.userId = userId;
    }

}
