package br.com.appfastfoodpagamentos.dominio;

import lombok.Getter;

import java.util.UUID;


@Getter
public class Pagamento {


    private UUID id;

    private String meioPagamento;

    private String idMeioPagamento;

    private Double valor;

    private Boolean aprovado;

    public Pagamento(String meioPagamento, String idMeioPagamento, String valor){
        this.id = UUID.randomUUID();
        this.meioPagamento = meioPagamento;
        this.idMeioPagamento = idMeioPagamento;
        this.valor = valorConsistente(valor) ;
    }


    public Pagamento(String meioPagamento, String idMeioPagamento, String valor, Boolean aprovado){
        this.id = UUID.randomUUID();
        this.meioPagamento = meioPagamento;
        this.idMeioPagamento = idMeioPagamento;
        this.valor = valorConsistente(valor);
        this.aprovado = aprovado;
    }

    public Pagamento(Double valor) {
        this.valor = valor;
    }

    private Double valorConsistente(String valor) throws IllegalArgumentException {
        if(Double.parseDouble(valor) > 0 ){
            return Double.valueOf(valor);
        }
        throw new IllegalArgumentException("Pagamento deve ser maior que zero");
    }


}
