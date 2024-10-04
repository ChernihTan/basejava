package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.ArrayStorage;
import com.urise.webapp.storage.SortedArrayStorage;
import com.urise.webapp.storage.Storage;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage SORTED_ARRAY_STORAGE = new SortedArrayStorage();
    private static final Storage ARRAY_STORAGE = new ArrayStorage();


    public static void main(String[] args) {
        Resume r0 = new Resume("uuid0");
        Resume r1 = new Resume("uuid1");
        Resume r2 = new Resume("uuid2");
        Resume r3 = new Resume("uuid3");
        Resume r4 = new Resume("uuid4");
        Resume r5 = new Resume("uuid5");
        Resume r6 = new Resume("uuid6");

        System.out.print("SORTED_ARRAY_STORAGE");
        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r6);
        SORTED_ARRAY_STORAGE.save(r5);
        SORTED_ARRAY_STORAGE.save(r4);
        SORTED_ARRAY_STORAGE.save(r0);
        printAll();
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

        SORTED_ARRAY_STORAGE.delete(r1.getUuid());
        SORTED_ARRAY_STORAGE.delete(r5.getUuid());
        printAll();
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

        SORTED_ARRAY_STORAGE.update(r3);
        printAll();
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

        SORTED_ARRAY_STORAGE.clear();
        printAll();
        System.out.println("Size: " + SORTED_ARRAY_STORAGE.size());

        System.out.println("\nARRAY_STORAGE");
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r6);
        ARRAY_STORAGE.save(r0);
        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r5);
        ARRAY_STORAGE.save(r2);
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : SORTED_ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
