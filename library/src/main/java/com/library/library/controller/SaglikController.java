package com.library.library.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SaglikController {

    @GetMapping("/api/health")
    public String saglik() {
        return "OK";
    }
}



    

