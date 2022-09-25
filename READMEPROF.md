# Sistema Biblioteca

Sistema de biblioteca implementado para a disciplina de Programação Orientada a Objetos Avançada com Heroku.


O arquivo [Application.java](src/main/java/app/Aplication.java) atua como a main do sistema e configura as rotas para os controllers. Todas as operações podem ser acessadas via rest com a url disponibilizada pelo heroku antes dos paths definidos em Application.java (Exemplo: GET https://pooa-sist-biblioteca.herokuapp.com/api/usuario/leitores retorna todos os leitores)

A integração com os outros grupos pode ser vista em [Integracao.java](src/main/java/app/Integracao/Integracao.java)

Testes podem ser feitos usando thunder-client para vscode com os arquivos em [thunder-tests](thunder-tests)