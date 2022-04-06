# 스프링부터 JPA 블로그 V3

### 1. 의존성
- Devtools
- Spring web (mvc)
- Mustache
- JPA
- MariaDB
- Lombok
- Security
- Validation

### 2. DB설정
```sql
CREATE user 'green'@'%' IDENTIFIED BY 'green1234';
CREATE DATABASE greendb;
GRANT ALL PRIVILEGES ON greendb.* TO 'green'@'%';
```