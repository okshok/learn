package ok.common.context;

import org.springframework.core.NamedThreadLocal;

/**
 * 요청정보를 담고 있는 객체
 * */
public class RequestContextHolder {

    private RequestContextHolder() {}
    private static final ThreadLocal<RequestContext> requestAttributesHolder = new NamedThreadLocal<>("GuhadaRequestContextHolder");


    public static void setRequestContext(RequestContext RequestContext) {
        requestAttributesHolder.set(RequestContext);
    }
    public static RequestContext getRequestContext() {
        return requestAttributesHolder.get();
    }

    public static void resetRequestContext() {
        requestAttributesHolder.remove();
    }
}
