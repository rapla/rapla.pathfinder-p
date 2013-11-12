package java.util.concurrent.locks;
import java.util.concurrent.locks.Lock;

public class ReentrantReadWriteLock implements ReadWriteLock {
    Lock lock = new Lock();
	
	public Lock readLock()
    {
    	return lock;
    }
    public Lock writeLock()
    {
    	return lock;
    }
}