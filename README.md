# Дипломный проект по профессии «Тестировщик»

##### Дипломный проект по автоматизации тестирования комплексного сервиса, взаимодействующего с СУБД и API Банка.
## Документация
* ### [План автоматизации тестирования](https://github.com/nmarenova/DiplomaQA/blob/master/documentation/Plan.md)

* ### [Отчёт по итогам тестирования](https://github.com/nmarenova/DiplomaQA/blob/master/documentation/Report.md)

* ### [Отчёт по итогам автоматизации](https://github.com/nmarenova/DiplomaQA/blob/master/documentation/Summary.md)

## Запуск программы и тестов


#### Для работы с MySQL
1. Запуск контейнера MySQL:
    ```
    docker-compose -f docker-compose-mysql.yml up -d
    ```
1. Запуск SUT:
    ```
    java -Dspring.datasource.url=jdbc:mysql://localhost:3306/app -jar artifacts/aqa-shop.jar
    ```

1. Запуск тестов осуществить в новом окне терминала:
    ```
    gradlew test -Ddb.url=jdbc:mysql://localhost:3306/app

    ```
   Для формирования отчета и его открытия в браузере выполнить команды:
      ```
   gradlew allureReport   
   gradlew allureServe
      ```
   
1. Остановить контейнеры:
    ```
    docker-compose -f docker-compose-mysql.yml down
    ```
1. Закрыть все окна терминала
   
#### Для работы с Postgres
1. Запуск Postgres
    ```
    docker-compose -f docker-compose-pgsql.yml up -d
    ```
1. Запуск SUT
    ```
    java -Dspring.datasource.url=jdbc:postgresql://localhost:5432/app -jar artifacts/aqa-shop.jar
    ```

1. Запуск тестов осуществить в новом окне терминала:
    ```
    gradlew test -Ddb.url=jdbc:postgresql://localhost:5432/app
    ```
   Для формирования отчета и его открытия в браузере выполнить команды 
      ```
      gradlew allureReport   
      gradlew allureServe
     ```
1. Остановить контейнеры
    ```
    docker-compose -f docker-compose-pgsql.yml down
    ```
1. Закрыть все окна терминала