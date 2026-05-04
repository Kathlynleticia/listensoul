# 🎵 Listensoul

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Status](https://img.shields.io/badge/status-finalizado-green?style=for-the-badge)

## 📖 Sobre o projeto

O **Listensom** é uma aplicação baseada em console que permite:

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

A aplicação suporta dois tipos de banco:

### 🔹 H2 (ambiente de teste)

- Banco em memória ou arquivo
- Console disponível via navegador

⚠️ Atenção ao uso do H2

O projeto oferece duas opções de uso do banco de dados **H2**:

- **Modo em memória**
- **Modo em arquivo**

É muito importante que as configurações utilizadas no **console do H2 (via navegador)** estejam **de acordo com o arquivo de configuração ativo no projeto**.

### 🔹 H2 em memória (Sempre que rodar a aplicação, os dados e perdem.)
- Utiliza as configurações do `application-test.properties`
- A URL geralmente segue o padrão:
```bash
jdbc:h2:mem:testdb
```
### 🔹 H2 em arquivo (Os dados são salvos em arquivo, se mantendo mesmo após rodar a aplicação.)
- Utiliza as configurações do `application-dev.properties`
- A URL segue um padrão de arquivo, por exemplo: 
```bash
jdbc:h2:file:./data/listensouldb
```

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

## Dica
Se algo não aparecer no H2, provavelmente você está conectado em **outro banco diferente do que a aplicação está usando**.
