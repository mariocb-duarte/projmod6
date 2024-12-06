
# Projeto: Testes Automatizados para API REST

## Descrição

Este projeto tem como objetivo criar testes automatizados para uma API REST, utilizando diferentes tipos de testes que compõem a **pirâmide de testes**:

-   **Testes Unitários**: Base para garantir que componentes individuais funcionam como esperado.
-   **Testes de Integração**: Garantem que os componentes funcionam corretamente em conjunto. Utilizaremos o **Wiremock** para simular dependências externas.
-   **Testes End-to-End (E2E)**: Validam o comportamento completo da API do ponto de vista do usuário. Será utilizado **Cucumber** em conjunto com **RestAssured** ou **WebClientTest**.

## Tecnologias Utilizadas

-   **Java**: Linguagem principal para desenvolvimento e testes.
-   **JUnit**: Framework para testes unitários.
-   **Wiremock**: Ferramenta para simular APIs externas durante os testes de integração.
-   **RestAssured/WebClientTest**: Para realizar chamadas HTTP nos testes E2E.
-   **Cucumber**: Framework de BDD para escrever cenários de testes End-to-End.
-   **Maven/Gradle**: Gerenciamento de dependências e build.

----------

## Estrutura do Projeto

```
src
├── main
│   └── java
│       └── br.com.suapackage
│           └── api
│               ├── controllers
│               ├── services
│               └── models
└── test
    ├── unit
    ├── integration
    └── e2e
```



## Configuração do Ambiente de Testes

1.  **Pré-requisitos**:
    
    -   Java 17 ou superior instalado.
    -   Maven/Gradle configurado.
    -   Um ambiente local para simular as chamadas (como Docker, se necessário).
2.  **Configuração do Wiremock**:
    
    -   Inicie o Wiremock no ambiente local:
        
        bash
        
        Copiar código
        
        `java -jar wiremock-standalone-x.x.x.jar --port 8089` 
        
    -   Configure os stubs em `src/test/resources/wiremock`.
3.  **Configuração para Testes End-to-End**:
    
    -   Crie um arquivo `.env` na raiz do projeto com as variáveis de ambiente necessárias, como URL base da API e credenciais de acesso.
    -   Execute o comando para rodar os testes:
      
        
        `mvn test -Dtest=RunCucumberTest` 
        

----------

## Como Executar os Testes

1.  **Testes Unitários**:
    
    -   Execute os testes unitários com o comando:
        
             
        `mvn test -Dtest=UnitTest` 
        
2.  **Testes de Integração**:
    
    -   Certifique-se de que o Wiremock está em execução.
    -   Rode os testes de integração:
            
        `mvn test -Dtest=IntegrationTest` 
        
3.  **Testes End-to-End**:
    
    -   Configure o ambiente conforme instruções anteriores.
    -   Execute os testes funcionais:
        
        `mvn test -Dtest=RunCucumberTest` 
        



