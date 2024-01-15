Funcionalidade: Pagamentos

  Cenário: Efetuar um pagamento bem-sucedido
  Dado que eu tenho uma requisição de pagamento válida
  Quando eu tento efetuar um pagamento
  Então o pagamento deve ser aprovado

  Cenário: Efetuar um pagamento com parâmetros incorretos
  Dado que eu tenho uma requisição de pagamento inválida
  Quando eu tento efetuar um pagamento
  Então devo receber uma resposta de erro