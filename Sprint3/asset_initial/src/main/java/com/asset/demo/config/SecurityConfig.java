package com.asset.demo.config;

import com.asset.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.catalina.authenticator.BasicAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final UserService userService;
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }


    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize) -> authorize
                        .requestMatchers(HttpMethod.OPTIONS,"/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.POST,"/api/user/add")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET,"/api/asset/get-all")
                        .hasAnyAuthority("ADMIN","EMPLOYEE")
                                .requestMatchers(HttpMethod.GET,"/api/user/get-all")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/user/get/{id}")
                                .hasAnyAuthority("ADMIN","EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/api/asset-category/add")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/asset-category/get-all")
                                .hasAnyAuthority("ADMIN","EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/api/asset/add/{categoryId}")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.GET,"/api/asset/get-all")
                                .hasAnyAuthority("ADMIN","EMPLOYEE")
                                .requestMatchers(HttpMethod.GET,"/api/asset/get/{assetId}")
                                .hasAnyAuthority("ADMIN","EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/api/asset-request/add/{employeeId}/{assetId}")
                                .hasAnyAuthority("ADMIN","EMPLOYEE")
                                .requestMatchers(HttpMethod.GET,"/api/asset-request//get-all")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/asset-allocation/allocate/{employeeId}/{assetId}/{assetRequestId}")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/asset-allocation/reject/{employeeId}/{assetId}/{assetRequestId}")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/asset-allocation/return-asset/{employeeId}/{assetId}/{assetAllocationId}")
                                .hasAnyAuthority("ADMIN","EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/api/service-request/request-service/{employeeId}/{assetId}/{assetAllocationId}")
                                .hasAnyAuthority("ADMIN","EMPLOYEE")
                                .requestMatchers(HttpMethod.PUT,"/api/service-request/accept-service-request/{serviceRequestId}")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/service-request/reject-service-request/{serviceRequestId}")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.PUT,"/api/service-request/resolved-service-request/{serviceRequestId}")
                                .hasAnyAuthority("ADMIN")
                                .requestMatchers(HttpMethod.POST,"/api/asset-audit/audit/{employeeId}/{assetId}")
                                .hasAnyAuthority("ADMIN")



//                for development no need of user credentials
//                        .anyRequest().permitAll()
//                for production i have to enable this so security will be checked
                        .anyRequest().authenticated()

                );
        http.httpBasic(Customizer.withDefaults());  //Spring understand that i am using this technique
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(authenticationProvider);
    }
}
