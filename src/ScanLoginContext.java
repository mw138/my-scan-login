import java.util.concurrent.ConcurrentHashMap;

public class ScanLoginContext {

    private final ConcurrentHashMap<String, ScanLoginEntity> entityMap = new ConcurrentHashMap<String, ScanLoginEntity>();

    public ConcurrentHashMap<String, ScanLoginEntity> getEntityMap() {
        return entityMap;
    }
}
