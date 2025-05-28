package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.*;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void homeEndpointShouldReturnHelloJenkins() {
        String response = this.restTemplate.getForObject("/api/", String.class);
        assertThat(response).isEqualTo("Hello Jenkins!");
    }

    @Test
    void greetEndpointShouldReturnHelloName() {
        String response = this.restTemplate.getForObject("/api/greet?name=Cylia", String.class);
        assertThat(response).isEqualTo("Hello, Cylia!");
    }

    @Test
    void addEndpointShouldReturnCorrectSum() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> request = new HttpEntity<>("a=3&b=4", headers);

        ResponseEntity<String> response = this.restTemplate.postForEntity("/api/add", request, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("7");
    }
}
