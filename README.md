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

---

## Visualizando e testando a API

Para criação e execução da API REST usamos o Thunder Client. Segue um curto guia de como rodar no seu computador após clonar o repositório:

1. Com o VSCode instalado, baixe a extensão Thunder Client

2. Para utilizar os casos de testes disponíveis nesse repositório, procure a configuração `Thunder-client: Save To Workspace` e tenha certeza que está abrindo o Thunder Client na pasta raiz do projeto

3. As requisições de teste devem estar disponíveis na aba Thunder Client -> Collections -> Sistema Biblioteca
