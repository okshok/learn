package ok.practice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ok.practice.lamda.StreamService;

@RestController
@RequestMapping("/test/stream")
public class StreamController {

	@Autowired
	private StreamService streamService;
	
	@RequestMapping("/calculatePlus")
	public void calculatePlus() {
		streamService.calculatePlus();	
	}
}
