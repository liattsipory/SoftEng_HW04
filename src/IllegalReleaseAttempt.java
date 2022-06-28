public class IllegalReleaseAttempt extends IllegalMonitorStateException {
    /****
     * this is an exception that is thrown when an attempt to release the
     * reentrant lock is made but the lock is already unlocked or the calling thread
     * is not the thread that owns the lock.
     */
    public IllegalReleaseAttempt(){
        super();
    }

    public IllegalReleaseAttempt(String message) {
        super(message);
    }
}
