package ok.joy.learn.config;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThreadPoolConfiguration {

	
	
	
	@Bean
	public ThreadPoolExecutor threadPoolExecutor() {
		System.out.println("비동기 처리 ");
	LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<>(9);
		ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 100, 5, TimeUnit.MINUTES, queue);
		
		return threadPoolExecutor; 
	}
}
