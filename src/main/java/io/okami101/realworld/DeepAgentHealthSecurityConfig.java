// Deployment Contract §2 — health endpoint auto-added by deploy_my_app.
// A kubelet probe carries no credentials, so a Spring Security chain that
// ends in .anyRequest().authenticated() answers 401 and the rollout fails
// against a perfectly healthy app. This chain matches ONLY /health;
// every other request still goes through the application's own chain.
package io.okami101.realworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
class DeepAgentHealthSecurityConfig {
    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    SecurityFilterChain deepAgentHealthSecurityChain(HttpSecurity http) throws Exception {
        return http.securityMatcher("/health")
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())
                .build();
    }
}
