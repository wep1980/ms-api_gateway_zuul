package br.com.wepdev.apigatewayzuul.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {



    /**
     * Componente para ser utilizar o JWT
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey("MY-SECRET-KEY"); // Chave secreta que assinara os tokens. (Assinatura do token)
        return tokenConverter;
    }


    /**
     * Objeto responsável por ler as informações do token
     */
    @Bean
    public JwtTokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }
}
