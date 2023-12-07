package bg.softuni.servicesvratsa.utils.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (request.isUserInRole("ROLE_CLIENT") || request.isUserInRole("ROLE_WORKER")) {
            response.sendRedirect("/");
            return false;
        }
        return true;
    }
}
