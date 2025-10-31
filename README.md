Mail Server Java
Система обмена сообщениями с фильтрацией спама

Описание проекта
Программа реализует простую почтовую систему на Java с возможностью отправки сообщений между пользователями и автоматической фильтрацией спама с помощью различных фильтров (ключевые слова, повторяемость, отправитель, композиция фильтров).

Структура проекта
text
mail_server_java/
│
├── src/example/                  # Основной код
│   ├── CompositeSpamFilter.java
│   ├── KeywordsSpamFilter.java
│   ├── Main.java
│   ├── Message.java
│   ├── RepetitionSpamFilter.java
│   ├── SenderSpamFilter.java
│   ├── SimpleSpamFilter.java
│   ├── SpamFilter.java
│   ├── User.java
│   └── UserStorage.java
│
├── test/example/                 # Тесты (JUnit 5)
│   ├── KeywordsSpamFilterTest.java
│   ├── MainTest.java
│   ├── MessageTest.java
│   ├── RepetitionSpamFilesTest.java
│   ├── SenderSpamFilterTest.java
│   └── SimpleSpamFilterTest.java
│
├── .gitignore
├── Mail server.iml
└── README.md
Основные особенности
Создание и управление пользователями

Обмен сообщениями с распределением на Входящие, Исходящие и Спам

Гибкая настройка спам-фильтра для пользователя (Ключевые слова, Повторяемость, Отправитель, Композитный фильтр)

Разделение кода на доменные классы и покрытие тестами большинства функций

Используемые технологии
Java 21+

JUnit 5 для тестов

IntelliJ IDEA (рекомендуемо)

Пример использования
java
User alice = new User("Alice");
User bob = new User("Bob");

alice.sendMessage("Hello!", "How are you?", bob);

bob.setSpamFilter(new KeywordsSpamFilter(Arrays.asList("spam", "buy now")));
Инструкция по запуску
Клонируйте репозиторий и откройте проект в IntelliJ IDEA.

Запустите Main.java для демонстрации работы.

Тесты запускайте через JUnit в папке test/example.

Чистота кода
Каждый класс в отдельном файле.

Используется package example.

Все основные методы снабжены краткими JavaDoc-комментариями.

Переменные и методы названы по Java convention.
