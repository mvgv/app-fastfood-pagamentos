package br.com.appfastfoodpagamentos.infraestrutura.menssagem.saida;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PagamentoOut implements Serializable {
    @JsonProperty("produtos")
    private List<ProdutosOut> produtos;

    @JsonProperty("id_cliente")
    private String idCliente;

    @JsonProperty("valor_total")
    private Double valorTotal;

    @JsonProperty("status")
    private String status;

    @JsonProperty("tempo_espera")
    private String tempoEspera;

    @JsonProperty("id_pedido")
    private String idPedido;

    @JsonProperty("status_pagamento")
    private String statusPagamento;

    public PagamentoOut(){}
}
