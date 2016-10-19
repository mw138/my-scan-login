public class Main {
    public static void main(String[] args) {
        ScanLoginContext context = new ScanLoginContext();
        ScanLoginAction loginAction = new ScanLoginAction(context);
        ScanLoginAuthorizeAction authorizeAction = new ScanLoginAuthorizeAction(context);

        LoginThread loginThread1 = new LoginThread(loginAction, authorizeAction);
        LoginThread loginThread2 = new LoginThread(loginAction, authorizeAction);
        LoginThread loginThread3 = new LoginThread(loginAction, authorizeAction);
        LoginThread loginThread4 = new LoginThread(loginAction, authorizeAction);
        LoginThread loginThread5 = new LoginThread(loginAction, authorizeAction);
        LoginThread loginThread6 = new LoginThread(loginAction, authorizeAction);

        loginThread1.start();
        loginThread2.start();
        loginThread3.start();
        loginThread4.start();
        loginThread5.start();
        loginThread6.start();


    }
}
