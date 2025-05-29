package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
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
        HttpEntity<String> request = createHttpEntity("a=3&b=4");
        ResponseEntity<String> response = this.restTemplate.postForEntity("/api/add", request, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isEqualTo("7");
    }

    @Test
    void greetEndpointShouldReturnHelloWorldByDefault() {
        String response = this.restTemplate.getForObject("/api/greet", String.class);
        assertThat(response).isEqualTo("Hello, World!");
    }

    @Test
    void addEndpointShouldWorkWithNegativeNumbers() {
        HttpEntity<String> request = createHttpEntity("a=-2&b=-3");
        ResponseEntity<String> response = this.restTemplate.postForEntity("/api/add", request, String.class);
        
        assertThat(response.getBody()).isEqualTo("-5");
    }

    @Test
    void addEndpointShouldReturnBadRequestForInvalidInput() {
        HttpEntity<String> request = createHttpEntity("a=abc&b=4");
        ResponseEntity<String> response = this.restTemplate.postForEntity("/api/add", request, String.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    private HttpEntity<String> createHttpEntity(String body) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        return new HttpEntity<>(body, headers);
    }
}
