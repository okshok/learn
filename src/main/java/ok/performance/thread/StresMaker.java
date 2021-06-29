package ok.performance.thread;

public interface StresMaker {

	public void makeStressInDivison(int count);
	
	public void makeStressInDivisonAndGetFutureInOtherThread(int count);

	
}
