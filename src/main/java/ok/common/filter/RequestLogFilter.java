package ok.common.filter;

import lombok.extern.slf4j.Slf4j;
import ok.common.type.OkMarkerType;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

@Slf4j
public class RequestLogFilter extends GenericFilterBean {


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        log.info(OkMarkerType.REQUEST_BEGIN.getMarker(), "Request Log");

        chain.doFilter(request, response);

        long elapsedTimeMills = System.currentTimeMillis()-startTime;
        log.info(OkMarkerType.RESPONSE_END.getMarker(), "[{} ms]", elapsedTimeMills );

    }


}
