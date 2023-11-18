package com.samplecompany.administration;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = SampleCompanyApplication.class)
@ActiveProfiles("test")
class SampleCompanyApplicationTests extends AbstractIT {

    @Test
    void contextLoads() {
    }

}
