# Workation CSV Batch Importer

This Spring Boot project imports **Workation** data from a CSV file into a PostgreSQL database using **Spring Batch**.  
The batch job can be triggered manually or automatically at configurable intervals using a scheduler. PostgreSQL runs via Docker Compose for easy setup.

---

## Features

- CSV to PostgreSQL import using **Spring Batch**
- Configurable **scheduler** for automatic job execution
- PostgreSQL database setup via **Docker Compose**
- Logging and error handling for batch jobs

---

## Prerequisites

- Java 17+
- Maven 3.8+
- Docker & Docker Compose

---

## Getting Started

1. **Start PostgreSQL using Docker Compose**

```bash
docker-compose up -d
```

2. **Run the Spring Boot application**

```bash
mvn spring-boot:run
```

### Scheduler Configuration
```bash
batch.job.cron=0 0 * * * ?   # Cron expression example: every hour
```
