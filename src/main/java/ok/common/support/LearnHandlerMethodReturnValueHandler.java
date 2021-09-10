package ok.common.support;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ok.common.annotation.OkJsonResponse;
import ok.common.messsage.DefaultMessageSupport;
import ok.common.messsage.OkDefaultMessage;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Objects;

@RequiredArgsConstructor
public class LearnHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {


    private final ObjectMapper objectMapper;

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        Method method = returnType.getMethod();
        Annotation annotation = method.getDeclaredAnnotation(OkJsonResponse.class);

        if (Objects.nonNull(annotation)) {
            return true;
        }
        return false;
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType, ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        HttpServletResponse response = webRequest.getNativeResponse(HttpServletResponse.class);
        mavContainer.setRequestHandled(true);  //다음 핸들러를 찾지 않음

        setHeaders(response);
        String jsonValue = getJsonBody(returnValue);

        response.getWriter().write(jsonValue);
    }

    private String getJsonBody(Object returnValue) throws JsonProcessingException {
        OkDefaultMessage message = DefaultMessageSupport.getDefaultMessage(returnValue);

        return objectMapper.writeValueAsString(message);
    }

    private void setHeaders(HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
    }

}
