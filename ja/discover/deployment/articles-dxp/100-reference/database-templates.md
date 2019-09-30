---
header-id: database-templates
---

# データベース テンプレート

[TOC levels=1-4]

以下は、Liferayの組み込みデータソースで@product@がサポートするさまざまなデータベースを構成するためのテンプレートです。

## MariaDB

    jdbc.default.driverClassName=org.mariadb.jdbc.Driver
    jdbc.default.url=jdbc:mariadb://localhost/lportal?useUnicode=true&characterEncoding=UTF-8&useFastDateParsing=false
    jdbc.default.username=
    jdbc.default.password=

## MySQL

    jdbc.default.driverClassName=com.mysql.jdbc.Driver
    jdbc.default.url=jdbc:mysql://localhost/lportal?characterEncoding=UTF-8&dontTrackOpenResources=true&holdResultsOpenOverStatementClose=true&useFastDateParsing=false&useUnicode=true
    jdbc.default.username=
    jdbc.default.password=

## PostgreSQL

    jdbc.default.driverClassName=org.postgresql.Driver
    jdbc.default.url=jdbc:postgresql://localhost:5432/lportal
    jdbc.default.username=sa
    jdbc.default.password
