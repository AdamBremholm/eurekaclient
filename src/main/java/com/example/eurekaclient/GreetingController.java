package com.example.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class GreetingController {

    RestTemplate restTemplate;

    public GreetingController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/hello")
    public String sayHello() {
        return "Martins client says hello";
    }

    @GetMapping("/randomhello")
    public String randomHello() {

        return restTemplate.getForObject("http://greeting-app/hello", String.class);
    }

}
