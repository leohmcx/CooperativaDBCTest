# CooperativaDBCTest

![Alt Text](https://github.com/leohmcx/cooperativadbctest/blob/main/logico.jpg)

- API Backend restful para gerenciar votos de pautas de uma cooperativa

- Projeto utiliza as tecnologias Java 8, Spring boot, web, data jpa, jdbc, swagger2, swagger-UI e h2 (DB).

- Projeto é baseado no modelo maven de dependências.

# Configuração e execução da API

1: Instale JDK java 8 -> https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html

2: Instale o Maven -> https://maven.apache.org/download.cgi

3: Instale o Lombok no eclipse, instruções: https://projectlombok.org/setup/eclipse

4: Configurados, na pasta do projeto pelo terminal execute os comandos: [mvn clean install -Dspring.profiles.active=dev] e [mvn spring-boot:run]

5: Rodar pelo eclipse é necessário configurar o VM Arguments adicionando a seguinte configuração: -Dspring.profiles.active=dev

- A API estará disponível no link (local) -> http://localhost:8080/
- End-points criados disponível para teste em (local) -> http://localhost:8080/swagger-ui.html#/
- Banco de dados utilizado em memória após a execução do API -> http://localhost:8080/h2-console -> url jdbc:h2:mem:cooperativa