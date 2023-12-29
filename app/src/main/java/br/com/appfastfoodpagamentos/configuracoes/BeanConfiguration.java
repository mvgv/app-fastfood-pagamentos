package br.com.appfastfoodpagamentos.configuracoes;

import br.com.appfastfoodpagamentos.AppFastfoodPagamentosApplication;
import br.com.appfastfoodpagamentos.casodeuso.SolicitarPagamento;
import br.com.appfastfoodpagamentos.casodeuso.SolicitarPagamentoMercadoLivreImpl;
import br.com.appfastfoodpagamentos.infraestrutura.AdaptadorMercadoLivre;
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
    SolicitarPagamento solicitarPagamento(AdaptadorMercadoLivre adaptadorMercadoLivre){
        return new SolicitarPagamentoMercadoLivreImpl(adaptadorMercadoLivre);
    }
}
