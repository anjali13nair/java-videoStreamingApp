# Video Streaming Microservices (Spring Boot)

This repository explains how to build a simple **video streaming application** using Spring Boot microservices. It covers the key components and how they fit together:

- Service Registry (Eureka)
- Configuration Server (Spring Cloud Config)
- API Gateway (Spring Cloud Gateway)
- Cloud configuration (centralized `application.properties`)
- Inter-service communication - RestTemplate
- Distributed tracing with Zipkin
- Docker & docker-compose for local orchestration (and notes for Kubernetes)

> This README is written as a practical, copy‑pasteable guide with configuration snippets and sample commands so you can spin up a working demo quickly.

---

## High-level architecture

```
[User / Client]
    |
    v
[API Gateway (Gateway Service)]
    |---> [Video Service] <--- (streams video files / range requests)
    |---> [Metadata Service] (catalog, search)
    

Supporting infra:
 - Eureka (Service Registry)
 - Spring Cloud Config Server
 - Zipkin (Distributed Tracing)
 - (Optional) Config-backed S3 / NFS for video storage
```

Key idea: API Gateway is the single public entrypoint, routes to backend microservices. Services register to Eureka and fetch configuration from the Config Server. Tracing is handled with Sleuth + Zipkin.

---

## Prerequisites

- Java 17+ (or Java 11 depending on your Spring Cloud compatibility)
- Maven 3.6+
- Docker & docker-compose (for local multi-service runs)
- (Optional) kubectl + minikube / kind / k3d for Kubernetes

---

## Microservices to create

1. `config-server` — Spring Cloud Config Server (serves centralized configs from a git repo or local filesystem), local filesystem is used here
2. `service-registry` — Netflix Eureka Service Registry
3. `api- gateway` — Spring Cloud Gateway (routes and applies cross-cutting concerns)
4. `movie-streaming-service` — Streams video content (supports HTTP Range requests)
5. `movie-catalog-service` — Stores video metadata (title, description, duration, URL)
6. `zipkin` — Zipkin service to collect traces

---

## How to Run
1. Clone the repository:
   ```bash
   git clone https://github.com/anjali13nair/java-videoStreamingApp.git

2. Navigate to each service directory and build the services:
    ```bash
      mvn clean install

3. Start the services in the following order:
-Service Registry
-API Gateway
-Question Service
-Quiz Service

4. Access the application through the API Gateway URL.


## Contact
If you have any questions, suggestions, or feedback, feel free to reach out:  
- **GitHub**: [@anjali13nair](https://github.com/anjali13nair)
- **Email**: anjalinnair13@gmail.com
- **LinkedIn**:[Anjali Nair](https://www.linkedin.com/in/anjalinnair13/)
