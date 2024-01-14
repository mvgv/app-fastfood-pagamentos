package br.com.appfastfoodpagamentos.infraestrutura;

import br.com.appfastfoodpagamentos.dominio.Pagamento;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AdaptadorMercadoLivreTest {
    @Test
    public void testPagarComQrCode() {
        AdaptadorMercadoLivre adaptadorMercadoLivre = new AdaptadorMercadoLivre();
        Pagamento pagamento = new Pagamento("Credit Card", "123456789", "100.00", true);

        Boolean resultado = adaptadorMercadoLivre.pagarComQrCode(pagamento);

        assertThat(resultado).isFalse();
    }
}
