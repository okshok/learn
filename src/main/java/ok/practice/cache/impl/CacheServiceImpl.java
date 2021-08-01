package ok.practice.cache.impl;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import ok.practice.cache.CacheService;

public class CacheServiceImpl implements CacheService {

	@Override
	public List<String> getBigData() {
		return Stream.iterate(0, i -> i + 1).limit(10000)
				.map(i -> i + "start")
				.collect(Collectors.toList());					
	}

}
