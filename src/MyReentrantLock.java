import java.util.concurrent.atomic.AtomicBoolean;
public class MyReentrantLock implements Lock {
    /**
     * This class represents a lock which can be entrant by the same thread multiple times.
     * Has 3 features:
     * isLocked - AtomicBoolean type, representes whether the lock is locked.
     * lockedThread - Thread type, the owner thread.
     * counter - int type, amount of locks acquired by the same thread.
     */
    private AtomicBoolean isLocked = new AtomicBoolean(false);
    private Thread lockedThread;
    private int counter = 0;

    public MyReentrantLock(){
        /**
         * This constructs a new instance of MyReentrantLock.
         * Puts the currently executing thread in lockedThread feature.
         */
        this.lockedThread = Thread.currentThread();
    }


    @Override
    public void acquire() {
        /**
         * Locks this lock if possible. if lock is already locked, we wait until unlocked to lock it again.
         */
        if (this.counter==0 || Thread.currentThread()!=this.lockedThread) {
            while (this.isLocked.compareAndSet(false, true)
                    && Thread.currentThread() != this.lockedThread) {
                try {
                    Thread.sleep(11);
                } catch (InterruptedException e) {
                }
            }
            this.lockedThread = Thread.currentThread();
        }
        this.counter++;
    }

    /**
     * If locking is possible, calls acquire.
     * @return true if successful, false otherwise.
     */
    @Override
    public boolean tryAcquire() {
        if (!isLocked.get() || (isLocked.get() && Thread.currentThread()==this.lockedThread)) {
            acquire();
            return true;
        } else return false;
    }

    /**
     * if possible to release the lock, decreases the counter (releases if counter hits 0).
     * @throws IllegalReleaseAttempt - if the release is impossible
     */
    @Override
    public void release() {
        if (!isLocked.get() || Thread.currentThread()!=this.lockedThread) {
            throw new IllegalReleaseAttempt();
        } else {
            if (this.counter==1) {
                this.counter--;
                this.isLocked.set(false);
            } if (this.counter > 1) this.counter--;
        }
    }
    /**
     * calls the release function.
     */
    @Override
    public void close() {
        this.release();
    }
}
