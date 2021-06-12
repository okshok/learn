package ok.joy.learn.thread.impl;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ok.joy.learn.param.Range;
import ok.joy.learn.thread.StresMaker;
import ok.joy.learn.thread.StressJob;
import ok.joy.learn.thread.StressJobCompletableFuture;

@Component
public class StressMakerImpl implements StresMaker{

	private final Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	ThreadPoolExecutor threadPoolExecutor;
	
	protected int partitionNumber = 6;
	
	@Override
	public void makeStressInDivison(int count) {
	
		List<StressJob> workList = divideWorkRange(count).stream()
			.map(workRange -> {
				return getWrok(workRange);
		}).collect(Collectors.toList());
		
		try {
			List<Future<String>> list = threadPoolExecutor.invokeAll(workList);
			list.stream().forEach(future -> {
					try {
						log.debug(future.get());
					} catch (InterruptedException | ExecutionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
			});
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Override
	public void makeStressInDivison2(int count) {
		List<StressJobCompletableFuture> workList = divideWorkRange(count).parallelStream()
			.map(workRange -> {
					return getWrokCompletable(workRange);
		}).peek(work -> {
			threadPoolExecutor.submit(work);
		}).collect(Collectors.toList());
			

		threadPoolExecutor.submit(() -> {
			
			workList.stream().forEach(work -> {
				try {
					log.debug(work.newFuture.get());
				} catch (InterruptedException | ExecutionException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
			log.debug("진짜 다 끝났다");
		});
//			try {
//				log.debug(work.newFuture.get());
//			} catch (InterruptedException | ExecutionException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}

		log.debug("언제 호출 다 마쳐?");
	}
	
	protected List<Range> divideWorkRange(int count) {
		int remainder = count % partitionNumber;
		int quotient = count / partitionNumber;
		
		List<Range> list = Stream.iterate(1, n -> n+ quotient ).limit(partitionNumber)
			.map(i -> {
				
				Range range = new Range(i, i + quotient - 1);
				return range;
				})
			.collect(Collectors.toList());
	
		if (remainder > 0) {
			int lastLower = quotient * partitionNumber + 1;
			list.add(new Range(lastLower, lastLower + remainder - 1 ));
		}
		return list;
	}

	protected StressJob getWrok(Range range) { 
		StressJob job = new StressJob(); 
		job.range = range;
		return job;
	}
	
	protected StressJobCompletableFuture getWrokCompletable(Range range) { 
		StressJobCompletableFuture job = new StressJobCompletableFuture(); 
		job.range = range;
		return job;
	}


	
}
