package com.nowcoder.community.controller.interceptor;

import com.nowcoder.community.annotation.LoginRequired;
import com.nowcoder.community.util.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

@Component
public class LoginRequiredInterceptor implements HandlerInterceptor {
    @Autowired
    private HostHolder hostHolder;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 拦截器拦截到方法时，handler类型会是HandlerMethod
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            // 获取拦截到的方法对象
            Method method = handlerMethod.getMethod();
            // 取该对象的注解
            LoginRequired loginRequired = method.getAnnotation(LoginRequired.class);
            // 该操作需要登录 但当前没有登录
            if (loginRequired != null && hostHolder.getUser() == null) {
                // 重定向到登录页面
                response.sendRedirect(request.getContextPath() + "/login");
                // 返回值为false DispatcherServlet 假定此拦截器已经处理了响应本身
                return false;
            }
        }
        // 返回值为true 执行链继续下一个拦截器或处理程序本身
        return true;
    }
}
