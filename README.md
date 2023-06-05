
<h2 align="center">Gerenciamento de usuário</h2>


<h3>Pré-requisitos: Java 11+ </h3>


 <h4>🎲 Como executar o projeto</h4>

```bash
# Clone este repositório
$ git@github.com:rosana-moreira/desafio-department.git

# Acesse a pasta do projeto no terminal/cmd
$ cd desafio-department/backend


# Execute o projeto
$ mvn spring-boot:run

```
<H4> 🎲 Coleção do postman/insomnia para realização dos testes </h4>

```bash
# Para buscar todos os cargos em ordem alfabética:
 Get : http://localhost:8080/positions

# Para criar um novo cargo
 Post: http://localhost:8080/positions

    {
			"name": "Arquiteto"
		} 

# Para atualizar um cargo
 Put: http://localhost:8080/positions/id
    {
			"name": "Scrum Master"
		} 
# Para buscar todos os perfis em ordem alfabética:
 Get : http://localhost:8080/profiles

# Para criar um perfil
 Post: http://localhost:8080/profiles
  {
		"name": "Junior"
	} 

# Para atualizar um perfil
 Put: http://localhost:8080/profiles/id
  {
		"name": "Pleno"
	} 
# Para deletar um perfil (O perfil só irá ser deletado quando não estiver associado a um usuário)
 Delete : http://localhost:8080/profiles/id

# Para buscar todos os usuarios em ordem alfabética:
 Get : http://localhost:8080/users

# Para criar um novo usuário
 Post: http://localhost:8080/users

    {
      "name": "Mariana",
      "cpf": "14412043663",
      "birthDate": "2023-06-04T00:00:00Z",
      "gender": "F",
      "companyPositionId": 1,
      "profile": [
        {
          "id": 1
        }
      ]
    }
 

# Para atualizar um usuário
 Put: http://localhost:8080/positions/id
    {
    "name": "Fernanda",
    "cpf": "14412043673",
    "birthDate": "2023-06-04T00:00:00Z",
    "gender": "F",
    "companyPositionId": 1,
    "profile": [
      {
        "id": 1
      }
    ]
  }


```
<H4> 🎲 Acessando o  banco h2 </h4>

```bash
# Para acessar o banco de dados click no link 
 http://localhost:8080/h2-console

# JDBC URL:
 jdbc:h2:mem:testdb

# User Name:
 sa

# Password: vazio
 

```