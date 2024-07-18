## 📋 Proposta da Aplicação

Nossa aplicação tem como propósito ser um sistema de gerenciamento de pacientes e médicos, onde os usuários poderão cadastrar, listar, atualizar e remover pacientes e médicos, bem como buscar informações detalhadas sobre eles.

## 🚀 Como Executar a Aplicação

### ⚙️ Pré-requisitos

- Java 17+
- Maven
- MySQL


1\. **Clone o Repositório:**
```sh\
git clone https://github.com/seuusuario/MedSystem
```

2\. **Configure o Banco de Dados:**

Acesse o MySQL:
```cmd
mysql -u root -p 
```
## 🗂️ Script do Banco de Dados

O script SQL para criar o banco de dados e as tabelas necessárias está incluído na seção de configuração do banco de dados:

**CRIAR SCHEMA MED**
```mysql
CREATE DATABASE Med;
```
**DEFINIR SCHAMA QUE SERÁ USADO**
```MYSQL
USE Med;
```
**CRIAR TABELA PACIENTES**
```MYSQL
CREATE TABLE pacientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    cpf VARCHAR(14) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    ativo BIT NOT NULL,
    logradouro VARCHAR(255),
    bairro VARCHAR(255),
    cep VARCHAR(10),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    rua VARCHAR(255),
    uf VARCHAR(2),
    complemento VARCHAR(255),
    numero VARCHAR(10)
);
```
**CRIAR TABELA MEDICOS**
```mysql
CREATE TABLE medicos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    crm VARCHAR(20) NOT NULL,
    telefone VARCHAR(20) NOT NULL,
    especialidade VARCHAR(255) NOT NULL,
    ativo BIT NOT NULL,
    logradouro VARCHAR(255),
    bairro VARCHAR(255),
    cep VARCHAR(10),
    cidade VARCHAR(255),
    estado VARCHAR(255),
    rua VARCHAR(255),
    uf VARCHAR(2),
    complemento VARCHAR(255),
    numero VARCHAR(10)
);
```

**Execute a Aplicação:**
```cmd
java -Dexec.mainClass="org.example.ApplicationSystemStream"
```

## 📋 Funcionalidades da aplicação

**🤒 PACIENTES**
- Cadastrar Paciente:
  - **nome:** String
  - **email:** String
  - **telefone:** String
  - **cpf:** String
  - **endereco:** 
    - **logradouro:** String
    - **bairro:** String
    - **cep:** String
    - **cidade:** String
    - **estado:** String
    - **rua:** String
    - **uf:** String
    - **complemento:** String
    - **numero:** String
- Listar Pacientes: Os usuários podem visualizar uma lista de pacientes cadastrados.
- Atualizar Paciente: Os usuários podem atualizar as informações de um paciente existente.
- Remover Paciente: Os usuários podem inativar um paciente.

**🧑‍⚕️ MÉDICOS**
- Cadastrar Médicos:
- nome: String
- email: String
- crm: String
- telefone: String
- especialidade: String
- endereco:
  - logradouro: String
  - bairro: String
  - cep: String
  - cidade: String
  - estado: String
  - rua: String
  - uf: String
  - complemento: String
  - numero: String
- Listar Médicos: Os usuários podem visualizar uma lista de médicos cadastrados.
- Atualizar Médico: Os usuários podem atualizar as informações de um médico existente.
- Remover Médico: Os usuários podem inativar um médico.

## 🔧 Tecnologias Utilizadas
- Java
- MySQL
- Maven
- Hibernate
- JUnit
- Git
- GitHub
- IntelliJ Community IDEA

## 🤝 Desenvolvedor

Creditos ao desenvolvedor que contribuiu a execução desse projeto:

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
  </tr>
</table>


## 📝 Licença

Esse projeto está sob licença. Veja o arquivo [LICENÇA](https://github.com/R2DWess/GerenciamentoDeMedicos/blob/main/LICENSE) para mais detalhes.

