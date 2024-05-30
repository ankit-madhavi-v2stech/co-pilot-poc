package com.quiz.mgmt;

import com.quiz.mgmt.admin.service.SubjectService;
import com.quiz.mgmt.repository.SubjectRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Configuration
	static class TestConfig {
		@Bean
		public SubjectService subjectService() {
			return Mockito.mock(SubjectService.class);
		}

		@Bean
		public SubjectRepository subjectRepository() {
			return Mockito.mock(SubjectRepository.class);
		}
	}

}
