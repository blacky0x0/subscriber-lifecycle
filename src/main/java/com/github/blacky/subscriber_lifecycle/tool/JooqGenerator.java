package com.github.blacky.subscriber_lifecycle.tool;

import org.jooq.codegen.GenerationTool;
import org.jooq.meta.jaxb.*;

public class JooqGenerator {

    public static void main(String[] args) throws Exception {

        Configuration configuration = new Configuration()
                .withJdbc(new Jdbc()
                        .withDriver("org.postgresql.Driver")
                        .withUrl("jdbc:postgresql:postgres")
                        .withUser("postgres")
                        .withPassword("postgres"))
                .withGenerator(new Generator()
                        .withDatabase(new Database()
                                .withName("org.jooq.meta.postgres.PostgresDatabase")
                                .withIncludes(".*")
                                .withExcludes("")
                                .withInputSchema("public"))
                        .withTarget(new Target()
                                .withPackageName("com.github.blacky.subscriber_lifecycle.jooq")
                                .withDirectory("src/main/java"))
                        .withGenerate(new Generate()
                                .withDaos(true)
                                .withPojos(true)
                                .withSpringAnnotations(true)
                                .withValidationAnnotations(true)));

        GenerationTool.generate(configuration);
    }

}
