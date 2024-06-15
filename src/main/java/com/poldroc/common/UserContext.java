package com.poldroc.common;
/**
 * user context
 * @author Poldroc
 * @date 2024/6/14
 */

public class UserContext {

    private static final ThreadLocal<String> user = new ThreadLocal<>();

    private static final ThreadLocal<Integer> readerId = new ThreadLocal<>();

    public static String getUser() {
        return user.get();
    }

    public static Integer getReaderId() {
        return readerId.get();
    }

    public static void setUser(String user) {
        System.out.println("UserContext.setUser: " + user);
        UserContext.user.set(user);
    }

    public static void setReaderId(Integer readerId) {
        System.out.println("UserContext.setReaderId: " + readerId);
        UserContext.readerId.set(readerId);
    }

    public static void remove() {
        user.remove();
    }
}
