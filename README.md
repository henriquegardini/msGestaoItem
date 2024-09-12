# Microserviço responsável pela parte de gestão de itens do sistema.

## Descrição

O projeto **msGestaoItem** é um microserviço dedicado à gestão de itens utilizados no no carrinho de compras. Desenvolvido usando o framework Spring Boot, o sistema fornece funcionalidades para gerenciar os itens, tais como buscar os itens cadastrados, buscar um item pelo identificador único, criar um item, atualizar um item e excluir um item.

## Funcionalidades

- **Buscar os itens:** Consulta de todos os itens cadastrados na base.
- **Criação de item:** Cadastro de um novos item, utilizando um produto com preço cadastrado.
- **Buscar um item:** Consulta, pelo id (identificador único), dos dados de um item cadastrado na base.
- **Atualizar um item:** Atualizar os dados de um item cadastrado na base.
- **Exclusão do item:** Exclusão dos dados de um item cadastrado na base.

## Requisitos

- Java 17+
- Spring Boot 3.x
- Maven 3.x
- MongoDB

## Estrutura do Projeto

- **Controller:** Camada responsável por gerenciar as requisições HTTP relacionadas ao item, buscando os itens, criando , buscando pelo id, atualizando e excluindo um item.
- **Exception:** Implementação de tratamento de exceções personalizadas, como o ItemNotFoundException e PrecoItemNotFoundException, para fornecer mensagens claras e informativas sobre erros relacionados à gestão de itens.
- **Feign:** Implementação da integração com o msGestaoPreco. 
- **Model:** Camada que define a estrutura de dados para a entidade Item e Produto, incluindo atributos como identificador do produto, descrição do item, quantidade e preço total.
- **Repository:** Camada de acesso a dados utilizando Reactive Crud Repository, responsável pela persistência e recuperação de informações sobre os itens no banco de dados.
- **Service:** Camada onde está a lógica de negócio para o gerenciamento de item, buscando os itens, criando , buscando pelo id, atualizando e excluindo um item. O microserviço msGestaoPreco é invocado para validar se o produto existe e buscar o preço unitário para utiliza-lo, multiplicando pela quantidade e calculando do preço total.

## Configuração

1. Clone o repositório:

   ```bash
   git clone https://github.com/henriquegardini/msGestaoItem.git
    ```

2. Accesse o diretório do projeto:

   ```bash
   cd msGestaoItem
   ```

3. Instale as dependências do Maven:

   ```bash
   mvn clean install
   ```

4. Configure o banco de dados no arquivo application.properties ou application.yml.

5. Execute a aplicação:

   ```bash
   mvn spring-boot:run
   ```

## Uso

### Endpoints disponíveis:

- **GET /item**

  Recupera os itens cadastrados na base de dados. Retorna o item no formato `Item` .

- **GET /item/{idItem}**

  Recupera os dados de um item específico com base no identificador do item cadastrado na base de dados. Retorna o item no formato `Item` .

- **POST /item**

  Cadastro de um item. Recebe um objeto `Item` no corpo da requisição e retorna o item cadastrado.

- **PATCH /item**

  Atualização de um item. Recebe um objeto `Item` no corpo da requisição e retorna o item atualizado.

- **DELETE /item/{idItem}**

  Exclui um produto específico com base no identificador do produto. Retorna uma mensagem de sucesso confirmando a exclusão do preço.


### Exemplo de Requisição:
**Cadastrar ou atualizar preco do produto**:
```bash
  curl -X POST http://localhost:8081/item \
     -H "Content-Type: application/json" \
     -d '{
	      "idProduto": 21,
	      "descricao": "Camiseta Corinthians 2024/2025",
	      "quantidade": 1,
	      "precoTotal": 150
          }'
```

**Obter os itens cadastrados na baseum item ( Obs: para executar favor criar o produto primeiro)**:
```bash
curl -X GET http://localhost:8081/item
```

**Obter um item ( Obs: para executar favor criar o produto primeiro)**:
```bash
curl -X GET http://localhost:8081/item/99
```


**Deletar preco Produto**:
```bash
curl -X DELETE http://localhost:8081/item/99
```
