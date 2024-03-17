@echo off

echo ### Criando Topicos!
@REM aws --endpoint-url=http://localhost:4566/ sns create-topic --name fecha-carrinho
@REM aws --endpoint-url=http://localhost:4566/ sns create-topic --name carrinho-fechado
@REM aws --endpoint-url=http://localhost:4566/ sns create-topic --name pagamento-efetuado
@REM aws --endpoint-url=http://localhost:4566/ sns create-topic --name pagamento-recusado
@REM aws --endpoint-url=http://localhost:4566/ sns create-topic --name cria-pedido
@REM aws --endpoint-url=http://localhost:4566/ sns create-topic --name pedido-criado

echo ### Criando Subscricao!

@REM aws --endpoint-url=http://localhost:4566/ sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:pagamento-efetuado --protocol http --notification-endpoint http://fastfood:8888/pagamento-efetuado
@REM aws --endpoint-url=http://localhost:4566/ sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:pagamento-recusado --protocol http --notification-endpoint http://fastfood:8888/pagamento-recusado
@REM aws --endpoint-url=http://localhost:4566/ sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:fecha-carrinho --protocol http --notification-endpoint http://fastfood-produtos:8081/carrinho-eventos/fecharCarrinho
@REM aws --endpoint-url=http://localhost:4566/ sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:cria-pedido --protocol http --notification-endpoint http://fastfood:8888/cria-pedido
@REM aws --endpoint-url=http://localhost:4566/ sns subscribe --topic-arn arn:aws:sns:us-east-1:000000000000:pedido-criado --protocol http --notification-endpoint http://fastfood:8888/pedido-criado
