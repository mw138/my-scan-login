import java.util.Random;

import static javafx.scene.input.KeyCode.R;

public class LoginThread extends Thread {

    private final ScanLoginAction loginAction;
    private final ScanLoginAuthorizeAction scanLoginAuthorizeAction;


    public LoginThread(ScanLoginAction loginAction, ScanLoginAuthorizeAction scanLoginAuthorizeAction) {
        this.scanLoginAuthorizeAction = scanLoginAuthorizeAction;
        this.loginAction = loginAction;
    }


    @Override
    public void run() {
        try {
            Thread.sleep(new Random().nextInt(250));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String diagramId = loginAction.showDiagram();
        AuthorizeThread authorizeThread = new AuthorizeThread(scanLoginAuthorizeAction, diagramId);
        authorizeThread.start();
        loginAction.login(diagramId);
    }

}
