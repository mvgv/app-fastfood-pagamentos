package br.com.appfastfoodpagamentos.infraestrutura;

import br.com.appfastfoodpagamentos.dominio.Pagamento;

public class AdaptadorMercadoLivre {

    public Boolean pagarComQrCode(Pagamento pagamento) {
        return Boolean.FALSE;
    }
}
