package com.farukgenc.boilerplate.springboot.security.utils;

import com.farukgenc.boilerplate.springboot.model.CustomUserDetails;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@UtilityClass
public class UserDetailUtils {
    public static CustomUserDetails getUserDetailsByToken() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }
}
