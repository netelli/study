package com.github.netelli;

/**
 * Created by user on 08.12.2016.
 */
public class DBDrivers {
    private DBDrivers() {
    }

    public static void load() throws ClassNotFoundException {
        Class.forName("org.h2.Driver");
    }
}
