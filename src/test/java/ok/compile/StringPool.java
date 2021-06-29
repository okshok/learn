package ok.compile;

public class StringPool {

	public static void main(String[] args) {

		String a = "abc";
		String b = "bcd";
		String c = "abc";
		String d = a + b;
		String e = new String("abc");
		String f = new String("fed").intern();

	}

}
