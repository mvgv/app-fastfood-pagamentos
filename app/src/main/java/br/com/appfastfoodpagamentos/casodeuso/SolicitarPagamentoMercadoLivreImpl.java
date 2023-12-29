package br.com.appfastfoodpagamentos.casodeuso;

import br.com.appfastfoodpagamentos.apresentacao.requisicao.RequisicaoPagamento;
import br.com.appfastfoodpagamentos.dominio.Pagamento;
import br.com.appfastfoodpagamentos.infraestrutura.AdaptadorMercadoLivre;

public class SolicitarPagamentoMercadoLivreImpl implements SolicitarPagamento{

    private AdaptadorMercadoLivre adaptadorMercadoLivre;

    public SolicitarPagamentoMercadoLivreImpl(AdaptadorMercadoLivre adaptadorMercadoLivre) {
        this.adaptadorMercadoLivre = adaptadorMercadoLivre;
    }
    @Override
    public Pagamento solicitarPagamento(RequisicaoPagamento pagamentoReq) throws IllegalArgumentException {
        Pagamento pagamentoAEfetuar = new Pagamento(pagamentoReq.getMeioPagamento(), pagamentoReq.getIdMeioPagamento(), pagamentoReq.getValor());
        Boolean efetuouPagamento = adaptadorMercadoLivre.pagarComQrCode(pagamentoAEfetuar);
        return new Pagamento(pagamentoAEfetuar.getMeioPagamento(),
                pagamentoAEfetuar.getIdMeioPagamento(),
                pagamentoAEfetuar.getValor().toString(),
                efetuouPagamento);
    }
}
