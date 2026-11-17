package in.ashokit.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "USER REST endpoints",
                version = "1.1",
                description = "API for managing Users",
                license = @License(name = "Ashokit", url = "http://ashokit.in")
        ),
        servers = @Server(url = "http://localhost:8080")
)
@Configuration
public class AppConfig {
}
