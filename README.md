# Site Universidade Horizon

Este é um projeto desenvolvido de criar o site **Universidade Horizon** como um estudo e desafio da Accenture, com o objetivo de criar um sistema simples de login, registro, e gerenciamento de informações dos alunos. O sistema permite aos alunos registrar suas informações, editar seu perfil e visualizar os dados após o login.

## Tecnologias Usadas

- **Spring Boot**: Framework principal para a construção de aplicações Java de forma rápida e fácil.
- **Spring Security**: Para a implementação de autenticação e controle de acesso no sistema.
- **Thymeleaf**: Motor de templates para a renderização das páginas HTML.
- **Bootstrap 5**: Framework de CSS para criar interfaces web responsivas e modernas.
- **H2 Database**: Banco de dados em memória usado para facilitar o armazenamento de dados durante o desenvolvimento.
- **BCrypt**: Algoritmo utilizado para criptografar as senhas dos usuários.
- **jQuery**: Biblioteca JavaScript para manipulação de eventos e interações no lado do cliente.

## Funcionalidades

- **Login de aluno**: Permite que o aluno faça login utilizando seu nome e senha.
- **Registro de aluno**: Alunos podem se registrar com seus dados pessoais e uma senha criptografada.
- **Edição de perfil**: Os alunos podem editar seus dados, como nome, curso e cidade.
- **Visualização do perfil**: Após o login, o aluno é redirecionado para a página de boas-vindas, onde pode visualizar suas informações.
- **Exclusão de registro**: O aluno pode excluir sua conta, removendo os dados do banco de dados.

## Estrutura de URLs

Principais caminhos de URL e o que cada um faz:

1. **/login**
   - Página de login onde os alunos podem inserir suas credenciais para acessar a plataforma.

2. **/bemVindo/{id}**
   - Página de boas-vindas, acessada após o login. Exibe as informações do aluno que se logou.

3. **/registrar**
   - Página para o aluno se registrar na plataforma. O aluno deve preencher informações como nome, curso, e senha.

4. **/editar/{id}**
   - Página onde o aluno pode editar suas informações, como nome, curso, CEP e cidade.

5. **/deletar/{id}**
   - URL que permite a exclusão de um aluno do sistema, removendo suas informações do banco de dados.

## Como Rodar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/usuario/site-universidade.git
