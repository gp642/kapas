## README

The following is a quick guide to setup Kapas Project.

Prerequisites
-------------

* JDK 11
* Maven
* MySQL

Following environment variable(s) need to be set:

0. AES_128_KEY (16 characters. Example: `UniqueSecretKeys`)

Usual build tasks
-----------------

* Start server

  ```mvn spring-boot:run```

    Production:

  ```java -Djasypt.encryptor.password=[password] -jar [application-name].jar```

* Running database migrations:

  ```mvn compile flyway:migrate``` (on local) or ```mvn compile flyway:migrate -P [env]```

  ```mvn compile flyway:repair``` (on local) or ```mvn compile flyway:repair -P [env]```


Database on local environment
--------------------------------
Create copy of `application-prod.properties` to `application-local.properties`and change database values accordingly. This file will never be pushed on to git and will be only for local development work.
