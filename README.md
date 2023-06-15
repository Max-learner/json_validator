# Тестовое задание
**Содержание**
- [Description](/#description)
- [Results](/#results)
- [Goals](/#goals)
- [Requirements](/#requirements)
- [Main Tools](/#main-tools)
- [CLI parameters](/#cli)
- [Getting started](/#getting-started)
- [Author](/#author)

## Description 
Создайте проект с использованием Java, Junit и Maven (альтернативно TestNG, Gradle) по автоматизированному тестированию приложения. Требуется разработать минимально-необходимый набор тестов (позитивных и негативных). Все тесты должны быть в одном классе, при этом должна быть возможность запуска из командной строки отдельно для позитивных и негативных.

## Results
Выполнил позитивные тесты с парсингом json файла, негативные через генерацию объекты POJO.

Изначально планировал делать валидацию содержимого JSON на соответствие JSON Schema, для чего создал схему на основе json из ТЗ и подключил библиотеку com.networknt:json-schema-validator, но эта библиотека не отрабатывает conditionals, что прояснилось несколько позже. 

## Goals
Для упрощения задачи предполагается, что приложение не имеет никаких входных параметров. Результатом работы приложения выступает файл в формате json.
В данном файле случайным образом могут изменяться следующие значения:
1.	Массив detectives может иметь не менее одного и не более 3х объектов. При этом поле MainId может случайным образом меняться от 0 до 10
2.	CategoryID принимает значения 1 или 2
3.	Элемент extra может принимать значение null только для CategoryID=2
4.	Массив extraArray дожен иметь минимум один элемент для CategoryID=1
5.	Поле success принимает значение true только если в массиве detectives есть элемент с firstName ="Sherlock"
Остальными условиями для упрощения задачи можно пренебречь.

## Requirements
- Java JDK from 11 to [15](https://www.oracle.com/java/technologies/downloads/archive/)
- [Gradle](https://gradle.org/releases/)
- Любая Java IDE. e.g. [IntelliJ Idea](https://www.jetbrains.com/idea/download/#section=windows)

## Main Tools
- [Gradle](https://docs.gradle.org/current/userguide/userguide.html)
- [TestNG 7](https://testng.org/doc/documentation-main.html)
- [Lombok](https://projectlombok.org/features/)
- [Jackson](https://github.com/FasterXML/jackson)

## CLI parameters
CLI параметры имеют форму:
- тестовые группы </br>
    **parameters**: positiveTests, negativeTests

**Пример**  
`clean positiveTests`  
С этими параметрами запустятся позитивные тесты для проверки файла json.

`clean negativeTests`  
С этими параметрами запустятся негативные тесты для проверки json через объект.

## Getting started
1. Установите:
    * Java JDK from 11 to [15](https://www.oracle.com/java/technologies/downloads/archive/)
    * [Gradle](https://gradle.org/releases/)

2. Склонируйте репозиторий в любую папку

````batch  

git clone https://github.com/Max-learner/json_validator.git

````

3. Запустите тесты с желаемыми параметрами, например:  

````batch

gradle clean positiveTests

````

## Author
-  [Maksim Plotnikov](https://github.com/Max-learner/json_validator.git) 
