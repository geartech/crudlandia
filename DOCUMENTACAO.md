# 📚 Documentação da API Crudlandia

Este guia mostra como acessar a documentação da API através do Swagger UI e do Javadoc.

---

## 🌐 Swagger UI (Documentação Interativa da API)

O Swagger UI fornece uma interface interativa para testar todos os endpoints da API REST.

### Como Acessar:

1. **Inicie a aplicação:**

   ```bash
   ./gradlew bootRun
   ```

   Ou execute a classe `CrudlandiaApplication` diretamente pela IDE.

2. **Acesse o Swagger UI no navegador:**

   ```
   http://localhost:5001/crudlandia/swagger-ui.html
   ```

3. **Alternativa - OpenAPI JSON:**
   ```
   http://localhost:5001/crudlandia/api-docs
   ```

### Recursos do Swagger UI:

- ✅ Visualize todos os endpoints disponíveis
- ✅ Veja os modelos de request e response
- ✅ Execute requisições diretamente do navegador
- ✅ Teste com diferentes payloads
- ✅ Veja códigos de resposta HTTP
- ✅ Documentação completa de cada endpoint

### Exemplo de Uso no Swagger:

1. Expanda o grupo **"Exemplos"**
2. Clique em um endpoint (ex: `POST /api/exemplo/criar`)
3. Clique no botão **"Try it out"**
4. Preencha o JSON de exemplo
5. Clique em **"Execute"**
6. Veja a resposta da API

---

## 📖 Javadoc (Documentação do Código Java)

O Javadoc contém a documentação detalhada de todas as classes, métodos e interfaces do projeto.

### Como Gerar o Javadoc:

```bash
./gradlew javadoc
```

### Como Acessar:

1. Após gerar o Javadoc, abra o arquivo index.html no navegador:

   ```
   build/docs/javadoc/index.html
   ```

2. **No Windows (via terminal):**

   ```bash
   start build/docs/javadoc/index.html
   ```

3. **No Linux/Mac:**
   ```bash
   open build/docs/javadoc/index.html
   # ou
   xdg-open build/docs/javadoc/index.html
   ```

### Estrutura do Javadoc:

O Javadoc está organizado por pacotes:

- **`com.crudlandia.controllers`** - Controllers REST
  - `ExemploController` - CRUD de Exemplos
- **`com.crudlandia.services`** - Lógica de negócio
  - `ExemploService` - Interface do serviço
  - `ExemploServiceImpl` - Implementação do serviço
- **`com.crudlandia.models.entities`** - Entidades JPA
  - `BaseEntity` - Entidade base com auditoria
  - `ExemploEntity` - Entidade de Exemplo
  - `ReferenciaEntity` - Entidade de Referência
- **`com.crudlandia.models.repository`** - Repositórios JPA
  - `ExemploRepository` - Repositório de Exemplos
  - `ReferenciaRepository` - Repositório de Referências
- **`com.crudlandia.mappers`** - Mappers MyBatis
  - `ExemploMapper` - Consultas customizadas

---

## 🚀 Links Rápidos

| Recurso          | URL                                              | Descrição                               |
| ---------------- | ------------------------------------------------ | --------------------------------------- |
| **Swagger UI**   | http://localhost:5001/crudlandia/swagger-ui.html | Interface interativa da API             |
| **OpenAPI JSON** | http://localhost:5001/crudlandia/api-docs        | Especificação OpenAPI em JSON           |
| **Javadoc**      | `build/docs/javadoc/index.html`                  | Documentação do código (gerar primeiro) |
| **Aplicação**    | http://localhost:5001/crudlandia                 | Servidor principal                      |

---

## 📋 Endpoints Principais

### Exemplos

| Método | Endpoint             | Descrição                  |
| ------ | -------------------- | -------------------------- |
| POST   | `/api/exemplo/criar` | Criar novo exemplo         |
| GET    | `/api/exemplo/{id}`  | Buscar exemplo por ID      |
| GET    | `/api/exemplo`       | Listar exemplos (paginado) |
| PUT    | `/api/exemplo/{id}`  | Atualizar exemplo          |
| DELETE | `/api/exemplo/{id}`  | Deletar exemplo            |

**Nota:** Todos os endpoints devem ser acessados com o prefixo `/crudlandia`, por exemplo: `http://localhost:5001/crudlandia/api/exemplo/criar`

---

## 💡 Dicas

### Swagger UI:

- Use o botão **"Authorize"** se a API tiver autenticação
- Os modelos (Schemas) mostram a estrutura esperada dos dados
- Códigos de resposta verdes (2xx) indicam sucesso
- Códigos vermelhos (4xx, 5xx) indicam erros

### Javadoc:

- Use a barra de busca para encontrar classes específicas
- Clique em "All Classes" para ver todas as classes
- A navegação por pacotes facilita encontrar componentes relacionados
- Links entre classes facilitam a navegação

---

## 🔧 Configurações

As configurações do Swagger estão em:

- **Arquivo:** `src/main/resources/application.properties`
- **Classe:** `src/main/java/com/crudlandia/config/SwaggerConfig.java`

---

## 📞 Suporte

Para mais informações sobre a API, consulte:

- Javadoc completo das classes
- Swagger UI para testar endpoints
- README.md do projeto
