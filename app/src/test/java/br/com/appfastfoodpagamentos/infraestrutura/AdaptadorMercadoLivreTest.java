package br.com.appfastfoodpagamentos.infraestrutura;

import br.com.appfastfoodpagamentos.dominio.Pagamento;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AdaptadorMercadoLivreTest {

    @Test
    public void testPagarComQrCode_valorMenorQue10() {
        AdaptadorMercadoLivre adaptadorMercadoLivre = new AdaptadorMercadoLivre();
        Pagamento pagamento = new Pagamento("Credit Card", "123456789", "5.00", true);

        Boolean resultado = adaptadorMercadoLivre.pagarComQrCode(pagamento);

        assertThat(resultado).isFalse();
    }

    @Test
    public void testPagarComQrCode_valorIgualA10() {
        AdaptadorMercadoLivre adaptadorMercadoLivre = new AdaptadorMercadoLivre();
        Pagamento pagamento = new Pagamento("Credit Card", "123456789", "10.00", true);

        Boolean resultado = adaptadorMercadoLivre.pagarComQrCode(pagamento);

        assertThat(resultado).isTrue();
    }

    @Test
    public void testPagarComQrCode_valorMaiorQue10() {
        AdaptadorMercadoLivre adaptadorMercadoLivre = new AdaptadorMercadoLivre();
        Pagamento pagamento = new Pagamento("Credit Card", "123456789", "15.00", true);

        Boolean resultado = adaptadorMercadoLivre.pagarComQrCode(pagamento);

        assertThat(resultado).isTrue();
    }
}
