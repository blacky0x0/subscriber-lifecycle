package com.github.blacky.subscriber_lifecycle.tool;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import java.sql.SQLException;

public class InitDatabase {

    public static void main(String[] args) throws SQLException {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(new HikariDataSource() {{
            setJdbcUrl("jdbc:postgresql://localhost:5432/");
            setUsername("postgres");
            setPassword("postgres");
        }});

        String dbQuery = "select coalesce((SELECT 'CREATE DATABASE postgres' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'postgres')), 'select 1')";
        String eventualDbQuery = jdbcTemplate.queryForObject(dbQuery, String.class);
        jdbcTemplate.execute(eventualDbQuery);
        jdbcTemplate.execute("SET search_path = public");

        Resource schemaResource = new DefaultResourceLoader().getResource("schema-postgresql.sql");
        Resource dataResource = new DefaultResourceLoader().getResource("data-postgresql.sql");

        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), schemaResource);
        ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), dataResource);
    }

}
