package com.natwest.restservice.unit;

import com.natwest.restservice.model.AtkinPrimes;
import com.natwest.restservice.model.EratosthenesPrimes;
import com.natwest.restservice.model.SundaramPrimes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrimesControllerTest {
    private EratosthenesPrimes eratosthenesPrimes;
    private SundaramPrimes sundaramPrimes;
    private AtkinPrimes atkinPrimes;

    @BeforeEach
    void setUp(){
        eratosthenesPrimes = new EratosthenesPrimes();
        sundaramPrimes = new SundaramPrimes();
        atkinPrimes = new AtkinPrimes();
    }

    @Test
    void negative() {
        int n = -5;
        Integer[] expectedPrimes = {};
        checkAlgorithms(expectedPrimes, n);
    }

    @Test
    void belowMin() {
        int n = 1;
        Integer[] expectedPrimes = {};
        checkAlgorithms(expectedPrimes, n);
    }

    @Test
    void first() {
        int n = 2;
        Integer[] expectedPrimes = {2};
        checkAlgorithms(expectedPrimes, n);
    }

    @Test
    void firstTwo() {
        int n = 3;
        Integer[] expectedPrimes = {2, 3};
        checkAlgorithms(expectedPrimes, n);
    }

    @Test
    void firstTen() {
        int n = 30;
        Integer[] expectedPrimes = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29};
        checkAlgorithms(expectedPrimes, n);
    }

    @Test
    void firstHundred() {
        int n = 541;
        Integer[] expectedPrimes = {
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
        checkAlgorithms(expectedPrimes, n);
    }

    void checkAlgorithms(Integer[] expectedPrimes, int n){
        Object[] eratosthenesPrimesActual = eratosthenesPrimes.getPrimes(n).toArray();
        Object[] sundaramPrimesActual = sundaramPrimes.getPrimes(n).toArray();
        Object[] atkinPrimesActual = atkinPrimes.getPrimes(n).toArray();

        assertArrayEquals(expectedPrimes, eratosthenesPrimesActual);
        assertArrayEquals(expectedPrimes, sundaramPrimesActual);
        assertArrayEquals(expectedPrimes, atkinPrimesActual);
    }
}
