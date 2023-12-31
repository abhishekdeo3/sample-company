package com.samplecompany.administration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = SampleCompanyApplication.class)
@ActiveProfiles("test")
@DirtiesContext
@ExtendWith(PostgreSQLExtension.class)
class SampleCompanyApplicationTests {

    @Test
    void contextLoads() {
    }

}
