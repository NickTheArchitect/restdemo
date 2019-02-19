package org.nickthearchitect.camunda.restdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureMockMvc
public class RestdemoApplicationRestTests {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Autowired
	private MockMvc mvc;

	@Test
	public void restApiIsAvailable() throws Exception {
		ResponseEntity<String> entity = testRestTemplate.getForEntity("/rest/engine/", String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("[{\"name\":\"default\"}]", entity.getBody());
	}

	@Test
	public void testFetchAndLock() throws Exception {

		Map<String, Object> requestMap = makeRequestMap();

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);

		ResponseEntity<String> entity = testRestTemplate.postForEntity("/rest/engine/default/external-task/fetchAndLock", request, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("[]", entity.getBody());

	}

	private Map<String, Object> makeRequestMap() {
		return new HashMap<String, Object>() {
				{
					put("workerId", "postmanNick");
					put("maxTasks", "1");
					put("usePriority", "false");
				}
			};
	}

	@Test
	public void testFetchAndLockWithAsyncResponseTimeout() throws Exception {

		Map<String, Object> requestMap = makeRequestMap();
		requestMap.put("asyncResponseTimeout", "10000");

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestMap, headers);

		ResponseEntity<String> entity = testRestTemplate.postForEntity("/rest/engine/default/external-task/fetchAndLock", request, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("[]", entity.getBody());

	}

}

