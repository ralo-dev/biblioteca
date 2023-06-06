# Biblioteca API

Este es un proyecto de API REST desarrollado en Spring Boot, que proporciona funcionalidades relacionadas con una biblioteca. El proyecto utiliza Lombok, JPA y otras tecnologías para facilitar el desarrollo y la persistencia de datos.

## Requisitos previos

Antes de ejecutar este proyecto, asegúrate de tener instalado lo siguiente:

- Java Development Kit (JDK) 8 o superior.
- Maven para gestionar las dependencias.

## Configuración del proyecto

1. Clona el repositorio de GitHub:

```
git clone https://github.com/ralo-dev/biblioteca
```

2. Navega hasta el directorio del proyecto:

```
cd biblioteca
```

3. Compila el proyecto y genera el archivo JAR:

```
mvn package
```

## Configuración de la base de datos

Este proyecto utiliza una base de datos relacional compatible con JPA, como MySQL, PostgreSQL u Oracle. Asegúrate de tener una base de datos configurada y actualiza la configuración en el archivo `application.properties` ubicado en `src/main/resources`. Por ejemplo, para MySQL:

```
spring.datasource.url=jdbc:mysql://localhost:3306/biblioteca
spring.datasource.username=tu-usuario
spring.datasource.password=tu-contraseña
```

Asegúrate de crear la base de datos `biblioteca` en tu servidor de base de datos antes de ejecutar la aplicación.

## Ejecución del proyecto

Una vez que hayas configurado la base de datos, puedes ejecutar la aplicación utilizando el siguiente comando:

```
mvn spring-boot:run
```

La aplicación se ejecutará en `http://localhost:8080`.

## Uso de la API

Esta API REST proporciona endpoints para gestionar libros, autores, usuarios y otros recursos relacionados con una biblioteca. A continuación, se detallan algunos ejemplos de uso de los endpoints principales.
Para conocer a profundidad los endpoints, revisa las clases Controller dentro de los paquetes de cada módulo.

