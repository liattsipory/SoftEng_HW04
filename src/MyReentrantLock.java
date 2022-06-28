import java.util.concurrent.atomic.AtomicBoolean;

public class MyReentrantLock implements Lock {
    private AtomicBoolean isLocked = new AtomicBoolean(false);
    private Thread lockedThread;
    private int counter = 0;

    public MyReentrantLock(){
        this.lockedThread = Thread.currentThread();
    }


    @Override
    public void acquire() {
        if (this.counter==0 || Thread.currentThread()!=this.lockedThread) {
            //while (this.isLocked.get() && Thread.currentThread()!=this.lockedThread) {
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

    @Override
    public boolean tryAcquire() {
        if (!isLocked.get() || (isLocked.get() && Thread.currentThread()==this.lockedThread)) {
            acquire();
            return true;
        } else return false;
    }

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

    @Override
    public void close() {
        this.release();
    }
}
