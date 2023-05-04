package com.sgb.drones;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sgb.drones.domain.entities.Drone;
import com.sgb.drones.domain.enums.DroneStateEnum;
import com.sgb.drones.domain.enums.ModelEnum;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
@AutoConfigureMockMvc
class DronesApplicationTests {

	@Autowired
    private MockMvc mvc;

	@Autowired
    private ObjectMapper objectMapper;

	@Test
    public void shouldReturnMessageErrorWhenWeightIsGreaterThanTheLimit() throws Exception {

		var drone = new Drone();
		drone.setSerialNumber("123456");
		drone.setModel(ModelEnum.Cruiserweight);
		drone.setMaxWeight(600);
		drone.setState(DroneStateEnum.IDLE);

        mvc.perform(MockMvcRequestBuilders
			.post("/api/drone")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(drone)))
			.andDo(print())
			.andExpect(status().is4xxClientError())
			.andExpect(jsonPath("$.message", is("The weight: 600, exceeds the max weight")));
    }

}
