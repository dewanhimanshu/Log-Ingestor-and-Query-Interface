# Log Ingestor and Query Interface

@himanshu dewan 

November 19, 2023 

<aside>
💡 This project is submitted as a part of SDE-1 and SDE Intern Assignment                                     **For any details related to this or project setup feel free to contact : himanshudewan.mca20.du@gmail.com**

</aside>

# 👿 Problem

> Develop a log ingestor system that can efficiently handle vast volumes of log data, and offer a simple interface for querying this data using full-text search or specific field filters.
> 

---

---

# 👀 Tech Stack Used

- Java 8
- Spring Boot 2.7.8
- Kafka 3.4
- ElasticSearch 7.17
- Kibana 7.17

---

# 🛫 Plan

**Log ingestor system working :**

→ Any microservice providing a log can hit our API `/log` for centeralized logging.

→ The API internally creates an event on kafka topic and returns 200 status code.

→ The kafka Consumer consumes the event and stores the data in MongoDB and  ElasticSearch

**Query Interface System Working(Using API that uses MongoDB) :**

→This method support all the logs generated in lifetime

→ Any user with valid userId can hit the API `/log/search` with query params

→ This api support full text searching and all the filters

→ The user has privleages that define what filters the user have acess to(Role Based)

→ search within specific date ranges is supported by this API

→ Allow combining multiple filters

**Query Interface System Working(Using Kibana dashboard) :**

→This can be used to search most recent data (TTL : 90 Days)

→ As the data is synced to elasticsearch

→ User can use kibana dashbaord to search logs

→ Kibana dashbaord has roles for users

→All kind of filters and their combinations is supported in kibana

---

# 😇System Design

**Proposed system design**

![Untitled](Log%20Ingestor%20and%20Query%20Interface%209ae3d67a72134fbf9411cd2513f4a891/Untitled.png)

*For the following the requirements of project , the logs are pushed to api first and that in turn push the logs to kafka queue.This can act as bottleneck and act affect scalibility.

→ The services producing logs can push the data to kafka queue . (Hit the api in this case which in turn the pushed the data to kafka queue)

→ Log Ingestor Service uses kafka consumer to consume the events 

→ The data is then stored in mongoDB and elasticSearch.

## ❓Why this design

- Kafka Queue can handle large volumne of data as it is distributed and robust system. Hence ensuring scalibility.
- MongoDB is chosen because log can be unstructured data.Different services can provide different types of log. Apart from that mongoDB support sharding and replication ensuring efficient and speed in searching.
- ElasticSearch and Kibana is chosen to store last 90 days of records. Elasticsearch allows you to store, search, and analyze huge volumes of data quickly and in near real-time and give back answers in milliseconds. Kibana provides dahboard for differnet filters.

---

# 😃 Solution Implemented

- Log Ingestion can be done using hiting an API or using interface
    
    ```jsx
    curl --location 'http://localhost:8080/api/v1/log' \
    --header 'Content-Type: application/json' \
    --data '{
    	"level": "trace",
    	"message": "Failed to connect to DB",
        "resourceId": "server-1234",
    	"timestamp": "2023-09-15T08:00:00Z",
    	"traceId": "abc-xyz-123",
        "spanId": "span-456",
        "commit": "5e5342f",
        "metadata": {
            "parentResourceId": "server-0987"
        }
    }'
    ```
    
    ![Untitled](Log%20Ingestor%20and%20Query%20Interface%209ae3d67a72134fbf9411cd2513f4a891/Untitled%201.png)
    
- For query we can use API (to query all the records)
    
    → Full Text Based Searching (can use both curl or web interface)
    
    ```jsx
    curl --location 'http://localhost:3000/api/v1/log/search?text=info' \
    --header 'userId: 777' \
    --data ''
    ```
    
    ![Untitled](Log%20Ingestor%20and%20Query%20Interface%209ae3d67a72134fbf9411cd2513f4a891/Untitled%202.png)
    
    →Filter Based Searching (Using web interface)
    
    ![Untitled](Log%20Ingestor%20and%20Query%20Interface%209ae3d67a72134fbf9411cd2513f4a891/Untitled%203.png)
    
    ```jsx
    curl --location 'http://localhost:3000/api/v1/log/search?text=overload' \
    --header 'userId: 777' \
    --data ''
    ```
    
    → Using Filters and their combination(can use both curl or web interface)
    
    ![Untitled](Log%20Ingestor%20and%20Query%20Interface%209ae3d67a72134fbf9411cd2513f4a891/Untitled%204.png)
    

→ Using date filter (can use startDate , endDate , startDate and endDate)

![Untitled](Log%20Ingestor%20and%20Query%20Interface%209ae3d67a72134fbf9411cd2513f4a891/Untitled%205.png)

```jsx
curl --location 'http://localhost:3000/api/v1/log/search?startDate=2023-09-15T02%3A30%3A00.000&endDate=2023-09-16T02%3A30%3A00.000' \
--header 'userId: 777' \
--data ''
```

→ Searching using Kibana 

→ Full text searching supported

→ All Filters supported

→ Regex based searching supported

![Untitled](Log%20Ingestor%20and%20Query%20Interface%209ae3d67a72134fbf9411cd2513f4a891/Untitled%206.png)

![Untitled](Log%20Ingestor%20and%20Query%20Interface%209ae3d67a72134fbf9411cd2513f4a891/Untitled%207.png)

---

# 🧑🏻‍💻Project Set Up

- Spring Boot ([https://spring.io/guides/gs/spring-boot/](https://spring.io/guides/gs/spring-boot/))
    - To start spring boot server, go to project and run
    
    ```jsx
    mvn spring-boot:run
    ```
    
- Kafka ([https://kafka.apache.org/quickstart](https://kafka.apache.org/quickstart))
    - 
    
    ```jsx
    tar -xzf kafka_2.13-3.6.0.tgz
    cd kafka_2.13-3.6.0
    
    # Start the ZooKeeper service
    bin/zookeeper-server-start.sh config/zookeeper.properties
    
    # Start the Kafka broker service , use port 9092
    bin/kafka-server-start.sh config/server.properties
    ```
    
- Elasticsearch([https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-elasticsearch-on-ubuntu-22-04](https://www.digitalocean.com/community/tutorials/how-to-install-and-configure-elasticsearch-on-ubuntu-22-04))
    
    ```jsx
    #start elasticsearch, use port 9200
    sudo systemctl start elasticsearch
    ```
    
- Kibana([https://www.elastic.co/downloads/kibana](https://www.elastic.co/downloads/kibana))
    
    ```jsx
    sudo systemctl start kibana.service
    
    #Kibana dashboard, http://localhost:5601/
    ```
    
- MongoDB
    
    ```jsx
    #configure it to run on port 27017
    
    #Create a DB , run it in mongoshell
    use LogDB
    
    #insert role with access level
    db.role.insertOne({
      userId: "777",
      allowedAccessFields: ["level", "message", "resourceId", "timestamp", "traceId", "spanId", "commit", "meta.parentResourceId"]
    });
    
    ```
    
    All indexes are created automatically using spring boot
    
    ---
    

# 🚀Things Achieved

- [x]  Develop a mechanism to ingest logs in the provided format → (Develop API and Interface for it)
- [x]  Ensure scalability to handle high volumes of logs efficiently → Used Kafka for it
- [x]  Mitigate potential bottlenecks such as I/O operations, database write speeds, etc. → Used Kafka for it , hybrid solution using mongoDb and ElasticSearch
- [x]  Make sure that the logs are ingested via an HTTP server, which runs on port `3000` by default

- [x]  Offer a user interface (Web UI or CLI) for full-text search across logs. → (API created for this)
- [x]  Include various filters
- [x]  Aim for efficient and quick search results.(Added elasticsearch for it_

## 🚀Advance Features

- [x]  Implement search within specific date ranges. → API support startDate and EndDate
- [ ]  Utilize regular expressions for search → Kibana dashboard support this feature. Yet to add it in API
- [x]  Allow combining multiple filters.
- [x]  Provide real-time log ingestion and searching capabilities (Using near real time ingestion using kakfa and elasticsearch)
- [x]  Implement role-based access to the query interface.

---

# 🌕Future / Know Issues

- Currently we are sending log data using HTTP request. This can act as botttneck and uses lot of resources and bandwith. Implement **file beat** to send logs to log ingestor.
- Add validations and add test cases for the application. So the code is more robust.