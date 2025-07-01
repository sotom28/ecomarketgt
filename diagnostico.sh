#!/bin/bash

echo "=== DIAGNOSTICO ECOMARKETGT ==="

echo "1. Verificando Java..."
java -version

echo "2. Verificando conectividad a MySQL..."
nc -zv 44.201.241.98 3306

echo "3. Listando archivos JAR..."
ls -la ~/ecomarketgt*.jar

echo "4. Verificando configuración de red..."
netstat -tlnp | grep 8080

echo "5. Intentando ejecutar aplicación..."
java -jar -Dspring.profiles.active=prod ~/ecomarketgt-new.jar &
JAVA_PID=$!

echo "Aplicación iniciada con PID: $JAVA_PID"
echo "Esperando 10 segundos..."
sleep 10

echo "6. Verificando logs..."
ps aux | grep java

echo "7. Probando endpoint..."
curl -f http://localhost:8080/actuator/health 2>/dev/null || echo "Endpoint no disponible"

echo "=== FIN DIAGNOSTICO ==="
