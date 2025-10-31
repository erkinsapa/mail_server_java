import example.*;

import java.util.*;

public class Main {
    private static Scanner input = new Scanner(System.in);
    private static UserStorage userStorage = new UserStorage();

    public static void main(String[] args) {
        intro();
        System.out.println("============================");
        while (true) {
            System.out.print("> ");
            String command = input.next();
            switch (command) {
                case "add" -> add();
                case "send" -> send();
                case "inbox" -> inbox();
                case "spam" -> spam();
                case "setfilter" -> setfiler();
                case "list" -> list();
                case "info" -> info();
                case "exit" -> {
                    System.out.println("Отключение программы");
                    return;
                }
                default -> System.out.println("Ошибка: напишите 'info' для списка доступных команд" + "\n");
            }
        }
    }

    private static void info() {
        System.out.println("Доступные команды:");
        System.out.println("add - добавить пользователя");
        System.out.println("send - отправить письмо пользователю");
        System.out.println("inbox - почтовый ящик");
        System.out.println("spam - письмо спам");
        System.out.println("setfilter - настройка фильтров спама");
        System.out.println("list - список пользователей");
    }

    public static void intro() {
        System.out.println(" --- Почтовая система --- ");
        System.out.print("Выполнял Сапарбеков Э.У БИ2403");
        System.out.println();
        System.out.println("Для информации по командам напишите -info");
    }

    private static void add() {
        String userName = input.next();
        userStorage.addUser(userName);
        System.out.println("Пользователь '" + userName + "' добавлен");
    }

    private static void send() {
        System.out.print("От ");
        System.out.println();
        System.out.print('>');
        String senderName = input.next();
        User sender = userStorage.getUser(senderName);
        if (sender == null) {
            System.out.println("Ошибка: неизвестный отправитель");
        }
        System.out.print("Кому");
        System.out.println();
        System.out.print('>');
        String receiverName = input.next();
        User receiver = userStorage.getUser(receiverName);
        if (receiver == null) {
            System.out.println("Ошибка: неизвестный получатель ");
        }
        System.out.print("Заголовок");
        System.out.println();
        System.out.print('>');
        String caption = input.next();
        System.out.print("Текст");
        System.out.print('>');
        if (sender == null || receiver == null) {
        } else {
            String text = input.nextLine().trim();
            sender.sendMessage(caption, text, receiver);
            System.out.println("Отправлено сообщение от'" + senderName + "' для '" + receiverName + "'.");
        }
    }

    private static void inbox() {
        System.out.println("Введите пользователя: ");
        System.out.println("============================");
        System.out.println(" >");
        String userName = input.next();
        User user = userStorage.getUser(userName);

        if (user == null) {
            System.out.println("Ошибка: пользователь не существует ");
        } else {
            System.out.println("============================");
            System.out.println(userName);
            for (Message message : user.getInbox()) {
                System.out.println(message);
            }
        }
    }

    private static void spam() {
        System.out.println("Имя пользователя: ");
        String userName = input.next();
        System.out.println("============================");
        User user = userStorage.getUser(userName);

        if (user == null) {
            System.out.println("Ошибка, пользователь не существует.");
        } else {
            System.out.println(userName + "это ");
            System.out.println("> ");
            for (Message message : user.getSpam()) {
                System.out.println(message);
            }
        }
    }

    private static void setfiler() {
        String userName = input.next();
        String filterType = input.next();
        User user = userStorage.getUser(userName);
        if (user == null) {
            System.out.println("Ощибка, пользователь не существует.");
        } else {
            if (filterType.equals("simple")) {
                user.setSpamFilter(new SimpleSpamFilter());
                System.out.println("Фильтр типа 'simple' на " + userName + " применен");
            } else if (filterType.equals("keywords")) {
                System.out.println("Введите слово, для настройки фильтра");
                input.nextLine();
                String keywordsInput = input.nextLine();
                Set<String> keywords = new HashSet<>(Arrays.asList(keywordsInput.split(" ")));
                user.setSpamFilter(new KeywordsSpamFilter(keywords));
                System.out.println("Фильтр типа 'keywords' на " + userName + " применен");
            } else if (filterType.equals("repetition")) {
                System.out.println("Введите количество упоминанийц");
                int maxRepetitions = input.nextInt();
                user.setSpamFilter(new RepetitionSpamFilter(maxRepetitions));
                System.out.println("Фильтр типа 'repetit' на " + userName + " применен");
            } else if (filterType.equals("sender")) {
                System.out.println("Введите имя спамера");
                String senderName = input.next();
                user.setSpamFilter(new SenderSpamFilter(senderName));
                System.out.println("Фильтр типа 'sender' на " + userName + " применен");
            } else {
                System.out.println("Неизвезный фильтр");
            }
        }
    }

    private static void list() {
        userStorage.list();
    }
}