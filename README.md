## Campanhas de sócio torcedor (API)
Este sistema expõe serviços de cadastros de campanhas e sócio torcedores. Uma campanha possui um período de vigência e é associada a um time, as quais sócio torcedores podem se associar.

#### Models
##### Campanha (Campaign)
Uma campanha associada a um time; sócios torcedores podem associar a uma campanha.

| Campo         | Tipo        |
| ------------- | ------------- |
| Nome          | String |
| Time          | Associação 1:N com Time |
| Início        | LocalDate |
| Fim           | LocalDate |

##### Time (Team)
Um time de futebol;

| Campo         | Tipo        |
| ------------- | ------------- |
| Nome          | String |

###### Sócio Torcedor (User)
Usuário do sistema

| Campo         | Tipo        |
| ------------- | ------------- |
| Nome          | String |
| E-mail          | String |
| Data de nascimento | String |
| Time do coração   | Associação 1:N com Time |
| Campanhas associadas   | Associação N:N com Campanha |


#### Endpoints
###### /campaign
REST para campaign

| Metodo        | Endpoing | Descrição                             |
| ------------- | -------- | --------------------------------------|
| GET | /campaign | Lista todas as campanhas não vencidas |
| GET | /campaign/:id | Mostra a campanha pelo id fornecido |
| POST | /campaign | Cria uma campanha |
| PUT | /campaign/:id | Atualiza uma campanha |
| DELETE | /campaign/:id | Deleta uma campanha |

Existem algumas regras para a criação de uma campanha, descritas a seguir:
> No cadastramento de uma nova campanha, deve-se verificar se já existe uma campanha
ativa para aquele período (vigência), caso exista uma campanha ou N campanhas associadas
naquele período, o sistema deverá somar um dia no término da vigência de cada campanha
já existente. Caso a data final da vigência seja igual a outra campanha, deverá ser acrescido
um dia a mais de forma que as campanhas não tenham a mesma data de término de
vigência. Por fim, efetuar o cadastramento da nova campanha:
o Exemplo:
1. Campanha 1 : inicio dia 01/10/2017 a 03/10/2017;
2. Campanha 2: inicio dia 01/10/2017 a 02/10/2017;
3. Cadastrando Campanha 3: inicio 01/10/2017 a 03/10/2017;
 Sistema:
    3.1 Campanha 2 : 01/10/2017 a 03/10/2017 (porém a data bate com a campanha 1 e a 3, somando mais 1 dia)
    -> 3.1.2 Campanha 2 : 01/10/2017 a 04/10/2017
    3.2 Campanha 1: 01/10/2017 a 04/10/2017 (bate com a data da campanha 2, somando mais 1 dia)
    -> 3.2.1 Campanha 1: 01/10/2017 a 05/10/2017
    3.3 Incluindo campanha 3 : 01/10/2017 a 03/10/2017

###### /team
REST para Time

| Metodo        | Endpoing | Descrição                             |
| ------------- | -------- | --------------------------------------|
| GET | /team | Lista todas os times |
| GET | /team/:id | Mostra o time pelo id fornecido |
| POST | /team | Cria uma time |
| PUT | /team/:id | Atualiza um time |
| DELETE | /team/:id | Deleta um time |

###### /socio-torcedor
REST para User

| Metodo        | Endpoing | Descrição                             |
| ------------- | -------- | --------------------------------------|
| POST | /socio-torcedor | Cria o usuário e associa campanhas do time do coração à ele |

Existem algumas regras para o cadastro e associação de sócio torcedores à campanhas, descritas à seguir:
> Se for usuário novo: Após o cadastramento do usuário, o sistema deverá
solicitar as campanhas ativas para aquele time do coração e efetuar a
associação;

>  Se for um usuário já cadastrado: Deverá ser feita a associação das
campanhas novas, ou seja, as que o usuário daquele time do coração não
tem relacionamento até o momento.

#### Arquitetura
É um projeto feito em Spring e foi utilizado os padrões MVC e o repository, adaptado para Web Services.
Para a API, foi utilizado o padrão RESTfull, onde status code e métodos HTTP tem representações significativas para os consumidores e o próprio web service.
Foram criados classes conversoras para as class LocalDate (convertidas para java.sql,Date) e LocalDateTime (convertidas para java.sql.Timestamp) para salvarmos as datas no banco de dados com um tipo significativo em vez de tyniblob, facilitando possíveis manutenções e visualizações na base.
Todas as trocas de mensagens são feitas por JSON.

#### Mock e testes
Foi criada um endpoint com a finalidade de facilitar os testes à este projeto:

| Metodo        | Endpoing | Descrição                             |
| ------------- | -------- | --------------------------------------|
| GET | /mock | Insere 7 times e 2 campanhas ao banco de de dados |

#### Dependências
 - Spring Boot
 - Spring Data
 - Hibernate
 - Jackson Datatype
 - Mysql


### Executando o projeto
É um maven project com apenas o JUnity como dependência, você pode importar na sua IDE favorita com algo como "Imoprt Maven Project".
Para a base de dados, crie uma nova base com o nome de sua escolha:
```sql
CREATE DATABASE <nome_de_sua_base>;
```
E registre as credenciais em `CampaignApi/src/main/resources/application.properties`. É um arquivo de configuração do Spring. Altere as seguintes linhas:

```properties
spring.jpa.hibernate.ddl-auto=create # Para não perder os dados, mude para "update"
spring.datasource.url=jdbc:mysql://localhost:3306/<nome_de_sua_base>?useSSL=false
spring.datasource.username=<usuario_de_sua_base>
spring.datasource.password=<senha_de_sua_base>
```

### Autor
Renan Pallin \<renanpallin@gmail.com\>
