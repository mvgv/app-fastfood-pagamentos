package br.com.appfastfoodpagamentos.casodeuso;

import br.com.appfastfoodpagamentos.apresentacao.requisicao.RequisicaoPagamento;
import br.com.appfastfoodpagamentos.dominio.Pagamento;

public interface SolicitarPagamento {
    public Pagamento solicitarPagamento(RequisicaoPagamento pagamento);
}
