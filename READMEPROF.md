# Sistema Biblioteca

Sistema de biblioteca implementado para a disciplina de Programação Orientada a Objetos Avançada com Heroku.


O arquivo [Application.java](src/main/java/app/Aplication.java) atua como a main do sistema e configura as rotas para os controllers. Todas as operações podem ser acessadas via rest com a url disponibilizada pelo heroku antes dos paths definidos em Application.java (Exemplo: GET https://pooa-sist-biblioteca.herokuapp.com/api/usuario/leitores retorna todos os leitores)

A integração com os outros grupos pode ser vista em [Integracao.java](src/main/java/app/Integracao/Integracao.java)



## Visualizando e testando a API

Para criação e execução da API REST usamos o Thunder Client. Segue um curto guia de como rodar no seu computador após clonar o repositório:

1. Com o VSCode instalado, baixe a extensão Thunder Client

2. Para utilizar os casos de testes disponíveis nesse repositório, procure a configuração `Thunder-client: Save To Workspace` e tenha certeza que está abrindo o Thunder Client na pasta raiz do projeto

3. As requisições de teste devem estar disponíveis na aba Thunder Client -> Collections -> Sistema Biblioteca
