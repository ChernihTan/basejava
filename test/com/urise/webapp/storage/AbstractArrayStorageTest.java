package com.urise.webapp.storage;

import com.webapp.exception.StorageException;
import com.webapp.model.Resume;
import com.webapp.storage.Storage;
import org.junit.Assert;
import org.junit.Test;

import static com.webapp.storage.AbstractArrayStorage.STORAGE_LIMIT;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {
    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }


    @Test(expected = StorageException.class)
    // @Ignore
    public void saveOverflow() {
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


}