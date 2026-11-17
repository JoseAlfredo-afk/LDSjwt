package br.fai.lds.projetolds2026.configuration;

import br.fai.lds.projetolds2026.ports_and_adapters.adapter.dao.user.UserFakeDaoAdapter;
import br.fai.lds.projetolds2026.ports_and_adapters.adapter.dao.user.UserPostgresDaoAdapter;
import br.fai.lds.projetolds2026.ports_and_adapters.adapter.service.security.BasicAuthenticationServiceAdapter;
import br.fai.lds.projetolds2026.ports_and_adapters.adapter.service.security.JwtAuthenticationServiceAdapter;
import br.fai.lds.projetolds2026.ports_and_adapters.port.dao.user.UserDao;
import br.fai.lds.projetolds2026.ports_and_adapters.port.service.security.AuthenticationService;
import br.fai.lds.projetolds2026.ports_and_adapters.port.service.user.UserService;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import jakarta.websocket.RemoteEndpoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;

import javax.management.openmbean.OpenMBeanParameterInfo;
import java.sql.Connection;
import java.util.Arrays;

@Configuration
public class AppConfiguration {

    private final Environment environment;

    public AppConfiguration(Environment environment) {
        this.environment = environment;
        System.out.println("---------");
        System.out.println("active profile" + Arrays.toString(environment.getActiveProfiles()));
        System.out.println("---------");
    }

    public UserDao getUserFakeDao() {

        return new UserFakeDaoAdapter();
    }

    @Bean
    public UserDao getUserPostgresDao(final Connection connection) {
        return new UserPostgresDaoAdapter(connection);
    }


    @Profile("basic")
    @Bean
    public AuthenticationService basicAuthenticationService(final UserService userService) {
        return new BasicAuthenticationServiceAdapter(userService);
    }

    @Profile("jwt")
    @Bean
    public AuthenticationService JwtAuthenticationServiceAdapter() {
        return new JwtAuthenticationServiceAdapter();
    }

    @Bean
    public OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("lds")
                        .version("0.0.1")
                        .description("Backend Lds 2026"));
    }


}
