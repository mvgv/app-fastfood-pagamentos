package br.com.appfastfoodpagamentos.casodeuso;

import br.com.appfastfoodpagamentos.dominio.Pagamento;
import br.com.appfastfoodpagamentos.infraestrutura.AdaptadorMercadoLivre;
import br.com.appfastfoodpagamentos.infraestrutura.menssagem.entrada.PagamentoIn;
import br.com.appfastfoodpagamentos.infraestrutura.menssagem.portas.TopicHandler;
import br.com.appfastfoodpagamentos.infraestrutura.menssagem.saida.PagamentoOut;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SolicitarPagamentoMercadoLivreImpl implements SolicitarPagamento{

    private AdaptadorMercadoLivre adaptadorMercadoLivre;
    private TopicHandler topicHandler;

    public SolicitarPagamentoMercadoLivreImpl(AdaptadorMercadoLivre adaptadorMercadoLivre, TopicHandler topicHandler) {
        this.adaptadorMercadoLivre = adaptadorMercadoLivre;
        this.topicHandler = topicHandler;
    }
    @Override
    public void solicitarPagamento(String notification) throws IllegalArgumentException {
        try {
            PagamentoIn in = new ObjectMapper().readValue(notification, PagamentoIn.class);
            Boolean efetuouPagamento = adaptadorMercadoLivre.pagarComQrCode(Double.valueOf(in.getValorTotal()));
            PagamentoOut out = new ObjectMapper().readValue(notification, PagamentoOut.class);
            this.publicarPagamento(efetuouPagamento, out);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void publicarPagamento(Boolean efetuouPagamento, PagamentoOut out) {

        try {

            if(efetuouPagamento == true) {
                out.setStatusPagamento("APROVADO");
                this.topicHandler.publish(new ObjectMapper().writeValueAsString(out), "arn:aws:sns:us-east-1:101478099523:pagamento-efetuado");
            } else {
                out.setStatusPagamento("RECUSADO");
                this.topicHandler.publish(new ObjectMapper().writeValueAsString(out), "arn:aws:sns:us-east-1:101478099523:pagamento-recusado");
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }


}
