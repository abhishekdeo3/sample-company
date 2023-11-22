package com.samplecompany.administration.service;

import com.samplecompany.administration.PostgreSQLExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@DirtiesContext
@ExtendWith(PostgreSQLExtension.class)
public class EmployeeServiceTest {


}
