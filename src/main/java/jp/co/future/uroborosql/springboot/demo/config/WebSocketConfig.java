package jp.co.future.uroborosql.springboot.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * WebSocket configuration
 *
 * @author Kenichi Hoshi
 * @see org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("endpoint")
            .setAllowedOrigins("*");
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
//        //メッセージを処理する Controller の呼び出しパスのプレフィックスを設定します。
//        registry.setApplicationDestinationPrefixes("/front");
        //購読セッションに対して配信するためのブローカーのプレフィックスを設定します。
        registry.enableSimpleBroker("/topic");
    }
}
