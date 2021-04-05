package com.natwest.restservice.integration;

import com.natwest.restservice.RestServiceApplication;
import com.natwest.restservice.controller.PrimesController;
import com.natwest.restservice.dto.PrimeResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;



@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PrimesControllerIntegrationTest2 {
    @LocalServerPort private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    Integer[] firstPrime = {2},
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

    PrimeResponse firstPrimeEratosthenes = new PrimeResponse(
            "eratosthenes", new ArrayList<>(Arrays.asList(firstPrime))),
            firstTenPrimesAtkin = new PrimeResponse(
                    "atkin", new ArrayList<>(Arrays.asList(firstTenPrimes))),
            firstHundredPrimesSundaram = new PrimeResponse(
                    "sundaram", new ArrayList<>(Arrays.asList(firstHundredPrimes)));

    // write test cases here
    @Test
    public void given2_whenGetPrimes_thenGet2() throws Exception {
        ResponseEntity<PrimeResponse> response =
                this.restTemplate.getForEntity(getURL("/api/primes/2"), PrimeResponse.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    private String getURL(String path){
        return String.format("http://localhost:%d/%s", port, path);
    }
}
