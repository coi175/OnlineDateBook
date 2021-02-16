package model;

import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private static final AtomicInteger count = new AtomicInteger(0);
    private final int ID;
    private final String name;
    private final String password;

    public User(String name, String password) {
        ID = count.incrementAndGet();
        this.name = name;
        this.password = password;
    }
}
