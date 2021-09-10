package ok.practice.controller;

import java.util.List;

import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import ok.practice.jpa.Test;
import ok.practice.jpa.TestRepository;

@RestController
@RequiredArgsConstructor
public class TestController {

//	public  TestController(TestRepository testRepository) {
//		this.testRepository = testRepository;
//	}

	private final TestRepository testRepository;
	
	
	@RequestMapping("/test/tests")
	public List<Test> getTests() {
		
		return testRepository.findAll();
	}

	@PostMapping("/test/tests")
	public String  addResult(Test test){
		
		testRepository.save(test);
		return "ook";
	}
	
}
