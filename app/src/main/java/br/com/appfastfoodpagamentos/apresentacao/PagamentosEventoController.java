package br.com.appfastfoodpagamentos.apresentacao;

import br.com.appfastfoodpagamentos.apresentacao.requisicao.MensagemSNS;
import br.com.appfastfoodpagamentos.apresentacao.requisicao.RequisicaoPagamento;
import br.com.appfastfoodpagamentos.casodeuso.SolicitarPagamento;
import br.com.appfastfoodpagamentos.dominio.Pagamento;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/efetua-pagamento")
public class PagamentosEventoController {

    private SolicitarPagamento solicitarPagamento;
    public PagamentosEventoController(SolicitarPagamento solicitarPagamento){
        this.solicitarPagamento =  solicitarPagamento;
    }

    @PostMapping
    public ResponseEntity<?> efetuarPagamento(@RequestBody String notification) {

        ObjectMapper mapper = new ObjectMapper();
        MensagemSNS mensagemSNS;

        try {
            mensagemSNS = mapper.readValue(notification, MensagemSNS.class);

            switch (mensagemSNS.getType()) {
                case "SubscriptionConfirmation":
                    // Lógica para confirmar a inscrição
                    String subscribeURL = mensagemSNS.getSubscribeURL();
                    System.out.println("Received subscription confirmation request. URL: " + subscribeURL);
                    HttpClient client = HttpClient.newHttpClient();
                    URI uri = URI.create(subscribeURL);
                    System.out.println("PATH URL: " + uri.getPath());
                    HttpRequest request = HttpRequest.newBuilder()
                            .uri(URI.create(subscribeURL))
                            .GET() // Método GET para confirmar a inscrição
                            .build();
                    try {
                        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                        if (response.statusCode() == 200) {
                            System.out.println("Subscription confirmed successfully.");
                        } else {
                            System.out.println("Failed to confirm subscription. Response code: " + response.statusCode());
                        }
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                        System.out.println("Error confirming subscription: " + e.getMessage());
                        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no serviço");
                    }

                    break;
                case "Notification":
                    // Lógica para tratar mensagens recebidas
                    System.out.println("Received message: " + mensagemSNS.getMessage());
                    solicitarPagamento.solicitarPagamento(mensagemSNS.getMessage());
                    break;
                case "UnsubscribeConfirmation":
                    // Lógica para tratar confirmações de cancelamento de inscrição
                    System.out.println("Unsubscribed from topic");
                    break;
                default:
                    System.out.println("Unknown message type: " + mensagemSNS.getType());
                    break;
            }
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception j) {
            throw new RuntimeException(j);
        }
    }

}
