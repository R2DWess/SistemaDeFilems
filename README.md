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

## 🤝 Colaboradores

Agradecemos às seguintes pessoas que contribuíram para este projeto:

<table>
  <tr>
    <td align="center">
      <a href="https://www.linkedin.com/in/wesley-lima-244405251/" title="Wesley Lima">
        <img src="https://media.licdn.com/dms/image/D4D03AQGnIzTyPW-ctw/profile-displayphoto-shrink_800_800/0/1718908709929?e=1724284800&v=beta&t=Cnilj9FRnljF4pW7s_gzoXJlAbVOBGz96o-1YJ29pOw" width="100px;" alt="Foto do Wesley Lima"/><br>
        <sub>
          <b>Wesley Lima</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://www.linkedin.com/in/pdr-hp2004/" title="Pedro Henrique">
        <img src="https://media.licdn.com/dms/image/D4D03AQEaYTyYetINkQ/profile-displayphoto-shrink_800_800/0/1685739079307?e=1721865600&v=beta&t=eRedKXode-PexxMiM_nsKYHx1PPr5VvVu5ccmDPTA98" width="100px;" alt="Pedro Henrique"/><br>
        <sub>
          <b>Pedro Henrique</b>
        </sub>
      </a>
    </td>
    <td align="center">
      <a href="https://www.linkedin.com/in/pedro-c%C3%A9sarr-2oo2/" title="Pedro Cesar">
        <img src="https://media.licdn.com/dms/image/D4E03AQH7DooITDfsKQ/profile-displayphoto-shrink_400_400/0/1688525101280?e=1721865600&v=beta&t=tksNGtTdjl9WKAeYuDj4gZL0_GgSeEYhgdTpz1omEv4" width="100px;" alt="Foto do Pedro Cesar"/><br>
        <sub>
          <b>Pedro Cesar</b>
        </sub>
      </a>
    </td>
  </tr>
</table>

## 📝 Licença

Esse projeto está sob licença. Veja o arquivo [LICENÇA](https://github.com/R2DWess/SistemaDeFilmes/blob/main/LICENSE) para mais detalhes.

