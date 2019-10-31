
## Lombok
```
Intellij Idea -> Settings -> Compiler -> Annotation Processors -> Enable annotation processing
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
