# Mail Server Java

Система обмена сообщениями с фильтрацией спама

---

## 📋 Описание проекта

Программа реализует простую почтовую систему на Java с возможностью отправки сообщений между пользователями и автоматической фильтрацией спама с помощью различных фильтров (ключевые слова, повторяемость, отправитель, композиция фильтров).

---

## 📁 Структура проекта

```
mail_server_java/
│
├── src/
│   └── example/
│       ├── CompositeSpamFilter.java     # Композитный фильтр
│       ├── KeywordsSpamFilter.java      # Фильтр по ключевым словам
│       ├── Main.java                    # Точка входа программы
│       ├── Message.java                 # Класс сообщения
│       ├── RepetitionSpamFilter.java    # Фильтр по повторениям
│       ├── SenderSpamFilter.java        # Фильтр по отправителю
│       ├── SimpleSpamFilter.java        # Простой базовый фильтр
│       ├── SpamFilter.java              # Интерфейс спам-фильтра
│       ├── User.java                    # Класс пользователя
│       └── UserStorage.java             # Хранилище пользователей
│
├── test/
│   └── example/
│       ├── KeywordsSpamFilterTest.java
│       ├── MainTest.java
│       ├── MessageTest.java
│       ├── RepetitionSpamFilesTest.java
│       ├── SenderSpamFilterTest.java
│       └── SimpleSpamFilterTest.java
│
├── .gitignore
├── Mail server.iml
└── README.md
```

---

## ✨ Основные возможности

- ✅ Создание и управление пользователями
- ✅ Обмен сообщениями между пользователями
- ✅ Автоматическое распределение на **Входящие**, **Исходящие** и **Спам**
- ✅ Гибкая настройка спам-фильтров:
  - **SimpleSpamFilter** — базовый фильтр
  - **KeywordsSpamFilter** — фильтрация по ключевым словам
  - **RepetitionSpamFilter** — обнаружение повторяющихся паттернов
  - **SenderSpamFilter** — блокировка отправителей
  - **CompositeSpamFilter** — комбинация нескольких фильтров
- ✅ Полное покрытие тестами (JUnit 5)

---

## 🛠️ Технологии

- **Java 21+**
- **JUnit 5** (для тестирования)
- **IntelliJ IDEA** (рекомендуемая IDE)

---

## 🚀 Инструкция по запуску

### Требования

- Java 21 или выше
- IntelliJ IDEA (или другая Java IDE)

### Запуск программы

1. Откройте проект в IntelliJ IDEA
2. Найдите файл `src/example/Main.java`
3. Запустите программу:
   - Правой кнопкой мыши → **Run 'Main.main()'**

### Запуск тестов

1. Перейдите в папку `test/example/`
2. Правой кнопкой мыши → **Run 'Tests in example'**

---

## 💡 Примеры использования

### Создание пользователей и отправка сообщения

```java
// Создание пользователей
User alice = new User("Alice");
User bob = new User("Bob");

// Отправка сообщения
alice.sendMessage("Hello", "How are you?", bob);

// Просмотр входящих сообщений
List<Message> inbox = bob.getInbox();
```

### Настройка спам-фильтра

```java
// Фильтр по ключевым словам
List<String> keywords = Arrays.asList("spam", "buy now", "click here");
SpamFilter keywordFilter = new KeywordsSpamFilter(keywords);

// Фильтр по отправителю
SpamFilter senderFilter = new SenderSpamFilter("Spammer");

// Композитный фильтр (объединение нескольких фильтров)
SpamFilter compositeFilter = new CompositeSpamFilter(
    keywordFilter, 
    senderFilter
);

// Установка фильтра пользователю
bob.setSpamFilter(compositeFilter);
```

---

## 🧪 Тестирование

Проект включает полное покрытие тестами с использованием **JUnit 5**:

- Тесты классов сообщений
- Тесты всех типов спам-фильтров
- Интеграционные тесты

Все тесты находятся в папке `test/example/`

---

## 📦 Сборка архива для сдачи

Для сдачи работы создайте архив **HW22401.zip**, включающий:

- Папку `src/`
- Папку `test/`
- Файл `README.md`

### Создание архива через командную строку:

```bash
zip -r HW22401.zip src/ test/ README.md
```

---

## 📚 Основные классы

### `User`

- `sendMessage(String caption, String text, User receiver)` — отправка сообщения
- `getInbox()` — просмотр входящих
- `getOutbox()` — просмотр исходящих
- `getSpam()` — просмотр спама
- `setSpamFilter(SpamFilter filter)` — установка фильтра

### `Message`

Хранит информацию о сообщении:
- Заголовок (caption)
- Текст (text)
- Отправитель (sender)
- Получатель (receiver)

### `SpamFilter` (интерфейс)

- `boolean isSpam(Message message)` — проверка сообщения на спам

---

## 📝 Чистота кода

- ✅ Каждый класс в отдельном файле
- ✅ Используется package `example`
- ✅ Переменные и методы названы по Java convention
- ✅ Код покрыт тестами

---

## 👤 Автор

Проект выполнен в рамках домашнего задания по курсу программирования на Java (2024 год).


