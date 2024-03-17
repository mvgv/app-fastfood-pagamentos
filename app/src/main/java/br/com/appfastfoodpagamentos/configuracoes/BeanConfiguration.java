package br.com.appfastfoodpagamentos.configuracoes;

import br.com.appfastfoodpagamentos.AppFastfoodPagamentosApplication;
import br.com.appfastfoodpagamentos.casodeuso.SolicitarPagamento;
import br.com.appfastfoodpagamentos.casodeuso.SolicitarPagamentoMercadoLivreImpl;
import br.com.appfastfoodpagamentos.infraestrutura.AdaptadorMercadoLivre;
import br.com.appfastfoodpagamentos.infraestrutura.menssagem.portas.TopicHandler;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AppFastfoodPagamentosApplication.class)
public class BeanConfiguration {

    @Bean
    AdaptadorMercadoLivre adaptadorMercadoLivre() {
        return new AdaptadorMercadoLivre();
    }

    @Bean
    SolicitarPagamento solicitarPagamento(AdaptadorMercadoLivre adaptadorMercadoLivre, TopicHandler topicHandler){
        return new SolicitarPagamentoMercadoLivreImpl(adaptadorMercadoLivre, topicHandler);
    }
    @Bean
    public AmazonSNS amazonSNS() {
        // Configuração básica de um cliente AmazonSNS usando as credenciais padrão do SDK
        return AmazonSNSClientBuilder.standard()
                .withEndpointConfiguration(new AmazonSNSClientBuilder.EndpointConfiguration("http://localhost.localstack.cloud:4566", "us-east-1"))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials("test", "test")))
                .build();
    }
}
