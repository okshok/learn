package ok.common.filter;

import lombok.extern.slf4j.Slf4j;
import ok.common.context.RequestContext;
import ok.common.context.RequestContextHolder;
import ok.common.type.OkMDCType;
import org.slf4j.MDC;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/*
* 리퀘스트 스레드에서 공통으로 사용하는 요소들을 관리
* */
@Slf4j
public class RequestContextFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        Long userId = 0l;
        try {
//            GuhadaUserDetails decodedDetails = (GuhadaUserDetails) ((OAuth2AuthenticationDetails) SecurityContextHolder.getContext().getAuthentication().getDetails()).getDecodedDetails();
//            userId = decodedDetails.getUserId();
        } catch (Exception ex) {
            //로그인하지 않은 사용자, 기본아이디값은 0
        }
        String globalId = Optional.ofNullable(((HttpServletRequest) request).getHeader("global-id")).orElse(UUID.randomUUID().toString());

        RequestContext context = RequestContext.builder()
                .userId(userId)
                .globalId(globalId)
                .build();
        RequestContextHolder.setRequestContext(context);

        MDC.put(OkMDCType.GLOBAL_ID.getMdcName(), globalId);
        MDC.put(OkMDCType.USER_ID.getMdcName(), context.getUserId().toString());


        chain.doFilter(request, response);


        MDC.clear();
        RequestContextHolder.resetRequestContext();
    }


}
