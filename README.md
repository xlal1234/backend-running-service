Running-information-analysis-service
**running-information-analysis-service** is a RESTful service in spring boot, using *[Maven](https://maven.apache.org/)* as build tool.

**Feature List:**
- Upload runningInfo
- delete all runningInfo
- delete runningInfo by runningId
- find runningInfo by runningId
- get all runningInfo with sort and pagination. Page number, page size, sort direction, and sort property can be customized.
- get all runningInfo that belong to one user. Result is sorted by timestamp in descending order (i.e. the most recent one is on top). 

## Requirements 
* Java Platform (JDK) 8
* Apache Maven
* Docker
* Docker Compose 

## Installation and Quick Start
#### 1. Download project file
```
cd running-information-analysis-service
```
#### 2. Use Docker to run MySQL server
> Configure MySQL image version in `docker-compose.yml` (Default version is 5.6). Run following code in terminal.
```
docker-compose up -d
```
#### 3. Login MySQL database inside the Docker container
> Run `docker ps` to check the container name. Replace the `containerName` with your container name in following code before running it. The default password for root user is `root`. 

```
docker exec -ti containerName mysql -uroot -p
```
> Create table RUNNING_ANALYSIS in database runningInfoAnalysis_db if not exist
```
mysql> SHOW DATABASES;
mysql> USE running_information_analysis_db;
mysql> SHOW TABLES;
mysql> CREATE TABLE RUNNING_ANALYSIS (id BIGINT(20) AUTO_INCREMENT, runningId VARCHAR(50), latitude DOUBLE, longitude DOUBLE, runningDistance DOUBLE, totalRunningTime DOUBLE, timestamp TIMESTAMP, healthWarningLevel INT, heartRate INT, userName VARCHAR(30), userAddress VARCHAR(50), PRIMARY KEY(id));
mysql> EXIT;
```
#### 4. Build and run Spring Boot application
> Change directory to `/your/path/running-information-analysis-service` before running the following commands.
```
mvn clean install
java -jar ./target/running-information-analysis-service-1.0.0.BUILD-SNAPSHOT.jar
```
#### 5. Upload runningInfo.json data
> Run the command uhder the directory `/your/path/running-information-analysis-service`.
```
curl -H "Content-Type: application/json" localhost:9000/create -d @runningInfo.json
```
