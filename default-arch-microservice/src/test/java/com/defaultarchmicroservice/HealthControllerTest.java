package com.defaultarchmicroservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
public class HealthControllerTest {

    @Test
    void healthIsOk() {
        HealthController controller = new HealthController();
        assertEquals(true,controller.ok().getBody());
    }
}
