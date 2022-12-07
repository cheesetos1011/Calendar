package hook;

import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import net.thucydides.core.annotations.Steps;
import pages.LoginActions;

import java.awt.*;

import static constant.ConstantAccount.EMAIL1;
import static constant.ConstantAccount.PASSWORD1;

public class CalendarHook {
    private static boolean beforeFeature = false;
    @Steps
    LoginActions loginActions = new LoginActions();

    @Before("@calendar")
    public void checkLoggedIn(Scenario scenario) throws InterruptedException, AWTException {
        if (!beforeFeature) {
            loginActions.checkLogin(EMAIL1,PASSWORD1);
            beforeFeature = true;
        }
    }
}
