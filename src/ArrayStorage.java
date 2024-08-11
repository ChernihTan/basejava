import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private static final int MAX_COUNT_RESUME = 10000;
    Resume[] storage = new Resume[MAX_COUNT_RESUME];
    private int countResume;
    void clear() {
        Arrays.fill(storage, 0, countResume, null);
        countResume = 0;
    }

    void save(Resume r) {
        if (countResume == MAX_COUNT_RESUME) {
            throw new RuntimeException("Количесто резюме больше " + MAX_COUNT_RESUME + "\n");
        }
        storage[countResume++] = r;
    }

    Resume get(String uuid) {
        // нужно найти индекс элемента в массиве, под которым храниться данный элемент
        int position  = findIndex(uuid);
        if (!(position >= 0 && position < MAX_COUNT_RESUME)) {
            throw new RuntimeException("Указанный идентификатор не найден!\n");
        }
        return storage[position];
    }

    void delete(String uuid) {
        int position  = findIndex(uuid);
        if (!(position >= 0 && position < MAX_COUNT_RESUME)) {
            throw new RuntimeException("Указанный для удаления идентификатор не найден!\n");
        }
        System.arraycopy(storage, position + 1, storage,
                position, countResume - position - 1);

        countResume--;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        // return new Resume[0];
        return Arrays.copyOf(storage, countResume);
    }

    int size() {
        return countResume;
    }

    int findIndex(String uuid) {
        // поиск индекса элемента в массиве, под которым храниться резюме
        // с идентификатором uuid
        int position = -1;
        for(int i = 0; i < countResume; i++) {
            if (storage[i].uuid == uuid) {
                position = i;
                break;
            }
        }
        return position;
    }
}
