package com.jobportal;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
    info = @Info(
        title = "Employer API",
        version = "1.0",
        description = "API for employer job posting and application management",
        contact = @Contact(
            name = "Job Portal Support",
            email = "support@jobportal.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    )
)
public class EmployerApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmployerApiApplication.class, args);
    }
}