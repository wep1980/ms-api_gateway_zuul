package br.com.wepdev.apigatewayzuul.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@RefreshScope // Atualiza os valores da variaveis em tempo de execução com actuator, quando uma config e feita no repositorio criado de configuração do github
@Configuration
public class AppConfig {


    /**
     * Essa chave esta sendo lida do application.properties desse projeto, e ele le do server-config(MICRO SERVIÇO), que le do seu proprio
     * application.properties, que esta configurado para pegar as informações do repositorio de configuração criado no github, repositorio onde contem as configurações
     * globais do eco sistema dos micro serviços
     */
    @Value("${jwt.secret}")
    private String jwtSecret;

    /**
     * Componente para ser utilizar o JWT
     * @return
     */
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
        tokenConverter.setSigningKey(jwtSecret); // Chave secreta que assinara os tokens. (Assinatura do token)
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
