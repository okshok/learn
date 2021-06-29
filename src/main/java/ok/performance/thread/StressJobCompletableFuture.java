package ok.performance.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ok.performance.thread.param.Range;

public class StressJobCompletableFuture implements Callable<CompletableFuture<String>>{
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	public CompletableFuture<String> newFuture = new CompletableFuture<>();
	public Range range = null;

	@Override
	public CompletableFuture<String>  call() throws Exception {
		log.debug("start job");
		Stream.iterate(range.lower, i -> i + 1).limit(range.upper)
			.forEach(i -> {
				tidiousJob(i);
			}
		);
		newFuture.complete("ok " + range.lower   + " ~ " + range.upper);
		String result= "done this job  " + range.upper+ " ~ " + range.lower;
		log.debug(result);
		return newFuture;
	}
	
	/**
	 * 쓸데 없는 일 
	 */
	public void tidiousJob(int param) {
		for(int index = 0; index < param; index++) {
			int a = param - index;
		}
	}

}
