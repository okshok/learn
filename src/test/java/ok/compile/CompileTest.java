package ok.compile;

public class CompileTest {

	public static void main(String[] args) {
		
		CompileTest test = new  CompileTest();
		for (int i =0; i < 1000; i++) {
			
			test.forceComplile();
			test.makeLosingReference();
		}
	}
	
	
	public void forceComplile() {
		int a = 5;
		
		int b = 10;
		
		int c =a * b;
		c = 0;
	}
	
	public void makeLosingReference()  {

		String a = "werwerwer";
		String b= "34sffdsgerg";
		String c = a + b;
		b = null;
		
	}
}
