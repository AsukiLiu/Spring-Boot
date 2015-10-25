package org.asuki.springboot.component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

@Component
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
    private static final Logger log = LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        log.error("{}: {}", ex.getClass(), ex.getMessage());

        if (isRestController(handler)) {
            return null;
        }

        ModelAndView mv = new ModelAndView();
        mv.setViewName("error");

        return mv;
    }

    private boolean isRestController(Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;

            return method.getMethod().getDeclaringClass().isAnnotationPresent(RestController.class);
        }

        return false;
    }
}
