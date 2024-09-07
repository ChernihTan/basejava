package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();

    public static void main(String[] args) {
        Resume r1 = new Resume();
        r1.setUuid("uuid1");
        Resume r2 = new Resume();
        r2.setUuid("uuid2");
        Resume r3 = new Resume();
        r3.setUuid("uuid3");

        System.out.println(r1.equals(r2));

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);

        System.out.println("\nCheck  update() - r4");
        Resume r4 = new Resume();
        try {
            ARRAY_STORAGE.update(r4);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nCheck №1 delete()");
        try {
            ARRAY_STORAGE.delete(null);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Check №2 delete()");
        try {
            ARRAY_STORAGE.delete("nnn1");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Check №3 delete()");
        try {
            ARRAY_STORAGE.delete("");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("\nCheck №1 get()");
        try {
            System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Check №2 get()");
        try {
            ARRAY_STORAGE.get(null);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Check №3 get()");
        try {
            System.out.println("Get r4: " + ARRAY_STORAGE.get(r4.getUuid()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Check №4 get()");
        try {
            System.out.println("Get dummy: " + ARRAY_STORAGE.get("dummy"));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\nCheck №1 save()");
        try {
            ARRAY_STORAGE.save(r4);
            System.out.println("Save r4: " + ARRAY_STORAGE.get(r4.getUuid()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Check №2 save()");
        try {
            ARRAY_STORAGE.save(r1);
            System.out.println("Save r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }


        System.out.println("Size: " + ARRAY_STORAGE.size());
        printAll();
        try {
            ARRAY_STORAGE.delete(r1.getUuid());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
