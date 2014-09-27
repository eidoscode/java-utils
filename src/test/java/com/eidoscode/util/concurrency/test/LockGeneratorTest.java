package com.eidoscode.util.concurrency.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import com.eidoscode.util.concurrency.LockGenerator;

public class LockGeneratorTest {

    @Test
    public void testGettingSimpleLock() {
        final String key = "key";
        Object lock = LockGenerator.lockObject(key);
        assertNotNull(lock);
        LockGenerator.releaseLock(key);
    }

    @Test
    public void testGettingObject() {
        final String key = "gettingLotOfTimeKey";

        final Object lock1 = LockGenerator.lockObject(key);
        final Object lock2 = LockGenerator.lockObject(key);
        final Object lock3 = LockGenerator.lockObject(key);

        assertEquals(lock1, lock2);
        assertEquals(lock2, lock3);
        assertEquals(lock1, lock3);

        LockGenerator.releaseLock(key);
        LockGenerator.releaseLock(key);
        LockGenerator.releaseLock(key);

        int amount = LockGenerator.getConcurrentInUse(key);
        assertEquals(amount, -1);
    }

    @Test
    public void testReleaseObjectNotRequested() {
        final String key = "releaseObjectNotRequested";
        LockGenerator.releaseLock(key);
    }

    @Test
    public void testGettingSameKeyLock() {
        final String key = "sameKey";
        final String key2 = "sameKey";
        Object lock = LockGenerator.lockObject(key);
        assertNotNull(lock);
        Object lock2 = LockGenerator.lockObject(key2);
        assertNotNull(lock2);
        assertEquals(lock, lock2);
        LockGenerator.releaseLock(key);
        LockGenerator.releaseLock(key);
    }

    @Test
    public void testAmountOfRequestsNegative() {
        final String key = "testAmountOfRequestsNegative";
        int amount = LockGenerator.getConcurrentInUse(key);
        assertEquals(amount, -1);
    }

    @Test
    public void testAmountOfRequestsPositive() {
        final String key = "testKeys";
        LockGenerator.lockObject(key);
        int amount = LockGenerator.getConcurrentInUse(key);
        assertEquals(amount, 1);
        LockGenerator.releaseLock(key);
    }
}
