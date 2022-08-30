package com.defaultarchmicroservice;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {
    @GetMapping
    public ResponseEntity<Boolean> ok(){
        return ResponseEntity.ok(true);
    }
}
