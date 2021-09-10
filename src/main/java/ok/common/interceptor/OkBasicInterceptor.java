package ok.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import ok.common.context.RequestContextHolder;
import ok.common.type.OkMDCType;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class OkBasicInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        try {
            HandlerMethod methodHandler = (HandlerMethod) handler;
            String serviceId = methodHandler.getMethod().getDeclaringClass().getName() + methodHandler.getMethod().getName();

            MDC.put(OkMDCType.SERVICE_ID.getMdcName(), serviceId);
            RequestContextHolder.getRequestContext().setServiceId(serviceId);
        } catch (RuntimeException e ) {

        }

        return true;
    }


}
