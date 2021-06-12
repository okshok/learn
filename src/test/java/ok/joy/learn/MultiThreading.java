package ok.joy.learn;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;

public class MultiThreading {

	public void doInMultiThread(ExecutorService threadPoolExecutor, int numberOfThread) {
		int stressSize = 10000000;
		Stream.iterate(0, i -> i+ 1).limit(numberOfThread).forEach((i) -> {
			threadPoolExecutor.execute(() -> {
				System.out.println("thread number " + i );
				makeStress(stressSize);
				System.out.println("done thread number " + i);
			});
			
		});
	}

	public void makeStress(int size) {
		List<String> list = Lists.newArrayList();
		for (int i = 0; i < size; i++) {
			list.add(String.valueOf(i).toString().intern());

		}

	}
}
