package br.com.appfastfoodpagamentos.infraestrutura;

import br.com.appfastfoodpagamentos.dominio.Pagamento;

public class AdaptadorMercadoLivre {

    public Boolean pagarComQrCode(Double valor) {
        if (valor >= 10 )
            return Boolean.TRUE;
        return Boolean.FALSE;
    }
}
