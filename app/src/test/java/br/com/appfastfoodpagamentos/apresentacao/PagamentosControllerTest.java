package br.com.appfastfoodpagamentos.apresentacao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.appfastfoodpagamentos.apresentacao.requisicao.RequisicaoPagamento;
import br.com.appfastfoodpagamentos.casodeuso.SolicitarPagamento;
import br.com.appfastfoodpagamentos.dominio.Pagamento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class PagamentosControllerTest {

    @InjectMocks
    PagamentosController pagamentosController;

    @Mock
    SolicitarPagamento solicitarPagamento;


    @Test
    public void testEfetuarPagamento() {
        RequisicaoPagamento requisicaoPagamento = new RequisicaoPagamento("Credit Card", "123456789", "100.00");
        Pagamento pagamento = new Pagamento("Credit Card", "123456789", "100.00", true);

        when(solicitarPagamento.solicitarPagamento(any(RequisicaoPagamento.class))).thenReturn(pagamento);

        ResponseEntity<?> responseEntity = pagamentosController.efetuarPagamento(requisicaoPagamento);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(((RequisicaoPagamento) responseEntity.getBody()).getStatus()).isEqualTo("APROVADO");
    }

    @Test
    public void testEfetuarPagamentoException() {
        RequisicaoPagamento requisicaoPagamento = new RequisicaoPagamento("Credit Card", "123456789", "100.00");

        when(solicitarPagamento.solicitarPagamento(any(RequisicaoPagamento.class))).thenThrow(new IllegalArgumentException());

        ResponseEntity<?> responseEntity = pagamentosController.efetuarPagamento(requisicaoPagamento);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(400);
        assertThat(responseEntity.getBody()).isEqualTo("Parametros Incorretos");
    }
}