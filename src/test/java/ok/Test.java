package ok;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {

		
		List<? extends String> list = new ArrayList<>(List.of("a","b"));
//		list.add("a");
		System.out.println(list.get(0));
		
	}

}
