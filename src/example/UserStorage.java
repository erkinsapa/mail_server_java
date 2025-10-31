package example;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private Map<String, User> users;

    public UserStorage() {
        this.users = new HashMap<>();
    }
    public void addUser(String userName) {
        if (!users.containsKey(userName)) {
            users.put(userName, new User(userName));
        } else {
            System.out.println("Пользователь уже добавлен ранее '" + userName);
        }
    }

    public User getUser(String userName) {
        return users.get(userName);
    }

    public boolean userExists(String userName) {
        return users.containsKey(userName);
    }

    public void list() {
        if (users.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            System.out.println("Пользователи в системе:");
            for (String userName : users.keySet()) {
                System.out.println("- " + userName);
            }
            System.out.println(" Количество пользователей в системе" + users.size());
        }
    }
}
