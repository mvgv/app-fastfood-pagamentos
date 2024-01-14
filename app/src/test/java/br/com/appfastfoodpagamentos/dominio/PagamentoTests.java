import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import br.com.appfastfoodpagamentos.dominio.Pagamento;
import org.junit.jupiter.api.Test;
public class PagamentoTests {
    @Test
    public void testPagamentoConstructor() {
        Pagamento pagamento = new Pagamento("Credit Card", "123456789", "100.00", true);

        assertThat(pagamento.getId()).isNotNull();
        assertThat(pagamento.getMeioPagamento()).isEqualTo("Credit Card");
        assertThat(pagamento.getIdMeioPagamento()).isEqualTo("123456789");
        assertThat(pagamento.getValor()).isEqualTo(100.00);
        assertThat(pagamento.getAprovado()).isTrue();
    }

    @Test
    public void testValorConsistente() {
        assertThatThrownBy(() -> new Pagamento("Credit Card", "123456789", "-100.00", true)).isInstanceOf(IllegalArgumentException.class);
    }
}
