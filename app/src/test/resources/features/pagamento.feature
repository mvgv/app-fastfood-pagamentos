# language: pt
Funcionalidade: Pagamentos

  Cenário: Efetuar um pagamento bem-sucedido
  Dado que eu tenho uma requisicao de pagamento valida
  Quando eu tento efetuar um pagamento
  Então o pagamento deve ser aprovado

  Cenário: Efetuar um pagamento com parametros incorretos
  Dado que eu tenho uma requisição de pagamento invalida
  Quando eu tento efetuar um pagamento
  Então devo receber uma resposta de erro