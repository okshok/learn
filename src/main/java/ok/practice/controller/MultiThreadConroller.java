package ok.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ok.practice.performance.thread.StresMaker;

@RestController
@RequestMapping("/thread")
public class MultiThreadConroller {


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
	@RequestMapping("/stress3")
	public String stress3(String stressPoint) {
		
		multiThreadStress.makeStressWithCompletableFuture(100000);
		
		return "";
	}
}
