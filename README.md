# Sistema Biblioteca

Sistema de biblioteca implementado para a disciplina de Programação Orientada a Objetos Avançada.

Acesse o README versão do professor em [READMEPROF.md](READMEPROF.md)

1. Instalar o Heroku CLI
curl https://cli-assets.heroku.com/install.sh | sh

2. Compile e Deploy
mvn clean compile assembly:single && mvn heroku:deploy
Ele deve pedir autenticação com o heroku, logue no navegador

3. Acesse em https://pooa-sist-biblioteca.herokuapp.com

4. Acesse o terminal do servidor/heroku com
heroku logs --tail -a pooa-sist-biblioteca
