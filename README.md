# Инструкция

1. Клонировать проект в IntelliJIdea
2. Настроить подключение к базе данных
    1. Настроить файл application.properties
    2. Ввести название БД
    3. Логин и пароль
3. Запустить приложение и отправлять нужные запросы
4. Для проверки тестов необходим Docker. Так как тесты запускаются в контейнере.

# API

http://localhost:8080

+ POST /lord/ - добавление нового повелителя

Request

```JSON
{
  "name": "Lord Niko",
  "age": 20
}
```

+ POST /planet/ - добавление новой планеты

Request

```JSON
{
  "name": "Earth"
}
```

+ POST /lord/planet - назначение планеты повелителю

Request

```JSON
{
  "lordName": "Lord Niko",
  "planetName": "Earth"
}
```

+ DELETE /planet/ - удаление планеты

Request

```JSON
{
  "name": "Earth"
}
```

+ GET /lord/jobless - список всех безработных лордов

Response

```JSON
{
  "name": "Lord Niko",
  "age": 20,
  "planets" : [] 
}
```

+ GET /lord/topTenYoungest - список десяти самых молодых лордов

Response

```JSON
{
   "name": "Lord Niko",
   "age": 20,
   "planets" : []
}
```