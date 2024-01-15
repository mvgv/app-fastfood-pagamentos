package br.com.appfastfoodpagamentos.stepdefinitions;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class PagamentoStepDefinitions {
    @Autowired
    private MockMvc mockMvc;

    private String requestBody;
    private MockHttpServletResponse response;

    @Dado("eu tenho uma requisição de pagamento válida")
    public void eu_tenho_uma_requisicao_de_pagamento_valida() throws Exception {
        requestBody = "{ \"valor\": 100.00 }";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/pagamentos").content(requestBody))
                .andReturn();
        response = mvcResult.getResponse();
    }

    @Quando("eu tento efetuar um pagamento")
    public void eu_tento_efetuar_um_pagamento() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/pagamentos").content(requestBody))
                .andReturn();
        response = mvcResult.getResponse();
    }

    @Entao("o pagamento deve ser aprovado")
    public void o_pagamento_deve_ser_aprovado() {
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }

    @Dado("eu tenho uma requisição de pagamento inválida")
    public void eu_tenho_uma_requisicao_de_pagamento_invalida() throws Exception {
        requestBody = "{ \"valor\": -100.00 }";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/pagamentos").content(requestBody))
                .andReturn();
        response = mvcResult.getResponse();
    }

    @Entao("devo receber uma resposta de erro")
    public void devo_receber_uma_resposta_de_erro() {
        assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatus());
    }
}
