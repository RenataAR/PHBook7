package tests;

import manage.ProviderData;
import model.User;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LoginTests extends TestBase{
    @BeforeMethod(alwaysRun = true)
    public void preCondition(){
        if(app.getUser().isLogged()){
            app.getUser().logOut();
        }
    }

    @Test(invocationCount = 3, groups = {"smoke"}, dataProvider = "loginModelDto",dataProviderClass = ProviderData.class)
    public void loginPositiveTest(User user){
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginForm();
        Assert.assertTrue(app.getUser().isLogged());
    }

    @Test
    public void loginPositiveTestConfig(){
        app.getUser().openLoginForm();
        app.getUser().fillLoginRegistrationForm(app.getEmail(),app.getPassword());
        app.getUser().submitLoginForm();
        Assert.assertTrue(app.getUser().isLogged());
    }

    @Test(groups = {"smoke","regress"})
    public void loginNegativeTestWrongEmail(){
        User user = User.builder()
                .email("galinagmail.com")
                .password("Gg123456$")
                .build();
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginForm();
        Assert.assertTrue(app.getUser().isErrorFormatMessage());
        Assert.assertTrue(app.getUser().isAssertPresent());
    }

    @Test(groups = {"regress"})
    public void loginNegativeTestWrongPassword(){
        User user = User.builder()
                .email("galina@gmail.com")
                .password("Gg123456")
                .build();
        app.getUser().openLoginForm();
        app.getUser().fillLoginForm(user);
        app.getUser().submitLoginForm();
        Assert.assertTrue(app.getUser().isErrorFormatMessage());
        Assert.assertTrue(app.getUser().isAssertPresent());
    }
}
