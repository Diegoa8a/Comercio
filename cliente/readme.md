# Instrucciones de Despliegue

## 1. Ejecutar Maven Clean Install Package en los Tres Servicios
```sh
mvn clean install package
```

## 2. Desplegar el Microservicio Cliente
### Situarse en la Carpeta Raíz del Microservicio Cliente
- Ejecutar para levantar la base de datos Postgres en Docker:
  ```sh
  docker-compose -f docker-compose-infra.yml up -d
  ```
- Ejecutar para desplegar el servicio en Docker:
  ```sh
  docker-compose up --build
  ```

## 3. Desplegar el Microservicio de Productos en Local

## 4. Desplegar el Microservicio Pedido
### Situarse en la Carpeta Raíz del Microservicio Pedido
- Ejecutar para levantar la base de datos MongoDB en Docker:
  ```sh
  docker-compose -f docker-compose-infra.yml up -d
  ```
- Desplegar el microservicio en local.

## Para Probar la Aplicación"
1. Generar un cliente (tipoDocumento solo puede ser: `DNI`, `PASAPORTE`, `CARNET_DE_EXTRANJERIA`).
2. Generar productos (La categoriaId solo puede ser: `1`, `2`, `3`).
3. Generar el pedido.