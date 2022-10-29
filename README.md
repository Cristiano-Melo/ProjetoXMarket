<div>
  <img src="https://user-images.githubusercontent.com/90655270/161388302-145d58d6-723a-4dc1-97e7-80133dfa4c3a.png" width="100px">
  <img src="https://drive.google.com/file/d/1fdJ7YgEwnUTicbgYwQj08-ZO8nlX4Ft8/view?usp=sharing" width="100px">
</div>

<h1 align="center">Xmarket</h1>

<p align="center">

A empresa XMarket lançou um novo serviço de vendas online de produtos e deseja gerar relatórios de testes de unidade relacionados a parte de cadastro de Clientes.
O desafio consiste em construir na linguagem Java a parte operacional da aplicação . Após isso, a equipe deverá executar, no mínimo, dois testes estruturais demonstrando a estabilidade, segurança e confiabilidade desse cadastro.

✨ Sistema
===============

O sistema foi desenvolvido em Java e seus componentes gráficos criados através do GUI WindowBuilder, um banco de dados relacional foi criado através do MySQL. Os relatórios foram todos desenvolvidos através do Jasper Reports.

🏗️ Desenvolvedores
=================

- [x] André Augusto Xavier de Miranda
- [x] Agnaldo Machado
- [x] Cristiano Daniel Nascimento de Melo 
- [x] Weslley J. Leles

Manual do Usuário: 
https://drive.google.com/file/d/1Do23JUCu7TydSuCpNaSqobHPlCttbyfi/view?usp=sharing

📝 Features
=====================
* Login
* Clientes CRUD
* Produtos CRUD
* Marcas CRUD
* Pedidos CRUD
* Integração com API dos correios para autopreenchimento do CEP.
* Validação de CPF.

🚀 Tecnologias
=================

<table>
<tr>
<td>Java 17</td>
<td>Eclipse IDE</td>
<td>Window Builder</td>
<td>MySQL Server</td>
<td>Jasper Reports</td>
<td>bcrypt</td>
<td>jsonwebtoken</td>
<td>class-validator</td>
<td>reflect-metadata</td>
</tr>
</table>

## Projeto Local

Para executar o projeto localmente, você precisará do Git, Eclipse ( ou outra IDE compatível) e o MySQL instalado em seu computador.

Acesse seu MySQL e crie um database com o seguinte Script:

https://drive.google.com/file/d/1G-kym_S7yMAz70DYHaDiwgAL2w_uupC7/view?usp=sharing

Depois de clonar o projeto, será necessário editar o arquivo de conexão com o banco de dados, no caminho ../MenuJdesktop/src/Conexao/Dao/ConectaBancoDao.java e inserir os dados de conexão ( caminho do servidor na linha 19, usuário na linha 20 e senha na linha 21)

```bash
# Clone este repositório
$ https://github.com/Cristiano-Melo/ProjetoXMarket


📇 Manual do Usuário
=================

#### Acesso ao Sistema

```http
  O acesso ao sistema


Ao executar o atalho para acesso ao sistema, será apresentada a tela, conforme imagem acima. Para acesso é necessário ter um usuário e senha previamente registrados no sistema. 

```

Json example:

```
{
    "name":"User Test", 
    "email": "testuser@roda.com.br",
    "password": "123456", 
    "bio": "Hi Im User!", 
    "imgurl": "https://urlimg.com.br" 
}  
```

#### Login

```http
  POST http://roda-api.herokuapp.com/login
```

Json example:

```
{
  "email": "testuser@roda.com.br",
  "password": "123456"
}  
```

#### Logged user profile 

```http
  GET http://roda-api.herokuapp.com/login/profile
```

#### Edit logged user

```http
  PUT http://roda-api.herokuapp.com/user/edit
```

Json example:

```
{
  "name":"Test User Edit",
  "email": "testuseredit@roda.com.br",
  "bio": "Hi Im User Edited!",
  "imgurl": "https://urlimgedited.com.br"
}
```

#### List all users 

```http
  GET http://roda-api.herokuapp.com/user/alluser
```

#### Search users by name

```http
  GET http://roda-api.herokuapp.com/user/:name

| Parameter | Type       | Descrição                                   |
| `name`    | `string`   | Put user name to search |
```

#### Create Book

```http
  POST http://roda-api.herokuapp.com/book/create
```

Json example:

```
{  
	"isbnstring":"8532511015"
}

```

#### List all books

```http
  GET http://roda-api.herokuapp.com/book/allbooks
```

#### Search books by name

```http
  GET http://roda-api.herokuapp.com/user/:name

| Parameter | Type       | Descrição                                   |
| `name`    | `string`   | Put book name to search |
```

#### List users books

```http
  GET http://roda-api.herokuapp.com/book/mybooks
```

#### Create Book Review

```http
  POST http://roda-api.herokuapp.com/review/create/:idbook
```

Json example:

```
{
    "content": "This book is very...",
    "rating": "4",
    "tags": "Fantasy"
}  
```

#### Create Group

```http
  POST http://roda-api.herokuapp.com/group/create
```

Json example:

```
{
    "name": "Test Group",
    "about": "4",
    "discussion": "Fantasy",
    "books": "Name Book Test"
}  
```

#### List all groups

```http
    GET http://roda-api.herokuapp.com/group/allgroups
```

#### List all groups by logged user

```http
    GET http://roda-api.herokuapp.com/group/mygroups
```

Made by <br>

🔹 Cristiano Daniel Nascimento 👋 [Get in touch](https://github.com/Cristiano-Melo)<br>
🔹 Daniel Antunes Pereira Junior 👋 [Get in touch](https://github.com/DanielAntunes-dev)<br>
🔹 Giovana Paula Donzella 👋 [Get in touch](https://github.com/ale11011971)<br>
🔹 Leonardo Maia Garcia 👋 [Get in touch](https://github.com/LeoMPG)<br>
🔹 Reygis Azevedo 👋 [Get in touch](https://github.com/Reygis)<br>
🔹 Victor Nery 👋 [Get in touch](https://github.com/NeryVictor)<br>

🔸 Andrei Alcantara 👋 [Get in touch](https://github.com/dreialcantara)<br>

🔹 Laíza Mariano 👋 [Get in touch](https://www.linkedin.com/in/la%C3%ADzamariano/)<br>
🔹 Mariane Souza 👋 [Get in touch](https://www.linkedin.com/in/mariane-souza-42576b65/)<br>
🔹 Weslley Pinheiro 👋 [Get in touch](https://www.linkedin.com/in/weslley-pinheiro-8751251a6/)
