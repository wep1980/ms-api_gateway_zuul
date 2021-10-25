package br.com.wepdev.apigatewayzuul.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;


/**
 * Classe que determina que o zuul vai fazer o serviço de autenticação
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {


    @Autowired
    private JwtTokenStore tokenStore;


    /* Vetor de Strings que recebe os caminhos que serao publicos, autorizando as rotas.
       /oauth/oauth/token -> rota para fazer autenticação
     */
    private static final String[] PUBLIC = {"/oauth/oauth/token"};

    /*
    Rotas que precisam ter autorização de OPERADOR ou ADMIN para serem acessadas
    Qualquer rota do micro serviço de trabalhador esta autorizada
     */
    private static final String[] OPERATOR = {"/recursos-humanos-trabalhadores/**"};

    /*
    Rotas que para acessar precisam do perfil de ADMIN
     */
    private static final String[] ADMIN = {"/recursos-humanos-folha-pagamento/**", "/usuario/**"};


    /**
     * Metodo para ler o token
     * @param resources
     * @throws Exception
     */
    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.tokenStore(tokenStore);
    }


    /**
     * Metodo que configura as autorizações
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(PUBLIC).permitAll() // Todos tem permissao para acessar essas rotas
                .antMatchers(HttpMethod.GET, OPERATOR).hasAnyRole("OPERATOR", "ADMIN") // tem autorização somente nos metodos GET as rotas que estao nos perfis
                .antMatchers(ADMIN).hasAnyRole("ADMIN") // So acessa as rotas do ADMIN quem tiver o perfil de ADMIN
                .anyRequest().authenticated(); // Qualquer rota que nao esteja especificada, e exigido a autenticação para acessar
    }
}
