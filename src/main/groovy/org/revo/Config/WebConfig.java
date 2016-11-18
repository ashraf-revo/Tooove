package org.revo.Config;

import org.revo.Service.ActiveUserService;
import org.revo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ashraf on 18/11/16.
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
    @Autowired
    private ActiveUserService activeUserService;
    @Autowired
    @Lazy
    private UserService userService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptorAdapter() {
            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
                activeUserService.mark(userService.current().getUsername());
            }
        }).addPathPatterns("/api/message/*/**", "/api/user/*/**");
    }
}