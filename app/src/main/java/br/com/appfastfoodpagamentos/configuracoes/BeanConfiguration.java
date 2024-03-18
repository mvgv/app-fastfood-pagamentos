package br.com.appfastfoodpagamentos.configuracoes;

import br.com.appfastfoodpagamentos.AppFastfoodPagamentosApplication;
import br.com.appfastfoodpagamentos.casodeuso.SolicitarPagamento;
import br.com.appfastfoodpagamentos.casodeuso.SolicitarPagamentoMercadoLivreImpl;
import br.com.appfastfoodpagamentos.infraestrutura.AdaptadorMercadoLivre;
import br.com.appfastfoodpagamentos.infraestrutura.adaptadores.SNSTopicHandlerImpl;
import br.com.appfastfoodpagamentos.infraestrutura.menssagem.portas.TopicHandler;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = AppFastfoodPagamentosApplication.class)
public class BeanConfiguration {

    @Bean
    TopicHandler topicHandler(){
        return new SNSTopicHandlerImpl(amazonSNS());
    }
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
                .withRegion(Regions.US_EAST_1)
                .withCredentials(new DefaultAWSCredentialsProviderChain())
                .build();
    }
}
