package com.cqjtu.smartorder.config;

import com.cqjtu.smartorder.exception.CaptchaException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author mumu
 * @date 2020/7/28
 */
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        if ("/api/login".equals(request.getRequestURI())
                && "post".equalsIgnoreCase(request.getMethod())) {
            try {
                validate(request);
            } catch (CaptchaException e) {
                response.sendRedirect("/login?error");
                return;
            }
        }
        // 3. 校验通过，就放行
        filterChain.doFilter(request, response);
    }

    /* 验证保存在session的验证码和表单提交的验证码是否一致 */
    private void validate(HttpServletRequest request) throws ServletRequestBindingException, CaptchaException {
        String captcha = ServletRequestUtils.getStringParameter(request, "captcha");
        String code = (String) request.getSession().getAttribute(request.getParameter("uuid"));
        logger.info("获取提交的code: " + captcha);
        logger.info("获取保存的code: " + code);
        if (code == null || !code.equalsIgnoreCase(captcha)) {
            throw new CaptchaException("验证码不正确！");
        }
        request.getSession().removeAttribute(request.getParameter("uuid"));
    }
}
