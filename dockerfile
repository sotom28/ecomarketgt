FROM eclipse-temurin:17-jdk-jammy

# Actualizar paquetes y limpiar caché
RUN apt-get update && apt-get upgrade -y && apt-get install -y --no-install-recommends tzdata && rm -rf /var/lib/apt/lists/*

# Instalar timezone data
RUN apt-get update && apt-get install -y tzdata && rm -rf /var/lib/apt/lists/*

# Establecer timezone
ENV TZ=America/Santiago

# Crear directorio de trabajo
WORKDIR /app

# Copiar el JAR ya compilado
COPY target/ecomarketgt-0.0.1-SNAPSHOT.jar app.jar

# Exponer puerto
EXPOSE 8080

# Variables de entorno
ENV SPRING_PROFILES_ACTIVE=prod
ENV JAVA_OPTS="-Xmx512m -Xms256m"

# Comando para ejecutar la aplicación
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]