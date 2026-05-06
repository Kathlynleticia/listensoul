# 🎵 Listensoul

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/status-finalizado-green?style=for-the-badge)

## Sobre o projeto

O **Listensoul** é uma aplicação baseada em console que permite:

- Cadastrar artistas
- Cadastrar músicas associadas a um artista
- Listar músicas cadastradas
- Buscar músicas por artístas

A aplicação utiliza o Spring Boot para configuração e gerenciamento do projeto, e o Spring Data JPA para realizar a persistência dos dados.

Embora a interação principal ocorra via terminal, o projeto também disponibiliza o **console web do H2** para visualização do banco de dados em ambiente de teste.

## Tecnologias utilizadas

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- H2 Database
- PostgreSQL
- Maven

---

## Arquitetura

A aplicação segue uma arquitetura em camadas simples, separando dados, regras de negócio e interface.
O projeto segue uma estrutura baseada em:

- **model** → classes que representam os dados (Artista, Musica, TipoArtista)  
- **repository** → acesso ao banco de dados (Spring Data JPA)  
- **service** → lógica da aplicação e tratamento de dados  
- **principal** → interação com o usuário via terminal  
- **application** → inicialização do projeto com Spring Boot  

---

## Banco de dados
### Atenção Uso de Profiles

Este projeto utiliza **Spring Profiles** para alternar entre diferentes configurações de ambiente.

Existem os seguintes arquivos de configuração:

- `application.properties` → responsável por definir qual profile está ativo  
- `application-test.properties` → configuração para ambiente de teste (H2 em memória)  
- `application-dev.properties` → configuração para ambiente de desenvolvimento (H2 em arquivo)
- `application-prod.properties` → configuração para ambiente de produção (H2 em arquivo)

Como funciona

A ativação do profile é feita no arquivo principal:

```bash
spring.profiles.active=test
```
Atualmente, o projeto está configurado por padrão com o profile **`test`**, que utiliza o banco H2 em memória. <br>
Se você quiser utilizar o H2 em modo arquivo (com persistência de dados) ou o PostgreSQL, basta alterar o profile ativo no `application.properties`:

---

### A aplicação suporta dois tipos de banco:

## H2 (ambiente de teste)

- Banco em memória ou arquivo
- Console disponível via navegador

⚠️ Atenção ao uso do H2

O projeto oferece duas opções de uso do banco de dados **H2**:

- **Modo em memória**
- **Modo em arquivo**

É muito importante que as configurações utilizadas no **console do H2 (via navegador)** estejam **de acordo com o arquivo de configuração ativo no projeto**.

### H2 em memória (Os dados **não são persistidos**. Sempre que rodar a aplicação, os dados se perdem.)
- Utiliza as configurações do `application-test.properties`
- A URL geralmente segue o padrão:
```bash
jdbc:h2:mem:testdb
```

### H2 em arquivo (Os dados são salvos em arquivo, se mantendo mesmo após rodar a aplicação.)
- Utiliza as configurações do `application-dev.properties`
- A URL segue um padrão de arquivo, por exemplo: 
```bash
jdbc:h2:file:./data/listensouldb
```
### Em seu navegador acesse:

```bash
http://localhost:8080/h2-console
```
⚠️ Porta da aplicação

É importante saber em qual porta a aplicação está rodando para acessar o console do H2 no navegador.

Por padrão, o Spring Boot utiliza: http://localhost:8080 <br>
Mas essa porta pode variar. Para descobrir a porta,  ao iniciar a aplicação, verifique o terminal/log. Procure por uma mensagem do Tomcat semelhante a:

*Tomcat started on port(s): 8080* <br>
O número exibido será a porta da aplicação.


### Configurações padrão:

- JDBC URL: *de acordo com o application properties*
- User: sa
- Password: (vazio)

---

## Importante

As configurações inseridas no console do H2 **devem ser exatamente iguais** às definidas no `application.properties` correspondente.

Caso contrário:
- o banco pode não conectar
- ou será criado um banco diferente do esperado

Sempre verifique:
- URL do banco (`JDBC URL`)
- nome do banco
- tipo (memória ou arquivo)

antes de acessar o console.

---

## PostgreSQL (produção/local)

Permite persistência real dos dados.
Caso queira utilizar o PostgreSQL em vez do H2, é necessário configurar corretamente o arquivo `application-prod.properties`.
Nesse arquivo, você deve inserir as informações do seu banco de dados, como:

- URL de conexão  
- Usuário  
- Senha

A configuração do PostgreSQL no projeto está definida utilizando **variáveis de ambiente** no arquivo `application-prod.properties`.

Isso significa que os dados sensíveis (como usuário e senha) **não estão diretamente no código**, o que é a forma mais recomendada. <br>
Para que a aplicação funcione corretamente, você deve:

- Criar as variáveis de ambiente com **os mesmos nomes definidos no projeto**:
  - `DB_URL`
  - `DB_USERNAME`
  - `DB_PASSWORD`

 Alternativa: 
 Caso prefira usar nomes diferentes para as variáveis:
 - Será necessário **alterar também o `application-prod.properties`** para refletir os novos nomes.

### Dúvidas sobre variáveis de ambiente?

Caso tenha dúvidas sobre como criar ou configurar variáveis de ambiente, você pode consultar um outro projeto onde explico esse processo passo a passo.

🔗 [Acesse aqui o guia completo](https://github.com/Kathlynleticia/conversor-de-moedas/tree/main)

---

## Como executar o projeto

### Pré-requisitos

- Java 17+ (ou versão usada no projeto)
- Maven

---

### Executando

1. Clone o repositório:
```bash
git clone https://github.com/Kathlynleticia/listensoul
```
2. Abra o projeto em sua IDE
Recomendado: IntelliJ IDEA

3. Execute a classe principal
Rode a classe Main para iniciar o programa.

## Uso da aplicação

Ao executar, o sistema apresentará um menu no terminal com opções como:

- Cadastrar artistas
- Cadastrar músicas associadas a um artista
- Listar músicas cadastradas
- Buscar músicas por artístas
- Sair

As operações são realizadas via entrada de dados pelo usuário.

--- 

### Observações
- A aplicação não implementa um CRUD completo (não possui update/delete).
- Utiliza queries (JPQL ou derivadas) para acesso aos dados.
- O Spring Boot Web é utilizado apenas para suporte ao console do H2, não sendo uma API REST.

### Possíveis melhorias
- Implementar API REST com controllers
- Adicionar operações de update e delete
- Criar interface gráfica (web ou desktop)
- Validações de dados
- Tratamento de exceções
  
## 🙋🏻 Autora

Kathlyn Santos
