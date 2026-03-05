package pl.tradingplatfrom.dashboardservice.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;

@Configuration
@EnableWebSocketSecurity
public class WebSocketSecurityConfig {

    @Bean
    AuthorizationManager<Message<?>> messageAuthorizationManager(MessageMatcherDelegatingAuthorizationManager.Builder messages) {
        return messages
                .nullDestMatcher().permitAll()
                .simpDestMatchers("/topic/alerts/BTC", "/topic/alerts/ETH").hasRole("PREMIUM")
                .simpDestMatchers("topic/alerts/**").authenticated()
                .anyMessage().denyAll()
                .build();
    }

}
