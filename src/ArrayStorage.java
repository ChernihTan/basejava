import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int RESUMES_LIMIT = 3;  // 10000;
    Resume[] storage = new Resume[RESUMES_LIMIT];
    private int countResumes;

    void clear() {
        Arrays.fill(storage, 0, countResumes, null);
        countResumes = 0;
    }

    void save(Resume r) {
        if (countResumes >= RESUMES_LIMIT) {
            throw new RuntimeException("Количество резюме больше " + RESUMES_LIMIT + "\n");
        }
        storage[countResumes++] = r;
    }

    Resume get(String uuid) {
        // нужно найти индекс элемента в массиве, под которым храниться данный элемент
        int position = findIndex(uuid);
        if (position < 0 || position >= RESUMES_LIMIT) {
            throw new RuntimeException("Указанный идентификатор не найден!\n");
        }
        return storage[position];
    }

    void delete(String uuid) {
        int position = findIndex(uuid);
        if (position < 0 || position >= RESUMES_LIMIT) {
            throw new RuntimeException("Указанный для удаления идентификатор не найден!\n");
        }
        // последнее резюме помещается вместо удаляемого,
        // если это не последнее заполненное резюме
        if (position != --countResumes) {
            storage[position] = storage[countResumes];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        return Arrays.copyOf(storage, countResumes);
    }

    int size() {
        return countResumes;
    }

    private int findIndex(String uuid) {
        // поиск индекса элемента в массиве, под которым храниться резюме
        // с идентификатором uuid
        for (int i = 0; i < countResumes; i++) {
            if (storage[i].uuid.equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
