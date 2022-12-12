package hook;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.thucydides.core.annotations.Steps;
import pages.*;

import java.awt.*;

import static constant.ConstantAccount.EMAIL1;
import static constant.ConstantAccount.PASSWORD1;

public class CalendarHook {
    private static boolean beforeFeature = false;
    @Steps
    LoginActions loginActions = new LoginActions();
//    ChatActions chatActions = new ChatActions();
    WorkspaceActions workspaceActions = new WorkspaceActions();
    RoomActions roomActions = new RoomActions();
    OrgChartActions orgChartActions = new OrgChartActions();

    @Before("@calendar")
    public void checkLoggedIn(Scenario scenario) throws InterruptedException, AWTException {
        if (!beforeFeature) {
            loginActions.checkLogin(EMAIL1,PASSWORD1);
//            chatActions.getThread();
            workspaceActions.getWpID();
            roomActions.getRoomId();
            orgChartActions.getDepartment();
            orgChartActions.getRole();
            beforeFeature = true;
        }
    }
}
