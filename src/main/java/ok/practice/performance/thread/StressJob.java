package ok.practice.performance.thread;

import java.util.concurrent.Callable;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ok.practice.performance.thread.param.Range;

public class StressJob implements Callable<String>{
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	public String result = null;
	public Range range = null;

	@Override
	public String call() throws Exception {
		log.debug("start job");
		Stream.iterate(range.lower, i -> i + 1).limit(range.upper)
			.forEach(i -> {
				tidiousJob(i);
			}
		);
		result = "ok " + range.upper+ " ~ " + range.lower;
		log.debug(result);
		return result;
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
