# Evaluación Ingeniería de Software - API Mueblería
## API REST para "Mueblería los Muebles Hermanos S.A."

Este proyecto es un backend desarrollado en Spring Boot que implementa la lógica de negocio para gestionar el catálogo, cotizaciones y ventas de una mueblería, incluyendo el manejo de stock y variantes de productos.

---

### Tecnologías Utilizadas

* **Java 17+**
* **Spring Boot** (v3.x.x)
* **Spring Web:** Para la creación de la API REST.
* **Spring Data JPA:** Para el mapeo de objetos a la base de datos (ORM).
* **MySQL:** Como base de datos relacional.
* **Maven:** Como gestor de dependencias y build.
* **Lombok:** Para reducir el código boilerplate (getters, setters, etc.).
* **JUnit 5:** Para la realización de tests unitarios sobre la lógica de negocio.

---

### 1. Prerrequisitos

Antes de comenzar, necesitarás tener instalado:

* **JDK** (Java Development Kit) 17 o superior.
* **Maven** (usualmente integrado en los IDEs).
* **MySQL Server** (Se recomienda **XAMPP** para una fácil instalación y gestión con phpMyAdmin).
* **Postman** (o cualquier cliente API) para probar los endpoints.

---

### 2. Configuración ⚙️

Sigue estos pasos para configurar el entorno local.

#### A. Base de Datos

1.  Inicia tu servidor MySQL (usando XAMPP o el servicio de MySQL).
2.  Abre `http://localhost/phpmyadmin` (o tu gestor de BD preferido).
3.  Crea una nueva base de datos con el nombre: **`muebleria_db`**
    * *No necesitas crear las tablas*, Spring Boot lo hará por ti.

#### B. Archivo de Propiedades

1.  Abre el proyecto en tu IDE.
2.  Navega a `src/main/resources/application.properties`.
3.  Asegúrate de que el contenido coincida con la siguiente configuración (para XAMPP, la contraseña `password` está vacía):

```properties
# Configuración de la Base de Datos MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/muebleria_db
spring.datasource.username=root
spring.datasource.password=

# Configuración de JPA (Hibernate)
# 'update' -> Spring creará las tablas automáticamente basado en las clases @Entity
spring.jpa.hibernate.ddl-auto=update
# 'true' -> Muestra en la consola el SQL que Spring está ejecutando
spring.jpa.show-sql=true

# (Opcional) Si el puerto 8080 está en uso, cambia el puerto
# server.port=8081