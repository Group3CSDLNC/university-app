# University App (Spring Boot Java 8)

This is a sample Spring Boot project (Java 8) that demonstrates:
- MVC with Thymeleaf
- Calling SQL Server Stored Procedures using JdbcTemplate
- Models for the university schema (14 tables)

## How to use

1. Import the SQL script `sql/create_db_and_sprocs.sql` into your SQL Server to create tables, sample data and stored procedures.
2. Update `src/main/resources/application.properties` with your DB connection.
3. Build & run:
   - `mvn clean package`
   - `mvn spring-boot:run`
4. Visit:
   - `http://localhost:8080/khoa` - list of khoa
   - `http://localhost:8080/sinhvien/chuyennganh/{maCN}` - students by chuyennganh

