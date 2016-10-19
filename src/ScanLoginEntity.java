public class ScanLoginEntity {
    private String diagramId;

    private User user;

    private Thread LoginThread;

    private final Object lock = new Object();

    public ScanLoginEntity(String diagramId) {
        this.diagramId = diagramId;
    }

    public String getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(String diagramId) {
        this.diagramId = diagramId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Thread getLoginThread() {
        return LoginThread;
    }

    public void setLoginThread(Thread loginThread) {
        LoginThread = loginThread;
    }

    public void scanWait() {
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void scanNotify() {
        synchronized (lock) {
            lock.notify();
        }
    }


}
