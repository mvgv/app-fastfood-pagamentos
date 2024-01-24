package br.com.appfastfoodpagamentos.stepdefinitions;
import br.com.appfastfoodpagamentos.AppFastfoodPagamentosApplication;
import br.com.appfastfoodpagamentos.config.CucumberSpringConfiguration;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Entao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.UnsupportedEncodingException;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes=AppFastfoodPagamentosApplication.class)
@CucumberContextConfiguration

@AutoConfigureMockMvc
public class PagamentoStepDefinitions  {
    @Autowired
    private MockMvc mockMvc;

    private String requestBody;
    private MockHttpServletResponse response;

    @Dado("que eu tenho uma requisicao de pagamento valida com valor de {double}")
    public void que_eu_tenho_uma_requisicao_de_pagamento_valida_com_valor_de(Double double1)  throws Exception{
        requestBody = "{ \"valor\": 15.00 }";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/pagamentos")
                        .content(requestBody)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)) // Adiciona o cabeçalho aqui
                .andReturn();
        response = mvcResult.getResponse();
    }

    @Quando("eu tento efetuar um pagamento")
    public void eu_tento_efetuar_um_pagamento() throws Exception {
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/pagamentos")
                        .content(requestBody)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)) // Adiciona o cabeçalho aqui
                .andReturn();
        response = mvcResult.getResponse();
    }

    @Entao("o pagamento deve ser aprovado")
    public void o_pagamento_deve_ser_aprovado() throws UnsupportedEncodingException {
        String status = "APROVADO";
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        String responseBody = response.getContentAsString();
        assertEquals(true, responseBody.contains(status));
    }

    @Dado("que eu tenho uma requisição de pagamento invalida com valor de {double}")
    public void que_eu_tenho_uma_requisição_de_pagamento_invalida_com_valor_de(Double double1) throws Exception {
        requestBody = "{ \"valor\": 5.00 }";
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/pagamentos")
                        .content(requestBody)
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)) // Adiciona o cabeçalho aqui
                .andReturn();
        response = mvcResult.getResponse();
    }

    @Entao("devo receber uma resposta de erro")
    public void devo_receber_uma_resposta_de_erro() throws UnsupportedEncodingException {
        String status = "REPROVADO";
        String responseBody = response.getContentAsString();
        assertEquals(HttpStatus.OK.value(), response.getStatus());
        assertEquals(true, responseBody.contains(status));
    }
}
