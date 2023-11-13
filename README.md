# sensor-service

## Конфигурация и запуск
В сервисе в файле application.properties устанавливаются конфигурационные : server.port - порт сервиса, server.url - url сервера (например, http://localhost:8080) и sensor.name - **название сенсора** (сенсоры не могут нести одинаковое название)
Компиляция и формирование jar - bootJar. Локация для запуска: /build/libs/sensor-server-0.0.1-SNAPSHOT.jar
