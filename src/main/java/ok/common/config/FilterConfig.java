package ok.common.config;

import ch.qos.logback.classic.turbo.MDCFilter;
import ok.common.filter.RequestContextFilter;
import ok.common.filter.RequestLogFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;

/*
* 필터 설정을 관리
* */
@Configuration
public class FilterConfig {

    @Bean
    @Order(100)
    public Filter RequestContextFilter() {
        Filter filter = new RequestContextFilter();
        return filter;
    }


    @Bean
    @Order(300)
    public Filter RequestLogFilter() {
        Filter filter = new RequestLogFilter();
        return filter;
    }

}
