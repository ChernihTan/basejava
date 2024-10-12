package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static com.urise.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;
import static org.junit.Assert.assertEquals;

public abstract class AbstractArrayStorageTest {
    private final Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    public AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertSize(0);
        Resume[] arrayResume = new Resume[STORAGE_LIMIT];
        // storage.getAll() возвращает не весь массив, а его подмножество в диапзоне: [0; число заполненных резюме]
        // соответственно у эталона беру так же
        int sizeArrayResume = 0;
        Assert.assertArrayEquals(Arrays.copyOfRange(arrayResume,0, sizeArrayResume),storage.getAll());
    }

    @Test
    public void update() {
        storage.update(RESUME_1);
        Assert.assertSame(RESUME_1,storage.get(UUID_1));
    }

    @Test
    public void save() {
        Resume resumeNew = new Resume();
        storage.save(resumeNew);
        assertSize(4);
        assertEquals(resumeNew, storage.get(resumeNew.getUuid()));
    }

    @Test
    public void get() {
        assertGet(RESUME_1);
        assertGet(RESUME_2);
        assertGet(RESUME_3);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertSize(2);
        storage.update(RESUME_1);
    }

    @Test
    public void getAll() {
        Resume[] expected = new Resume[3];
        expected[0] = RESUME_1;
        expected[1] = RESUME_2;
        expected[2] = RESUME_3;
        Resume[] actual = storage.getAll();
        Assert.assertArrayEquals(expected, actual);
    }

    @Test
    public void size() {
        assertSize(3);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete("dummy");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        Resume resumeUpdate = new Resume("dummy");
        storage.update(resumeUpdate);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(RESUME_1);
    }
    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        try {
            // for (int i = 4; i < STORAGE_LIMIT; i++) { // error
            for (int i = 3; i < STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            Assert.fail("Переполнение произошло раньше времени.");
        }
        // Overflow
        storage.save(new Resume());
    }
    public void assertSize(int size) {
        assertEquals(size, storage.size());
    }
    public void assertGet(Resume resume) {
        assertEquals(resume, storage.get(resume.getUuid()));
    }
}