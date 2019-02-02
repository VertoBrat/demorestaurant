package ru.photorex.demorestaurant.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import static ru.photorex.demorestaurant.domain.User.Role.ROLE_ADMIN;
import static ru.photorex.demorestaurant.domain.User.Role.ROLE_ANONYMOUS;

public class AccessUtil {
    public static boolean hasAccessToVote() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    }

    public static boolean hasAccessToModify() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().contains(ROLE_ADMIN);
    }
}
