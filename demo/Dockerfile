# Usa la imagen base de OpenJDK 17 en una versión ligera
FROM openjdk:21-slim

# Establece el directorio de trabajo en /app
#WORKDIR /app

# Copia el archivo JAR generado al contenedor
COPY target/demo-0.0.1-SNAPSHOT.jar demo.jar

# Expone el puerto 3000
EXPOSE 8080

# Define el comando de inicio para ejecutar la aplicación
CMD ["java", "-jar", "/demo.jar"]
