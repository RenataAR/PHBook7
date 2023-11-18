package manage;

import model.User;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HelperUser extends HelperBase{
    public HelperUser(WebDriver wd) {
        super(wd);
    }

    public void openLoginForm() {
        click(By.xpath("//a[@href='/login']"));
    }

    public void fillLoginForm(User user) {
        type(By.xpath("//input[@name='email']"),user.getEmail());
        type(By.xpath("//input[@name='password']"),user.getPassword());
    }

    public void fillLoginRegistrationForm(String email, String password){
        type(By.xpath("//input[1]"), email);
        type(By.xpath("//input[2]"), password);
    }

    public void submitLoginForm() {
        click(By.xpath("//button[@type='submit']"));
    }

    public boolean isLogged() {
        return isElementPresent(By.xpath("//button[.='Sign Out']"));
    }

    public void logOut() {
        click(By.xpath("//button[.='Sign Out']"));
    }

    public boolean isAssertPresent() {
        Alert alert = new WebDriverWait(wd,10)
                .until(ExpectedConditions.alertIsPresent());
        if(alert==null) return false;
        else {
            wd.switchTo().alert();
            alert.accept();
        }
        return true;
    }

    public boolean isErrorFormatMessage() {
        Alert alert = new WebDriverWait(wd,10)
                .until(ExpectedConditions.alertIsPresent());
        return alert.getText().contains("Wrong email or password");
    }

    public void submitRegistrationForm() {
        click(By.xpath("//button[@name=\"registration\"]"));
    }
}
