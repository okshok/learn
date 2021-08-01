package ok.practice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ok.practice.jpa.Test;
import ok.practice.jpa.TestRepository;

@RestController
@RequiredArgsConstructor
public class TestController {

	
	private final TestRepository testRepository;
	
	
	@RequestMapping("/test/tests")
	public List<Test> getTests() {
		
		return testRepository.findAll();
	}

	@PostMapping("/test/tests")
	public void addResult(Test test){
		
		testRepository.save(test);
	}
	
}
