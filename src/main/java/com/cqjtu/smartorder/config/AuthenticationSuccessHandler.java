package com.cqjtu.smartorder.config;

import com.cqjtu.smartorder.api.UserService;
import com.cqjtu.smartorder.api.model.User;
import com.cqjtu.smartorder.global.RoleEnum;
import org.apache.catalina.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Optional;

/**
 * @author mumu
 * @date 2020/7/25
 */
public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws ServletException, IOException {

        logger.info(authentication.getName());
        logger.info(authentication.getAuthorities());

        request.getSession().setAttribute("username", authentication.getName());

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if (authorities.size() != 1) {
            logger.info("authority is zero or not only one");
            return;
        }
        String authority = ((GrantedAuthority) authorities.toArray()[0]).getAuthority();
        request.getSession().setAttribute("role", authority);
        logger.info(authority);
        response.sendRedirect("/");
    }

}

