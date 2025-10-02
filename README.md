# Cat API Collector & BFF

Este projeto é composto por dois serviços principais:

1. **Collector**  
   Responsável por consumir a [The Cat API](https://thecatapi.com/), coletar informações sobre raças e enviar mensagens para o RabbitMQ.

2. **BFF (Backend for Frontend)**  
   Responsável por consumir as mensagens enviadas pelo Collector, persistir no banco MySQL e expor endpoints REST para consulta.

---

## Estrutura do Projeto

- `collector` → Serviço que consome a Cat API e envia mensagens para a fila do RabbitMQ.
- `cat-api-bff` → Serviço que consome as mensagens do RabbitMQ, salva no MySQL e expõe as APIs.
- `docker-compose.yml` (collector) → Sobe o Collector e o RabbitMQ.
- `docker-compose.yml` (bff) → Sobe o BFF e o MySQL.

---

## Primeiro: Rode o Collector

### Pré-requisitos
- Docker e Docker Compose instalados.

### Subindo os containers
No diretório do **collector**, rode:

docker-compose up --build

---

## Segundo: Rode o BFF
- Docker e Docker Compose instalados.

### Subindo os containers
No diretório do cat-api-bff, rode:

docker-compose up --build

---

## APIs Disponíveis no BFF

1. Listar todas as raças:
   - GET /breeds

2. Buscar informações de uma raça:
    - GET /breeds/{id}

3. Buscar raças por temperamento:
   - GET /breeds/temperament/{temperament}

4. Buscar raças por origem:
   - GET /breeds/origin/{origin}

## Observações

- O Collector não possui banco de dados e nem autenticação via API Key.
- O BFF se conecta ao RabbitMQ e ao MySQL.
- RabbitMQ é necessário para comunicação entre Collector e BFF.
- O MySQL é usado apenas pelo BFF para persistência.

## Collection do Postman
- Enviada por email