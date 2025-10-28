# CRUD Mixto Spring Boot

Este proyecto es una aplicación web desarrollada con **Spring Boot** que permite gestionar empleados, usuarios y proyectos. Incluye funcionalidades para registrar, listar, editar y eliminar registros, así como exportar los datos a archivos **Excel (.xlsx)** y **PDF**.

## Características

- CRUD completo para empleados, usuarios y proyectos.
- Exportación de datos a Excel y PDF.
- Seguridad con Spring Security (login y registro).
- Interfaz web con Thymeleaf y Bootstrap.
- Conexión a base de datos MySQL.

## Requisitos

- Java 17+
- Maven
- MySQL

## Instalación

1. Clona el repositorio:
   ```bash
   git clone https://github.com/tu-usuario/tu-repo.git
   cd tu-repo
   ```

2. Configura la base de datos en `src/main/resources/application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
   spring.datasource.username=tu_usuario
   spring.datasource.password=tu_contraseña
   spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
   ```

3. Instala las dependencias y ejecuta la aplicación:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

## Uso

- Accede a la aplicación en [http://localhost:8080](http://localhost:8080)
- Exporta empleados:
  - Excel: [http://localhost:8080/empleados/excel](http://localhost:8080/empleados/excel)
  - PDF: [http://localhost:8080/empleados/pdf](http://localhost:8080/empleados/pdf)
- Exporta proyectos:
  - Excel: [http://localhost:8080/proyectos/excel](http://localhost:8080/proyectos/excel)
  - PDF: [http://localhost:8080/proyectos/pdf](http://localhost:8080/proyectos/pdf)

## Créditos

- Spring Boot
- Apache POI (Excel)
- OpenPDF (PDF)
- Thymeleaf
- Bootstrap

---

**Desarrollado por:**  
Andres Felipe Ramos Orjuela
