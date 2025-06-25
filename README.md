# Documentação da API - Swagger / OpenAPI

Este diretório contém a especificação da API REST do projeto, gerada automaticamente com o `springdoc-openapi` no padrão **OpenAPI 3.0**.

---

## Arquivo de Documentação

- **Arquivo**: `openapi.yaml`
- **Formato**: YAML (padrão OpenAPI 3.0)

Você pode abrir esse arquivo em ferramentas compatíveis com Swagger/OpenAPI, como Swagger Editor.

---

## 🧪 Como visualizar a documentação

### 1. Swagger UI (localmente)

Se o projeto estiver rodando localmente, acesse: http://localhost:8080/swagger-ui/index.html

> A porta pode variar de acordo com a configuração do seu projeto.

---

### 2. Swagger Editor (online)

Visualize a documentação diretamente no Swagger Editor:

🔗 [https://editor.swagger.io](https://editor.swagger.io)

**Passos:**
1. Acesse o link acima.
2. Clique em **File > Import File**.
3. Selecione o arquivo `openapi.yaml` localizado na pasta `docs/`.

---

## Como gerar novamente o arquivo `openapi.yaml`

1. Suba a aplicação localmente (`localhost:8080`).
2. No navegador, acesse: http://localhost:8080/v3/api-docs.yaml
3. Clique com o botão direito > **Salvar como** → `openapi.yaml`.
4. Substitua o arquivo na pasta `/docs`.

> Alternativamente, você pode acessar `http://localhost:8080/v3/api-docs` para baixar em formato JSON (`openapi.json`).

---

## Autenticação com JWT (Bearer Token)

Alguns endpoints exigem autenticação.

### Como usar o botão "Authorize" no Swagger:

1. Clique no botão **"Authorize"** no topo direito da interface Swagger UI.
2. Insira o token JWT no seguinte formato: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6...
3. Clique em **Authorize**, depois **Close**.
4. Agora os endpoints protegidos poderão ser testados com o token.

---

## Tecnologias relacionadas

- Spring Boot
- Spring Web / Spring Security
- `springdoc-openapi-starter-webmvc-ui`
- Swagger UI
- OpenAPI 3.0

---

## Licença

Este projeto segue a licença padrão definida no repositório principal.

---