package br.com.appfastfoodpagamentos.apresentacao;

import br.com.appfastfoodpagamentos.apresentacao.requisicao.RequisicaoPagamento;
import br.com.appfastfoodpagamentos.casodeuso.SolicitarPagamento;
import br.com.appfastfoodpagamentos.dominio.Pagamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamentos")
public class PagamentosController {

    private SolicitarPagamento solicitarPagamento;
    public PagamentosController(SolicitarPagamento solicitarPagamento){
        this.solicitarPagamento =  solicitarPagamento;
    }

    @PostMapping
    public ResponseEntity<?> efetuarPagamento(@RequestBody RequisicaoPagamento pagamentoReq) {
        try{
            Pagamento pagamento = solicitarPagamento.solicitarPagamento(pagamentoReq);
            RequisicaoPagamento pagamentoResposta = new RequisicaoPagamento(pagamento.getAprovado());
            return ResponseEntity.status(HttpStatus.OK).body(pagamentoResposta);
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parametros Incorretos");
        }
    }

}
