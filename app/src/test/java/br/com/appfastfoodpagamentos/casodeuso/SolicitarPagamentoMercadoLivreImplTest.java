package br.com.appfastfoodpagamentos.casodeuso;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import br.com.appfastfoodpagamentos.apresentacao.requisicao.RequisicaoPagamento;
import br.com.appfastfoodpagamentos.dominio.Pagamento;
import br.com.appfastfoodpagamentos.infraestrutura.AdaptadorMercadoLivre;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SolicitarPagamentoMercadoLivreImplTest {
    @InjectMocks
    SolicitarPagamentoMercadoLivreImpl solicitarPagamentoMercadoLivreImpl;

    @Mock
    AdaptadorMercadoLivre adaptadorMercadoLivre;

    @Test
    public void testSolicitarPagamento() {
        RequisicaoPagamento requisicaoPagamento = new RequisicaoPagamento("Credit Card", "123456789", "100.00");
        Pagamento pagamentoAEfetuar = new Pagamento("Credit Card", "123456789", "100.00", false);

        when(adaptadorMercadoLivre.pagarComQrCode(any(Pagamento.class))).thenReturn(true);

        Pagamento resultado = solicitarPagamentoMercadoLivreImpl.solicitarPagamento(requisicaoPagamento);

        assertThat(resultado.getMeioPagamento()).isEqualTo(pagamentoAEfetuar.getMeioPagamento());
        assertThat(resultado.getIdMeioPagamento()).isEqualTo(pagamentoAEfetuar.getIdMeioPagamento());
        assertThat(resultado.getValor()).isEqualTo(pagamentoAEfetuar.getValor());
        assertThat(resultado.getAprovado()).isTrue();
    }
}
