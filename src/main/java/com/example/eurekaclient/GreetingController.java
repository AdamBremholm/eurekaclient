package com.example.eurekaclient;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @Autowired
    private EurekaClient eurekaClient;

    @GetMapping("/clients")
    public List<InstanceInfo> clients(){

        Application application = eurekaClient.getApplication("greeting-app");
        return application.getInstances();
    }

    // This method should only be accessed by users with role of 'admin'
    @GetMapping("/admin")
    public String homeAdmin() {
        return "This is the admin area";
    }
}
