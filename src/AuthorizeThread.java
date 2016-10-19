public class AuthorizeThread extends Thread {

    private String diagramId;

    private final ScanLoginAuthorizeAction authorizeAction;

    public AuthorizeThread(ScanLoginAuthorizeAction authorizeAction, String diagramId) {
        this.diagramId = diagramId;
        this.authorizeAction = authorizeAction;
    }

    @Override
    public void run() {
        authorizeAction.associateDiagram(diagramId);
        authorizeAction.authorizeLogin(diagramId);
    }



}
