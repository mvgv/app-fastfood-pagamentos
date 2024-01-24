# language: pt
Funcionalidade: Pagamentos

  Cenário: Efetuar um pagamento bem-sucedido com valor maior que 10
    Dado que eu tenho uma requisicao de pagamento valida com valor de 15.00
    Quando eu tento efetuar um pagamento
    Então o pagamento deve ser aprovado

  Cenário: Efetuar um pagamento bem-sucedido com valor igual a 10
    Dado que eu tenho uma requisicao de pagamento valida com valor de 10.00
    Quando eu tento efetuar um pagamento
    Então o pagamento deve ser aprovado

  Cenário: Efetuar um pagamento mal-sucedido com valor menor que 10
    Dado que eu tenho uma requisição de pagamento invalida com valor de 5.00
    Quando eu tento efetuar um pagamento
    Então devo receber uma resposta de erro