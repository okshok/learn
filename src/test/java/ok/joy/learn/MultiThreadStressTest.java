package ok.joy.learn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultiThreadStressTest {

	
	public static void main(String[] args) {
		ExecutorService executorService	 = Executors.newCachedThreadPool();
		
		MultiThreading threading = new MultiThreading();
		threading.doInMultiThread(executorService, 10);
		
		executorService.shutdown();
	}

}
