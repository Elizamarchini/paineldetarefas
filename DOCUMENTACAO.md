# Documentação da API — Painel de Tarefas

API RESTful desenvolvida com Spring Boot para gerenciamento de tarefas pessoais.

**Base URL:** `http://localhost:8080`

---

## Endpoints

### 1. Listar todas as tarefas

**GET** `/tarefas`

Retorna a lista com todas as tarefas cadastradas.

**Parâmetros:** nenhum

**Exemplo de resposta (200 OK):**
```json
[
  {
    "id": 1,
    "titulo": "Estudar Spring Boot",
    "descricao": "Revisar anotações da aula"
  },
  {
    "id": 2,
    "titulo": "Fazer exercícios",
    "descricao": "30 minutos de caminhada"
  }
]
```

| Código | Descrição |
|--------|-----------|
| 200    | Lista retornada com sucesso (pode estar vazia) |

---

### 2. Buscar tarefa por ID

**GET** `/tarefas/{id}`

Retorna uma única tarefa pelo seu identificador.

**Parâmetros de URL:**

| Parâmetro | Tipo    | Descrição             |
|-----------|---------|-----------------------|
| `id`      | Integer | ID da tarefa buscada  |

**Exemplo de resposta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações da aula"
}
```

| Código | Descrição                        |
|--------|----------------------------------|
| 200    | Tarefa encontrada e retornada    |
| 404    | Tarefa não encontrada para o ID informado |

---

### 3. Criar nova tarefa

**POST** `/tarefas`

Adiciona uma nova tarefa. O ID é gerado automaticamente.

**Corpo da requisição (JSON):**

| Campo       | Tipo   | Obrigatório | Descrição              |
|-------------|--------|-------------|------------------------|
| `titulo`    | String | Sim         | Título da tarefa       |
| `descricao` | String | Sim         | Descrição da tarefa    |

**Exemplo de corpo:**
```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações da aula"
}
```

**Exemplo de resposta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações da aula"
}
```

| Código | Descrição                  |
|--------|----------------------------|
| 200    | Tarefa criada com sucesso  |

---

### 4. Atualizar tarefa existente

**PUT** `/tarefas`

Atualiza o título e a descrição de uma tarefa já existente.

**Corpo da requisição (JSON):**

| Campo       | Tipo    | Obrigatório | Descrição                        |
|-------------|---------|-------------|----------------------------------|
| `id`        | Integer | Sim         | ID da tarefa a ser atualizada    |
| `titulo`    | String  | Sim         | Novo título da tarefa            |
| `descricao` | String  | Sim         | Nova descrição da tarefa         |

**Exemplo de corpo:**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot (atualizado)",
  "descricao": "Revisar capítulo 3 e 4"
}
```

**Exemplo de resposta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot (atualizado)",
  "descricao": "Revisar capítulo 3 e 4"
}
```

| Código | Descrição                           |
|--------|-------------------------------------|
| 200    | Tarefa atualizada com sucesso       |
| 404    | Tarefa não encontrada para o ID informado |

---

### 5. Excluir tarefa

**DELETE** `/tarefas/{id}`

Remove uma tarefa pelo seu identificador.

**Parâmetros de URL:**

| Parâmetro | Tipo    | Descrição              |
|-----------|---------|------------------------|
| `id`      | Integer | ID da tarefa a excluir |

**Resposta:** sem corpo.

| Código | Descrição                              |
|--------|----------------------------------------|
| 204    | Tarefa excluída com sucesso            |
| 404    | Tarefa não encontrada para o ID informado |

---

## Resumo dos endpoints

| Método | Endpoint        | Ação                         |
|--------|-----------------|------------------------------|
| GET    | `/tarefas`      | Lista todas as tarefas       |
| GET    | `/tarefas/{id}` | Busca uma tarefa por ID      |
| POST   | `/tarefas`      | Cria uma nova tarefa         |
| PUT    | `/tarefas`      | Atualiza uma tarefa          |
| DELETE | `/tarefas/{id}` | Remove uma tarefa            |