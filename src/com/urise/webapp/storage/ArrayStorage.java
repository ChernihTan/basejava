package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int RESUMES_LIMIT = 10000;
    private final Resume[] storage = new Resume[RESUMES_LIMIT];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        int position = findIndex(r.getUuid());
        if (position >= 0) {
            storage[position] = r;
        } else {
            throw new RuntimeException("ERROR - Cannot  get, identifier uuid = \"" + r.getUuid() +
                    "\" not found");
        }
    }

    public void save(Resume r) {
        int position = findIndex(r.getUuid());
        if (!isExisting(position)) {
            if (size >= RESUMES_LIMIT) {
                throw new RuntimeException("Количество резюме больше " + RESUMES_LIMIT + "\n");
            }
            storage[size++] = r;
        } else {
            throw new RuntimeException("Resume with identifier uuid = \"" + r.getUuid() +
                    "\" already saved");
        }
    }

    public Resume get(String uuid) {
        int position = findIndex(uuid);
        if (position >= 0) {
            return storage[position];
        } else {
            throw new RuntimeException("ERROR - Cannot  get, identifier uuid = \"" + uuid +
                    "\" not found");
        }
    }

    public void delete(String uuid) {
        int position = findIndex(uuid);
        if (position >= 0) {
            storage[position] = storage[--size];
            storage[size] = null;
        } else {
            throw new RuntimeException("ERROR - Impossible to delete, identifier uuid = \"" + uuid +
                    "\" not found");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    /**
     * @return May return -1 if the specified identifier does not exist in the array objects
     */
    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    private boolean isExisting(int index) {
        return index >= 0;
    }
}