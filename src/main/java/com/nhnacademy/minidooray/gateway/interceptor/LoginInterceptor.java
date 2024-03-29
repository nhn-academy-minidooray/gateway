package com.nhnacademy.minidooray.gateway.interceptor;

import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession();
        if(Objects.isNull(session) || Objects.isNull(session.getAttribute("ACCOUNT_ID"))) {
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }
}
