# Documentação da API — Painel de Tarefas

API RESTful desenvolvida com Spring Boot para gerenciamento de tarefas pessoais.

**Base URL:** `http://localhost:8080`

**Versão atual:** `v1`

**Documentação interativa (Swagger UI):** `http://localhost:8080/swagger-ui.html`

**Especificação OpenAPI (JSON):** `http://localhost:8080/api-docs`

---

## Estrutura do Projeto

```
src/main/java/com/marchini/paineldetarefas/
├── config/
│   └── OpenApiConfig.java         # Configuração do Swagger/OpenAPI
├── controller/
│   └── TarefasController.java     # Endpoints REST
├── exception/
│   ├── ErroResponse.java          # DTO de resposta de erro
│   ├── GlobalExceptionHandler.java # Tratamento centralizado de exceções
│   └── TarefaNaoEncontradaException.java # Exceção customizada
├── model/
│   └── TarefaEntity.java          # Entidade de domínio
├── service/
│   └── TarefasService.java        # Regras de negócio
└── PaineldetarefasApplication.java
```

---

## Versionamento

A API utiliza **versionamento por URL**. Todos os endpoints estão sob o prefixo `/api/v1/`, o que garante compatibilidade com clientes existentes ao lançar versões futuras (`/api/v2/`, etc.).

---

## Formato de Resposta de Erro

Todos os erros retornam um objeto `ErroResponse` no seguinte formato:

```json
{
  "status": 404,
  "erro": "Recurso não encontrado",
  "mensagem": "Tarefa com ID 99 não encontrada",
  "timestamp": "2026-06-08T10:30:00"
}
```

Em erros de validação (400), o campo `campos` é incluído com os erros por campo:

```json
{
  "status": 400,
  "erro": "Dados inválidos",
  "mensagem": "Um ou mais campos não passaram na validação",
  "timestamp": "2026-06-08T10:30:00",
  "campos": {
    "titulo": "Título é obrigatório",
    "descricao": "Descrição é obrigatória"
  }
}
```

| Código | Cenário |
|--------|---------|
| 400    | Dados inválidos (validação de campos) ou JSON malformado |
| 404    | Recurso não encontrado (`TarefaNaoEncontradaException`) |
| 500    | Erro interno inesperado |

---

## Endpoints

### 1. Listar todas as tarefas

**GET** `/api/v1/tarefas`

Retorna a lista com todas as tarefas cadastradas.

**Exemplo de resposta (200 OK):**
```json
[
  {
    "id": 1,
    "titulo": "Estudar Spring Boot",
    "descricao": "Revisar anotações da aula",
    "status": "PENDENTE"
  }
]
```

---

### 2. Buscar tarefa por ID

**GET** `/api/v1/tarefas/{id}`

| Parâmetro | Tipo    | Descrição            |
|-----------|---------|----------------------|
| `id`      | Integer | ID da tarefa buscada |

**Resposta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações da aula",
  "status": "PENDENTE"
}
```

**Resposta (404 Not Found):**
```json
{
  "status": 404,
  "erro": "Recurso não encontrado",
  "mensagem": "Tarefa com ID 1 não encontrada",
  "timestamp": "2026-06-08T10:30:00"
}
```

| Código | Descrição                        |
|--------|----------------------------------|
| 200    | Tarefa encontrada e retornada    |
| 404    | Tarefa não encontrada            |

---

### 3. Criar nova tarefa

**POST** `/api/v1/tarefas`

O ID é gerado automaticamente. O `status` inicial é sempre `PENDENTE`.

**Corpo da requisição:**

| Campo       | Tipo   | Obrigatório | Restrições          |
|-------------|--------|-------------|---------------------|
| `titulo`    | String | Sim         | máx. 100 caracteres |
| `descricao` | String | Sim         | —                   |

**Exemplo de corpo:**
```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações da aula"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar anotações da aula",
  "status": "PENDENTE"
}
```

**Resposta (400 Bad Request):**
```json
{
  "status": 400,
  "erro": "Dados inválidos",
  "mensagem": "Um ou mais campos não passaram na validação",
  "timestamp": "2026-06-08T10:30:00",
  "campos": {
    "titulo": "Título é obrigatório"
  }
}
```

| Código | Descrição                             |
|--------|---------------------------------------|
| 201    | Tarefa criada com sucesso             |
| 400    | Campos obrigatórios ausentes ou inválidos |

---

### 4. Atualizar tarefa existente

**PUT** `/api/v1/tarefas/{id}`

O ID é informado na URL. O corpo não deve conter `id`.

| Parâmetro URL | Tipo    | Descrição                     |
|---------------|---------|-------------------------------|
| `id`          | Integer | ID da tarefa a ser atualizada |

**Corpo da requisição:**

| Campo       | Tipo   | Obrigatório | Restrições          |
|-------------|--------|-------------|---------------------|
| `titulo`    | String | Sim         | máx. 100 caracteres |
| `descricao` | String | Sim         | —                   |

**Exemplo de corpo:**
```json
{
  "titulo": "Estudar Spring Boot (atualizado)",
  "descricao": "Revisar capítulo 3 e 4"
}
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "titulo": "Estudar Spring Boot (atualizado)",
  "descricao": "Revisar capítulo 3 e 4",
  "status": "PENDENTE"
}
```

| Código | Descrição                             |
|--------|---------------------------------------|
| 200    | Tarefa atualizada com sucesso         |
| 400    | Campos obrigatórios ausentes ou inválidos |
| 404    | Tarefa não encontrada                 |

---

### 5. Excluir tarefa

**DELETE** `/api/v1/tarefas/{id}`

| Parâmetro | Tipo    | Descrição              |
|-----------|---------|------------------------|
| `id`      | Integer | ID da tarefa a excluir |

**Resposta:** sem corpo (204) ou erro (404).

| Código | Descrição                    |
|--------|------------------------------|
| 204    | Tarefa excluída com sucesso  |
| 404    | Tarefa não encontrada        |

---

## Resumo dos endpoints

| Método | Endpoint               | Ação                         | Retorno |
|--------|------------------------|------------------------------|---------|
| GET    | `/api/v1/tarefas`      | Lista todas as tarefas       | 200     |
| GET    | `/api/v1/tarefas/{id}` | Busca uma tarefa por ID      | 200/404 |
| POST   | `/api/v1/tarefas`      | Cria uma nova tarefa         | 201/400 |
| PUT    | `/api/v1/tarefas/{id}` | Atualiza uma tarefa          | 200/400/404 |
| DELETE | `/api/v1/tarefas/{id}` | Remove uma tarefa            | 204/404 |