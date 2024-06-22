## 📋 Proposta da Aplicação

Nossa aplicação tem como propósito ser um sistema de Streaming de Filmes, onde o usuário poderá se cadastrar, logar, listar filmes, buscar filmes, favoritar filmes, adicionar filmes à sua lista e assistir a um filme.
## 🚀 Como Executar a Aplicação

### Pré-requisitos

- Java 17+
- Maven
- MySQL

## PROJETO DE ORIENTAÇÃO A OBJETOS - Universidade Católica de Brasília:

1\. **Clone o Repositório:**
```sh\
https://github.com/R2DWess/SistemaDeFilmes
```

2\. **Configure o Banco de Dados:**

Acesse o MySQL:
```cmd
mysql -u root -p 
```
## 🗂️ Script do Banco de Dados

O script SQL para criar o banco de dados e as tabelas necessárias está incluído na seção de configuração do banco de dados:

```mysql
CREATE DATABASE systemmovies;
USE systemmovies;
```

Execute a Aplicação:
```cmd
java -Dexec.mainClass="org.example.ApplicationSystemStream"
```

## 📋 Funcionalidades da aplicação

- Tela de Cadastro
  - **fullname:** InsertfullnameUser
  - **socialname:** InsertsocialnameUser
  - **Email:** InsertEmailUser
  - **Password:** InsertPasswordUser
  - **RG:** InsertRGUser
  - **CPF:** InsertCPFUser
  - **birthdate:** InsertbirthdateUser
  - **Telefone:** InsertTelefoneUser
  - **isAdmin:** InsertisAdminUser

- Tela de Login
  **Email:** InsertEmailUser
  **Password:** InsertPasswordUser

- Tela de Filmes
  - 📜 **Listar Filmes:** Os usuários podem visualizar uma lista de filmes disponíveis.
  - 🔎 **Buscar Filmes:** Os usuários podem buscar filmes pelo título.
  - ⭐ **Favoritar Filmes:** Os usuários podem adicionar filmes à sua lista de favoritos.
  - ✅ **Adicionar Filmes à Lista:** Os usuários podem adicionar filmes à sua lista de interesse.
  - 🎥 **Assistir Filmes:** Os usuários podem assistir aos filmes disponíveis na plataforma.


## 🔧 Tecnologias Utilizadas
- Java
- MySQL
- Maven
- Hibernate
- JUnit
- Git
- GitHub
- IntelliJ IDEA

## 📞 Contato

Para mais informações, entre em contato pelo email
