package ok.joy.learn.thread;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ok.joy.learn.param.Range;

public class StressJob implements Callable<String>{
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	public String newFuture = null;
	public Range range = null;

	@Override
	public String call() throws Exception {
		log.debug("start job");
		Stream.iterate(range.lower, i -> i + 1).limit(range.upper)
			.forEach(i -> {
				tidiousJob(i);
			}
		);
//		newFuture.complete("ok " + range.lower   + " ~ " + range.upper);
		newFuture = "ok " + range.upper+ " ~ " + range.lower;
		log.debug(newFuture);
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
