# NatWest Lead Engineer - API Technical Exercise
Technical assessment for the Lead Engineer API position in NatWest Group. The task was to build an API using Java 
and Spring Boot framework that returns in JSON format all the prime numbers up to and including a given number. 
Additional functionality includes the developer being able to specify the Sieve algorithm used for building that 
list of prime numbers, specifying XML format to be returned, and deploying the service remotely. How to utilise these
features are described below.

## Pre-Requisites
This project has been built using
* [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Maven 3.6.3](http://maven.apache.org/index.html)
* [Spring Boot CLI 2.4.4](https://docs.spring.io/spring-boot/docs/current-SNAPSHOT/reference/html/getting-started.html#getting-started-installing-the-cli)

Note: `brew` based installations are available for both Maven and Spring Boot CLI

## Installation

Once the repository is cloned onto your machine, navigate to the root of the project and execute the following Maven
commands: 
> `mvn install` <br/>
> `mvn spring-boot:run`

The API will deploy on `http://localhost:8080`

## API Endpoints
There is only one API endpoint: `GET /api/primes/{n}`. The API will return a list of prime numbers in JSON format 
up to and including `n`. 

It will also include the name of the algorithm used to build this list of primes. The default
algorithm used is the Sieve of Eratosthenes. The other algorithms include the Sieve of Atkin and the Sieve of Sundaram.
The developer can specify which algorithm to use by appending a query string with the attribute `algorithm` followed 
by a choice of `eratosthenes`, `atkin`, or `sundaram`. These names are not case-sensitive, but they must be spelled 
correctly. Incorrectly spelled algorithm names will return a `404 Not Found` response. 

### Example Request
An example request could be `GET /api/primes/30?algorithm=atkin`, returning the following response:
> `{ "algorithm": "atkin", "primes": [ 2, 3, 5, 7, 11, 13, 17, 19, 23, 29]}`

The Swagger documentation for the API can be found at `http://localhost:8080/swagger-ui/` if running on a local 
machine.

### XML
The response is returned in JSON format by default. XML format can be specified by the header: 
`Accept: application/xml`

### Endpoint
The solution has been deployed to a remote cloud service. The specification is the same, with only the root URL
being changed: <br/>
`https://lead-engineer-api-technical.azurewebsites.net/api/primes/{n}`

The Swagger documentation for the remote copy of this API can be found at: <br/>
`https://lead-engineer-api-technical.azurewebsites.net/swagger-ui/` 

## Assumptions
Here is where a few assumptions about the desired outcome are documented, they are as follows:

1. The output was to be an array of primes
2. The API should not return an error for invalid values of `n` (i.e. input less than 0)
3. The API should return an error if it cannot find a match for the algorithm specified
4. The algorithm used was expected to form part of the response body

## Improvements
While writing this exercise, it was determined there were a few improvements that could be made on this codebase.
They include: 

1. Utilise enums for specifying the algorithm
2. When algorithm isn't specified, select best performing one for a given value `n`
3. Figure out the maximum safe value of `n`