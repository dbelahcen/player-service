# Player-Service
This service is a crud service for players.

## Setup

### IDE
Open the project as a maven project and run the main class `PlayerServiceApplication.java`

### Command Line
Go to the root of the project and execute the following command:
```
java -jar target/player-service-0.0.1-SNAPSHOT.jar
```

### Docker
A basic Dockerfile is provided to run it in a container.
Command (from the project root directory): 
```
docker build -t player-service:latest .
docker run --rm -p8080:8080 player-service:latest
```

## API Documentation

### Get all players

Path: `/api/players`

Parameters Description

| Parameter | Description | Default Value |
| :---: | :---: | :---: |
| offset | How many rows to skip | 0 |
| limit | How many rows to return | 10 |


### Get by ID

Returns a player by id. In case ID does not exist, it returns 404.
