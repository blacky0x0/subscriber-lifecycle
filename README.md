
## Prepare Database
Install Postgres v10 or any other. 
By default, user and database are named as `postgres`. 
Change credentials in `application.properties` file and other places if needed. 
Run `com.github.blacky.subscriber_lifecycle.tool.InitDatabase` script 
```
./mvnw clean install -DskipTests
cd target/subscriber-lifecycle/WEB-INF/
java -classpath "lib/*:classes/." com.github.blacky.subscriber_lifecycle.tool.InitDatabase
```
or execute `schema-postgresql.sql`, `data-postgresql.sql` scripts under `src/main/resources/` directory.

## Endpoints
Get subscriber's balance and status:
```
GET {url:port}/account/{msisdn}
```

Make a call:
```
POST {url:port}/call
JSON body: {"to": "target msisdn", "from": "source msisdn"} 
```

Send a SMS:
```
POST {url:port}/sms
JSON body: {"to": "target msisdn", "from": "source msisdn", "text": "any text message"}
```

Make a deposit to specific phone number:
```
PUT {url:port}/account/deposit
JSON body: {"amount": 1, "msisdn": "target msisdn"}
```

Examples:
```
curl -i -X GET localhost:8080/account/+12025008080
curl -i -X POST -H "Content-Type: application/json" localhost:8080/call --data '{"to": "+12025008081", "from": "+12025008080"}'
curl -i -X POST -H "Content-Type: application/json" localhost:8080/sms --data '{"to": "+12025008081", "from": "+12025008080", "text", "Good news, everyone!"}'
curl -i -X PUT -H "Content-Type: application/json" localhost:8080/account/deposit --data '{"amount": 100, "msisdn": "+12025008080"}'
```

## Lombok
Install [Lombok plugin] and configure:
```
Intellij Idea -> Settings -> Compiler -> Annotation Processors -> Enable annotation processing
```

## Tests
Some tests require the [Docker engine] so you need to install and configure it before start tests with [TestContainer's framework].
These tests use embedded Postgres `postgres:10-alpine` version, so you can connect to it in debug mode. 

### Skip Tests
Use `-DskipTests` parameter to skip tests while building an app.
```
./mvnw clean install -DskipTests
```

## Maven Wrapper

### Maven Wrapper: New Project
```
mvn -N io.takari:maven:0.7.6:wrapper -Dmaven=3.6.2
```

### Maven Wrapper: Build & Run Project
```
./mvnw clean install
./mvnw spring-boot:run
```

## Links
* [Takari Maven Wrapper]
* [Takari Maven Plugin]
* [MVN Repository - Maven Core]
* [MVN Repository - Takari Maven Wrapper]
* [MVN Repository - Takari Maven Plugin]

[Takari Maven Wrapper]: https://github.com/takari/maven-wrapper
[Takari Maven Plugin]: https://github.com/takari/takari-maven-plugin
[MVN Repository - Maven Core]: https://mvnrepository.com/artifact/org.apache.maven/maven-core
[MVN Repository - Takari Maven Wrapper]: https://mvnrepository.com/artifact/io.takari/maven-wrapper
[MVN Repository - Takari Maven Plugin]: https://mvnrepository.com/artifact/io.takari/maven
[Lombok plugin]: https://plugins.jetbrains.com/plugin/6317-lombok/
[Docker engine]: https://docs.docker.com/
[Docker Engine: Install on Ubuntu]: https://docs.docker.com/install/linux/docker-ce/ubuntu/
[Docker Engine: Configure groups on Ubuntu]: https://docs.docker.com/install/linux/linux-postinstall/
[TestContainer's framework]: https://www.testcontainers.org/
[Testcontainers: Postgres Module]: https://www.testcontainers.org/modules/databases/postgres/