package bg.softuni.servicesvratsa.config;

import bg.softuni.servicesvratsa.utils.interceptor.LoginInterceptor;
import bg.softuni.servicesvratsa.utils.interceptor.RegisterInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {

    private final RegisterInterceptor registerInterceptor;
    private final LoginInterceptor loginInterceptor;

    public InterceptorConfiguration(RegisterInterceptor registerInterceptor, LoginInterceptor loginInterceptor) {
        this.registerInterceptor = registerInterceptor;
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(registerInterceptor)
                .addPathPatterns("/users/register");

        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/users/login");
    }
}
