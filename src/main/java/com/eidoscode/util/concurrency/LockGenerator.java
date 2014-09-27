package com.eidoscode.util.concurrency;

import java.util.concurrent.ConcurrentHashMap;

/**
 * The purpose of this class is to generate a lock object so it can be used on
 * the synchronization of threads. This generates a unique sync object according
 * the provided unique key.<br/>
 * This is a nice way to control a bunch of services that impact each other if
 * they are using the same record or peace of data, so those services, if are
 * processing other information they will run simultaneously.<br/>
 * <br/>
 * <b>Example:</b><br/>
 * Let's say that you have a data called A and B. And you have two different
 * services. One import the data and the other export the data. Those services
 * are independent but you have to make sure that you are not reading the object
 * while someone is writing on the same object. At the same time, if those
 * services read and write different information they can run in parallel. <br/>
 * <b>Scenario 1:</b><br/>
 * Service A and B needs the Object X.<br/>
 * <li>Service A: Generates a lock using the LockGenerator class. <li>Service B:
 * Generates a lock using the LockGenerator class. <li>Service A: Synchronized
 * using the generated object and receive the lock so it can run. <li>Service B:
 * Synchronize using the generated object (same as the above) but it don't
 * receive the lock, so it wait for the lock acquisition. <li>Service A: Do a
 * lot of stuff on the Object X. <li>Service B: Waiting for the lock. <li>
 * Service A: Release the lock object. <li>Service B: Receive the lock status so
 * it can start working. <li>Service A: Start doing other stuff. <li>Service B:
 * Release the lock object. <br/>
 * Result: As you can see, as both Services want to use same Object, the lock
 * each other. <br/>
 * <br/>
 * <br/>
 * <b>Scenario 2:</b><br/>
 * Service A needs te Object X and B needs the Object Y.<br/> <li>Service A:
 * Generates a lock using the LockGenerator class. <li>Service B: Generates a
 * lock using the LockGenerator class. <li>Service A: Synchronized using the
 * generated object and receive the lock so it can run. <li>Service B:
 * Synchronized using the generated object and receive the lock so it can run.
 * <li>Service A: Do a lot of stuff on the Object X. <li>Service B: Do a lot of
 * stuff on the Object Y. <li>Service A: Release the lock object. <li>
 * Service B: Release the lock object. <li>Service A: Start doing other stuff.
 * <li>Service B: Start doing other stuff.<br/>
 * <br/>
 *
 * @author antonini
 * @since 1.1.0
 * @version 1.0
 */
public final class LockGenerator {

    private static final ConcurrentHashMap<String, LockObject> LOCKS = new ConcurrentHashMap<String, LockObject>();

    private static final Object LOCK_OBJECT = new Object();

    private LockGenerator() {
    }

    /**
     * Generates or retrieve a lock object according a given key.
     *
     * @param key
     *            Key of the generated lock object.
     * @return Lock Object.
     */
    public static Object lockObject(final String key) {
        LockObject lock = LOCKS.get(key);
        if (lock == null) {
            synchronized (LOCK_OBJECT) {
                lock = LOCKS.putIfAbsent(key, new LockObject());
            }
        }
        lock.count++;
        return lock;
    }

    /**
     * Release the Lock object. The main reason of this method is to ensure that
     * the JVM memory will be cleaned after all the threads use the lock object
     * avoiding a memory leak.
     *
     * @param key
     *            Key of the generated lock object.
     */
    public static void releaseLock(final String key) {
        LockObject lock = LOCKS.get(key);
        if (lock != null) {
            lock.count--;
            if (lock.count <= 0) {
                synchronized (lock) {
                    if (lock.count <= 0) {
                        LOCKS.remove(key);
                    }
                }
            }
        }
    }
}

/**
 * Lock Object.
 *
 * @author antonini
 * @since 1.1.0
 * @version 1.0
 */
class LockObject {

    protected int count = 0;

}