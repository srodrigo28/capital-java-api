### Java Investimentos
#### Melhores pontos
  * Docker compose com mysql

* Link:: 
```
https://www.youtube.com/watch?v=Tnl4YnB6E54
```
#### Passo a passo
* 1. Dependencias

* 2. Criar o arquivo 
 ```
 docker-compose.yml
 ```

 * 2. 1 Criar o arquivo
```
services:
  mysql:
    image: mysql
    ports:
      - "3306:3306"
    expose:
      - "3306"
    environment:
      - MYSQL_USER=root
      - MYSQL_PASSWORD=root
      - MYSQL_DATABASE=db_example
      - MYSQL_ROOT_PASSWORD=root
    volumes:
      - "./conf.d:/etc/mysql/conf.d:ro"
```

 * 3. Rodar o docker
 ```
 docker-compose up -d    
 ```

 * 4. Configurar o arquivo application.proprerties dentro da pasta resources

```
    spring.jpa.hibernate.ddl-auto=update
    spring.datasource.url=jdbc:mysql://localhost:3306/db_capital
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver

    # spring.jpa.show-sql=true
```
