package ok.stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import ok.lamda.StreamService;
import ok.lamda.impl.StreamServiceImpl;

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
