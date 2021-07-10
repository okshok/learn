package ok.practice.lamda.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import ok.practice.lamda.StreamService;

@Service
public class StreamServiceImpl implements StreamService {
	
	int numberOfProcessor; 
	{
		numberOfProcessor = Runtime.getRuntime().availableProcessors();
	}
	
	
	@Override
	public void calculatePlus() {
		
		Stream.iterate(0, i -> i + 1).limit(numberOfProcessor * 2)
			.forEach((i) ->{
				doSimpleLongJob();
			});

	}
	@Override
	public void calculatePlusInParrallel() {
		
		Stream.iterate(0, i -> i + 1).limit(numberOfProcessor * 2)
		.parallel() 
		.forEach((i) ->{
			doSimpleLongJob();
		});
		
	}

	private void doSimpleLongJob() {
		
		int upperRange = 10000000;
		List<Integer> list = Stream.iterate(0, i -> i + 1).limit(upperRange)
				.collect(Collectors.toList());
		
		
		Integer total = list.stream()
				.reduce(0, (x, y) -> x + y);

	}

}
