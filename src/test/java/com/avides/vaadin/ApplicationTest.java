package com.avides.vaadin;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = Application.class, webEnvironment = RANDOM_PORT)
@ActiveProfiles("it")
class ApplicationTest
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void testRun()
    {
        // nothing ..
        // simulates the start up process

        // TODO: remove this after action tests
        jdbcTemplate.update("INSERT INTO test (name) VALUES ('Test User')");
        var result = jdbcTemplate.queryForList("SELECT name FROM test", String.class);
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Test User", result.get(0));
    }
}
