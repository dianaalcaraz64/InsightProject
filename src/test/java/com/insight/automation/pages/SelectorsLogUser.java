package com.insight.automation.pages;

// LIBRERIA SELENIUM
import org.openqa.selenium.WebDriver;             // CLASE PARA MANUPULAR EL NAVEGADOR
import org.openqa.selenium.WebElement;            // CLASE PARA INTERACTUAR CON LOS ELEMENTOS DE LA PÁGINA
import org.openqa.selenium.support.FindBy;        // CLASE PARA LOCALIZAR ELEMENTOS.
import org.openqa.selenium.support.PageFactory;   // CLASE PARA INICIALIZAR LOS ELEMENTOS(FindBy) Y CONECTARLOS AL WEBDRIVER.
import org.openqa.selenium.support.ui.WebDriverWait; // ESPERA(MECANISMO o MOTOR DE ESPERA).
import org.openqa.selenium.support.ui.ExpectedConditions; // ESPERA(DEFINICIÓN DE LA CONDICIÓN. EL QUE DEFINE "QUE" ESPERAR").
import java.time.Duration;      // TIEMPO DE ESPERA.

public class SelectorsLogUser {
	
	WebDriver driver;                       // DECLARO UNA VARIABLE DEL TIPO WEBDRIVER
	private WebDriverWait wait;
	
public SelectorsLogUser(WebDriver driver) { // CONSTRUCTOR
	this.driver = driver;
	PageFactory.initElements(driver, this);// INICIALIZA LOS ELEMENTOS CON @FINDBY(NECESARIO PARA QUE FUNCIONEN).
	this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));  // INDICA QUE SELENIUM ESPERE HASTA 28 SEG EN QUE SE CUMPLA LA CONDICIÓN(EJ:VISIBILIDAD DE CAMPOS). 
}
	
@FindBy(id="email")                          // ELEMENTOS(SELECTORES)
WebElement emailField;
@FindBy(id="password")
WebElement passwordField;
@FindBy(css="img[alt='EyeIcon']")
WebElement eyeButton;
@FindBy(css="button[type='submit']")
WebElement loginButton;
	
public void login(String email, String password) {  // METODO PARA COMPLETAR LOS CAMPOS E INICIAR SESIÓN.
        
        wait.until(ExpectedConditions.visibilityOf(emailField)).sendKeys(email);  // INDICO A SELENIUM QUE ESPERA HASTA QUE APARESCAN LOS CAMPOS PARA LUEGO COMPLETARLOS.
        wait.until(ExpectedConditions.visibilityOf(passwordField)).sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(eyeButton)).click();
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
}


}