package hook;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.thucydides.core.annotations.Steps;
import pages.LoginActions;
import pages.WorkspaceActions;

import java.awt.*;

import static constant.ConstantAccount.EMAIL1;
import static constant.ConstantAccount.PASSWORD1;

public class OrgChartHook {
    private static boolean beforeFeature = false;
    @Steps
    LoginActions loginActions = new LoginActions();
    WorkspaceActions workspaceActions = new WorkspaceActions();

    @Before("@orgChart")
    public void checkLoggedIn(Scenario scenario) throws InterruptedException, AWTException {
        if (!beforeFeature) {
            loginActions.checkLogin(EMAIL1,PASSWORD1);
            workspaceActions.getWpID();
            beforeFeature = true;
        }
    }
}
