package br.com.wepdev.apigatewayzuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;


/**
 * Todas as requisições passam aqui pelo Zuul, o cliente faz requisições para o zuul. O zuul esta roteando os micro serviços.
 * O Zuul e o responsavel por verificar se o cliente tem a autorização para acessar os micro serviços
 */
@EnableEurekaClient // Configurando para ser um cliente eureka
@EnableZuulProxy // Configurando o proxy do Zuul
@SpringBootApplication
public class ApiGatewayZuulApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayZuulApplication.class, args);
    }

}
