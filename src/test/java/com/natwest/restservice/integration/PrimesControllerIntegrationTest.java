package com.natwest.restservice.integration;

import com.natwest.restservice.dto.PrimeResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PrimesControllerIntegrationTest {
    @LocalServerPort private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    Integer[] emptyPrimes = {},
            firstPrime = {2},
            firstTenPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29},
            firstHundredPrimes = {
                    2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                    31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                    73, 79, 83, 89, 97, 101, 103, 107, 109, 113,
                    127, 131, 137, 139, 149, 151, 157, 163, 167, 173,
                    179, 181, 191, 193, 197, 199, 211, 223, 227, 229,
                    233, 239, 241, 251, 257, 263, 269, 271, 277, 281,
                    283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
                    353, 359, 367, 373, 379, 383, 389, 397, 401, 409,
                    419, 421, 431, 433, 439, 443, 449, 457, 461, 463,
                    467, 479, 487, 491, 499, 503, 509, 521, 523, 541
            };

    // write test cases here
    @Test
    public void given2_whenGetPrimes_thenGet2() throws Exception {
        ResponseEntity<PrimeResponse> response =
                this.restTemplate.getForEntity(getPrimesURL("/api/primes/2"), PrimeResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("application/json", response.getHeaders().getContentType().toString());
        assertEquals("eratosthenes", response.getBody().getAlgorithm());
        assertArrayEquals(firstPrime, response.getBody().getPrimes().toArray());
    }

    @Test
    public void givenNeg5_whenGetPrimes_thenGetEmptyList() throws Exception {
        ResponseEntity<PrimeResponse> response =
                this.restTemplate.getForEntity(getPrimesURL("/api/primes/-5"), PrimeResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("application/json", response.getHeaders().getContentType().toString());
        assertEquals("eratosthenes", response.getBody().getAlgorithm());
        assertArrayEquals(emptyPrimes, response.getBody().getPrimes().toArray());
    }

    @Test
    public void given30_andAtkin_whenGetPrimes_thenGetFirstTen_andAtkin() throws Exception {
        ResponseEntity<PrimeResponse> response =
                this.restTemplate.getForEntity(getPrimesURL("/api/primes/30?algorithm=atkin"), PrimeResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("application/json", response.getHeaders().getContentType().toString());
        assertEquals("atkin", response.getBody().getAlgorithm());
        assertArrayEquals(firstTenPrimes, response.getBody().getPrimes().toArray());
    }

    @Test
    public void given541_andSundaram_whenGetPrimes_thenGetFirstHundred_andSundaram() throws Exception {
        ResponseEntity<PrimeResponse> response =
                this.restTemplate.getForEntity(
                        getPrimesURL("/api/primes/541?algorithm=sundaram"),
                        PrimeResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("application/json", response.getHeaders().getContentType().toString());
        assertEquals("sundaram", response.getBody().getAlgorithm());
        assertArrayEquals(firstHundredPrimes, response.getBody().getPrimes().toArray());
    }

    @Test
    public void given30_andRandomCaseEratosthenes_whenGetPrimes_thenGetFirst10_andEratosthenes() throws Exception {
        ResponseEntity<PrimeResponse> response =
                this.restTemplate.getForEntity(
                        getPrimesURL("/api/primes/30?algorithm=EratosThenes"),
                        PrimeResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("application/json", response.getHeaders().getContentType().toString());
        assertEquals("eratosthenes", response.getBody().getAlgorithm());
        assertArrayEquals(firstTenPrimes, response.getBody().getPrimes().toArray());
    }

    @Test
    public void givenBadAlgorithm_whenGetPrimes_thenGetAlgorithmNotFoundException() throws Exception {
        ResponseEntity<String> response =
                this.restTemplate.getForEntity(
                        getPrimesURL("/api/primes/541?algorithm=eratothenes"),
                        String.class);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Algorithm not found", response.getBody());
    }

    @Test
    public void givenXMLRequest_whenGetPrimes_thenGetXML() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_XML));
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<PrimeResponse> response =
                restTemplate.exchange(
                        getPrimesURL("/api/primes/541?algorithm=atkin"),
                        HttpMethod.GET,
                        httpEntity,
                        PrimeResponse.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getHeaders().getContentType().toString().contains("application/xml"));
        assertArrayEquals(firstHundredPrimes, response.getBody().getPrimes().toArray());
    }

    private String getPrimesURL(String path){
        return String.format("http://localhost:%d/%s", port, path);
    }
}
