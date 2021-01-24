# spring-boot-netcracker

Spring-boot web приложение, реализующее CRUD для сущностей Студент и Группа. 

В качестве хранилища данных была выбрана база данных Postgres.

Фронт реализован на thymeleaf

## Бизнес-слой

![Image alt](https://github.com/nikitusik/spring-boot-netcracker/raw/master/src/main/resources/groups.png)



## Начало работы

Для работы приложения, необходимо скачать Postgres, затем в консоли базы данных выполнить скрипт лежащий в init.sql

Далее необходимо поменять файл application.properties на ваши настройки к бд

После этого можно запускать приложение и переходить по адресу http://localhost:8080/
