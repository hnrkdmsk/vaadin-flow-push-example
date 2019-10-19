package com.avides.vaadin;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@SpringJUnitConfig
class ApplicationTest
{
    @Test
    void testRun()
    {
        // nothing ..
        // simulates the start up process
    }
}
