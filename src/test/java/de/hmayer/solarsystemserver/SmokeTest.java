package de.hmayer.solarsystemserver;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.hmayer.solarsystemserver.controller.SolarSystemController;

@SpringBootTest
class SmokeTest {

	@Autowired
	private SolarSystemController controller;

	@Test
	void SOL_T118_contextLoads() throws Exception {
		assertThat(controller).isNotNull();
	}

   
}