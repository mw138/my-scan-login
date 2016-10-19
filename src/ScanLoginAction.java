import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class ScanLoginAction {

    private final ScanLoginContext context;


    public ScanLoginAction(ScanLoginContext context) {
        this.context = context;
    }

    public String showDiagram() {
        ConcurrentHashMap<String, ScanLoginEntity> entityMap = context.getEntityMap();
        String diagramId = generateDiagramId();
        entityMap.put(diagramId, new ScanLoginEntity(diagramId));
        System.out.println("收到登录请求，图形ID 【" + diagramId + "】");
        return diagramId;
    }

    public void login(String diagramId) {
        ConcurrentHashMap<String, ScanLoginEntity> entityMap = context.getEntityMap();
        ScanLoginEntity entity = entityMap.get(diagramId);
        entity.setLoginThread(Thread.currentThread());
        entity.scanWait();
        User user = entity.getUser();
        System.out.println("图形ID 【" + diagramId + "】 登录成功， 登录用户ID 【" + user.getId() + "】");
    }

    private String generateDiagramId() {
        return UUID.randomUUID().toString();
    }

}
