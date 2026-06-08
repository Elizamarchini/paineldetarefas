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
    "descricao": "Revisar anotações da aula",
    "status": "PENDENTE"
  },
  {
    "id": 2,
    "titulo": "Fazer exercícios",
    "descricao": "30 minutos de caminhada",
    "status": "PENDENTE"
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
  "descricao": "Revisar anotações da aula",
  "status": "PENDENTE"
}
```

**Exemplo de resposta (404 Not Found):**
```json
{
  "erro": "Tarefa não encontrada"
}
```

| Código | Descrição                                 |
|--------|-------------------------------------------|
| 200    | Tarefa encontrada e retornada             |
| 404    | Tarefa não encontrada para o ID informado |

---

### 3. Criar nova tarefa

**POST** `/tarefas`

Adiciona uma nova tarefa. O ID é gerado automaticamente e o status inicial é `PENDENTE`.

**Corpo da requisição (JSON):**

| Campo       | Tipo   | Obrigatório | Restrições       | Descrição           |
|-------------|--------|-------------|------------------|---------------------|
| `titulo`    | String | Sim         | máx. 100 caracteres | Título da tarefa |
| `descricao` | String | Sim         | —                | Descrição da tarefa |

**Exemplo de corpo:**
```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações da aula"
}
```

**Exemplo de resposta (201 Created):**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações da aula",
  "status": "PENDENTE"
}
```

**Exemplo de resposta (400 Bad Request) — validação:**
```json
{
  "titulo": "Título é obrigatório",
  "descricao": "Descrição é obrigatória"
}
```

| Código | Descrição                                              |
|--------|--------------------------------------------------------|
| 201    | Tarefa criada com sucesso                              |
| 400    | Dados inválidos — corpo contém mapa de erros por campo |

---

### 4. Atualizar tarefa existente

**PUT** `/tarefas/{id}`

Atualiza o título e a descrição de uma tarefa já existente. O ID é informado na URL.

**Parâmetros de URL:**

| Parâmetro | Tipo    | Descrição                     |
|-----------|---------|-------------------------------|
| `id`      | Integer | ID da tarefa a ser atualizada |

**Corpo da requisição (JSON):**

| Campo       | Tipo   | Obrigatório | Restrições          | Descrição              |
|-------------|--------|-------------|---------------------|------------------------|
| `titulo`    | String | Sim         | máx. 100 caracteres | Novo título da tarefa  |
| `descricao` | String | Sim         | —                   | Nova descrição da tarefa |

**Exemplo de corpo:**
```json
{
  "titulo": "Estudar Spring Boot (atualizado)",
  "descricao": "Revisar capítulo 3 e 4"
}
```

**Exemplo de resposta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot (atualizado)",
  "descricao": "Revisar capítulo 3 e 4",
  "status": "PENDENTE"
}
```

**Exemplo de resposta (400 Bad Request) — validação:**
```json
{
  "titulo": "Título é obrigatório"
}
```

**Exemplo de resposta (404 Not Found):**
```json
{
  "erro": "Tarefa não encontrada"
}
```

| Código | Descrição                                              |
|--------|--------------------------------------------------------|
| 200    | Tarefa atualizada com sucesso                          |
| 400    | Dados inválidos — corpo contém mapa de erros por campo |
| 404    | Tarefa não encontrada para o ID informado              |

---

### 5. Excluir tarefa

**DELETE** `/tarefas/{id}`

Remove uma tarefa pelo seu identificador.

**Parâmetros de URL:**

| Parâmetro | Tipo    | Descrição              |
|-----------|---------|------------------------|
| `id`      | Integer | ID da tarefa a excluir |

**Resposta:** sem corpo.

**Exemplo de resposta (404 Not Found):**
```json
{
  "erro": "Tarefa não encontrada"
}
```

| Código | Descrição                                 |
|--------|-------------------------------------------|
| 204    | Tarefa excluída com sucesso               |
| 404    | Tarefa não encontrada para o ID informado |

---

## Resumo dos endpoints

| Método | Endpoint        | Ação                         |
|--------|-----------------|------------------------------|
| GET    | `/tarefas`      | Lista todas as tarefas       |
| GET    | `/tarefas/{id}` | Busca uma tarefa por ID      |
| POST   | `/tarefas`      | Cria uma nova tarefa         |
| PUT    | `/tarefas/{id}` | Atualiza uma tarefa          |
| DELETE | `/tarefas/{id}` | Remove uma tarefa            |