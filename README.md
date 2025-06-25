# Clicker API

It's a simple game when it's possible to create an account and access clicker endpoint where you receive a random number and available clicks.
Everything you need is test your lucky!

---
## Technologies

- Java
- Spring Boot
- Spring Security + JWT
- Spring Data JPA
- Docker
- PostgreSQL
- Insomnia
- Swagger / OpenAPI (springdoc-openapi)
---
## See documentation

http://localhost:8080/swagger-ui/index.html

> After authenticating, click **Authorize** and enter your JWT token in `Bearer <token>` format to test all protected endpoints.

Also available in:

- [openapi.yaml](./openapi.yaml)
- [Swagger Editor](https://editor.swagger.io) (open the `openapi.yaml`)

---

## Run the project

```bash
# Clone repository

git clone git@github.com:your-user/clicker-backend.git
cd clicker-backend

# Configure your application.properties or application.yml with your credentials
# Example: src/main/resources/application.yml

# Run the project
./mvnw spring-boot:run

The API will be started in:

http://localhost:8080
```
---
## Principals Endpoints


## User

| Method | Endpoint        | Description              | Params                                                            | Authenticated |
|--------|-----------------|--------------------------|-------------------------------------------------------------------|---------------|
| GET    | `/api/register` | Create a new user        | `{ "username": "test", "email": "test@email", "password": "1234"` | ❌             |
| POST   | `/api/login`    | Log in with user created | `{ "username": "", "password": "" }`                              | ✅             |

---

## Clicker

| Method | Endpoint       | Description                                                          | Params                   | Authenticated |
|--------|----------------|----------------------------------------------------------------------|--------------------------|---------------|
| POST   | `/api/clicker` | Check if your random number is equal to new generated when you click | `{ "username": "test" }` | ✅             |

---
## Author

- [@brenolucks](https://www.github.com/brenolucks)

---

## Feedback

If you have any comments, please send them to this email at brenolucks@gmail.com