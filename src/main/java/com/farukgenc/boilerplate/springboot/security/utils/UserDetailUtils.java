package com.farukgenc.boilerplate.springboot.security.utils;

import com.farukgenc.boilerplate.springboot.model.CustomUserDetails;
import com.farukgenc.boilerplate.springboot.model.User;
import com.farukgenc.boilerplate.springboot.security.mapper.UserMapper;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

@UtilityClass
public class UserDetailUtils {
    public static CustomUserDetails getUserDetailsByToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }
}
