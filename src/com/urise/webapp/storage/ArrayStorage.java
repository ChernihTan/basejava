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
        // if resume present
        if (r != null) {
            if (r.getUuid() != null) {
                int position = findIndex(r.getUuid());
                storage[position] = r;
            } else {
                // System.out.println("ERROR параметра при вызове метода update(..) ");
                throw new RuntimeException("ERROR при вызове метода update(..) - идентификтор резюме null");
            }
        } else {
            throw new RuntimeException("ERROR при вызове метода update(..) - объект резюме не создан (null)");
        }
    }

    public void save(Resume r) {
        if (r != null) {
            if (r.getUuid() != null) {
                // проверка, если существует
                if (isNewResume(r.getUuid())) {
                    if (size >= RESUMES_LIMIT) {
                        throw new RuntimeException("Количество резюме больше " + RESUMES_LIMIT + "\n");
                    }
                    storage[size++] = r;
                }
            } else {
                throw new RuntimeException("ERROR при вызове метода save(..) - идентификтор резюме null");
            }
        } else {
            throw new RuntimeException("ERROR при вызове метода update(..) - объект резюме не создан");
        }
    }

    public Resume get(String uuid) {
        if (uuid != null) {
            // нужно найти индекс элемента в массиве, под которым храниться данный элемент
            int position = findIndex(uuid);
            return storage[position];
        } else {
            throw new RuntimeException("ERROR параметра при вызове метода get(..) -  идентификатор резюме null ");
        }
    }

    public void delete(String uuid) {
        if (uuid != null) {
            int position = findIndex(uuid);
            storage[position] = storage[--size];
            storage[size] = null;
        } else {
            throw new RuntimeException("ERROR: удаление невозможно, т.к. индикатор резюме null! ");
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

    private int findIndex(String uuid) {
        // поиск индекса элемента в массиве, под которым храниться резюме
        // с идентификатором uuid
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        throw new RuntimeException("Резюме с указанным идентификатором uuid = \"" + uuid + "\" в базе не найдено!");
    }
    private boolean isNewResume(String uuid) {
        // поиск индекса элемента в массиве, под которым храниться резюме
        // с идентификатором uuid
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                throw new RuntimeException("Резюме с указанным идентификатором uuid = \"" + uuid + "\" уже есть в базе!");
            }
        }
        return true;
    }
}