package ok.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ok.performance.thread.StresMaker;

@RestController
@RequestMapping("/root")
public class RootConroller {

	
	@Autowired
	StresMaker multiThreadStress;
	
	@RequestMapping("/stress")
	public String stress(String stressPoint) {
		
		multiThreadStress.makeStressInDivison(100000);
		
		return "";
	}
	@RequestMapping("/stress2")
	public String stress2(String stressPoint) {
		
		multiThreadStress.makeStressInDivisonAndGetFutureInOtherThread(100000);
		
		return "";
	}
}
