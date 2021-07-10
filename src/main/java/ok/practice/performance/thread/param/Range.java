package ok.practice.performance.thread.param;

public class Range {

	public Range(int upper, int lower) {
		this.lower = lower;
		this.upper = upper;
	}
	
	public int lower;
	public int upper;
	
	public int size() {
		return lower - upper;
	}
}
