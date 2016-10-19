import sun.dc.pr.PRError;
import sun.util.resources.cldr.es.CalendarData_es_UY;

import java.util.concurrent.ConcurrentHashMap;

public class ScanLoginAuthorizeAction {

    private final ScanLoginContext context;

    private static int userId = 0;

    public ScanLoginAuthorizeAction(ScanLoginContext context) {
        this.context = context;
    }

    public void associateDiagram(String diagramId) {
        waitBeforeLogin(diagramId);
        ConcurrentHashMap<String, ScanLoginEntity> entityMap = context.getEntityMap();
        ScanLoginEntity entity = entityMap.get(diagramId);
        User user = newUser();
        entity.setUser(user);
        System.out.println("图形ID【" + diagramId + "】已经关联用户，用户ID【" + user.getId() + "】");
    }

    public void authorizeLogin(String diagramId) {
        ConcurrentHashMap<String, ScanLoginEntity> entityMap = context.getEntityMap();
        ScanLoginEntity entity = entityMap.get(diagramId);
        User user = entity.getUser();
        System.out.println("图形ID【" + diagramId + "】已经授权登录，用户ID【" + user.getId() + "】");
        entity.scanNotify();
    }

    private void waitBeforeLogin(String diagramId) {
        ScanLoginEntity entity = context.getEntityMap().get(diagramId);
        while (guardCondition(entity)) {
            try {
                wait(50);
            } catch (InterruptedException | IllegalMonitorStateException e) {
            }
        }
    }

    private boolean guardCondition( ScanLoginEntity entity ) {
        if (entity.getLoginThread() == null) {
            return true;
        }
        Thread.State state = entity.getLoginThread().getState();
        return state == Thread.State.NEW || state == Thread.State.RUNNABLE;
    }


    private synchronized User newUser() {
        User user = new User();
        user.setId(userId++);
        return user;
    }
}
