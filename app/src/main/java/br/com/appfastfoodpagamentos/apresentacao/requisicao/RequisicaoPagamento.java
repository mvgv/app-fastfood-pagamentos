package br.com.appfastfoodpagamentos.apresentacao.requisicao;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RequisicaoPagamento {
    @JsonProperty("status")
    private String status;

    @JsonProperty("meio_pagamento")
    private String meioPagamento;

    @JsonProperty("id_meio_pagamento")
    private String idMeioPagamento;

    @JsonProperty("valor")
    private String valor;

    public RequisicaoPagamento(String meioPagamento, String idMeioPagamento, String valor){
        this.status = "PENDENTE";
        this.meioPagamento =  meioPagamento;
        this.idMeioPagamento = idMeioPagamento;
        this.valor = valor;
    }

    public RequisicaoPagamento(Boolean status){
       this.status = status ? "APROVADO" : "REPROVADO";
    }
}
