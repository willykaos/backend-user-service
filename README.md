
# 📦 backend-user-service

Servicio backend en **Spring Boot** para la gestión básica de usuarios. Incluye endpoints REST, pruebas automáticas y métricas compatibles con Prometheus.

---

## 🛠️ Requisitos

- Java 11
- Git
- No es necesario tener Maven instalado, se usa el wrapper (`mvnw`) incluido en el proyecto

---

## ⬇️ Descarga y Ejecución

1. **Clona el repositorio:**  
```bash
git clone https://github.com/willykaos/backend-user-service.git
cd backend-user-service
```

2. **Ejecuta la aplicación:**  
```bash
./mvnw spring-boot:run
```
En Windows:  
```bash
mvnw spring-boot:run
```

Por defecto, la aplicación se expone en:  
```
http://localhost:8080
```

---

## ✅ Ejecución de Tests

Para ejecutar los tests unitarios:  
```bash
./mvnw test
```
En Windows:  
```bash
mvnw test
```

---

## 📡 Endpoints disponibles

### 🔹 Crear un usuario
```bash
curl -i -X POST http://localhost:8080/users \
  -H "Content-Type: application/json" \
  -d '{"username":"juan","email":"juan@ditech.es","active":true}'
```

### 🔹 Listar todos los usuarios
```bash
curl -i http://localhost:8080/users
```

### 🔹 Consultar un usuario por ID
```bash
curl -i http://localhost:8080/users/1
```

### 🔹 Eliminar un usuario por ID
```bash
curl -i -X DELETE http://localhost:8080/users/1
```

---

## 📊 Métricas Prometheus

La aplicación expone métricas en formato compatible con **Prometheus** en:  
```
http://localhost:8080/actuator/prometheus
```

---

## 🖥️ Tecnologías

- Spring Boot  2.7.18
- Spring Web  
- Spring Actuator  
- Prometheus (para monitoreo)  
- JUnit / Mockito (para pruebas)  

---

## 📝 Notas

- Si deseas modificar el puerto o configuraciones adicionales, puedes hacerlo en el archivo `application.properties`.  
- Se recomienda probar los endpoints con herramientas como **curl**, **Postman** o **Insomnia**.  
