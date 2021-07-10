package ok.stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ok.practice.lamda.StreamService;
import ok.practice.lamda.impl.StreamServiceImpl;

public class StreamTest {

	private static StreamService streamService;

	@BeforeAll
	public static void classSetting() {
		streamService = new StreamServiceImpl();
	}

	@Test
	public void stream() {
		streamService.calculatePlus();
	}

	@Test
	public void streamWithParrallel() {
		streamService.calculatePlusInParrallel();
	}
}
