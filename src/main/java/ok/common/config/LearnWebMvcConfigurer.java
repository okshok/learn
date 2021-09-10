package ok.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import ok.common.interceptor.OkBasicInterceptor;
import ok.common.support.LearnHandlerMethodReturnValueHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class LearnWebMvcConfigurer implements WebMvcConfigurer {

    private final ObjectMapper objectMapper;

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> handlers) {
        handlers.add(learnHandlerMethodReturnValueHandler(objectMapper));
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(okBasicInterceptor());
    }

    @Bean
    public HandlerMethodReturnValueHandler learnHandlerMethodReturnValueHandler(ObjectMapper objectMapper) {
       return new LearnHandlerMethodReturnValueHandler(objectMapper);
    }

    @Bean
    public HandlerInterceptor okBasicInterceptor() {
        return new OkBasicInterceptor();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        builder.requestFactory(() -> {
            CloseableHttpClient httpClient = HttpClientBuilder.create()
                    .setMaxConnTotal(100) // connection pool 적용 .
                    .setMaxConnPerRoute(5) // connection pool 적용
                    .build();
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setReadTimeout(15_000); // 읽기시간초과, ms
            factory.setConnectTimeout(10_000);  // 연결시간초과, ms
            factory.setConnectionRequestTimeout(10_000);
            factory.setHttpClient(httpClient);
            return factory;
        });

        return builder.build();
    }
}
