package ok.common.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ok.practice.timer.MyTimer;

@Configuration
public class ThreadPoolConfiguration {

	
	@Bean
	public ExecutorService threadPoolExecutor() {
		System.out.println("비동기 처리 ");
		ExecutorService threadPoolExecutor = Executors.newFixedThreadPool(30);
		
		return threadPoolExecutor; 
	}
	
	@Bean
	public MyTimer myTimer() {
		MyTimer timer = new MyTimer();
		return timer;
	}
}
