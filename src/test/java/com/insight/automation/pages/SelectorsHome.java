package com.insight.automation.pages;

import org.openqa.selenium.WebDriver;             // CLASE PARA MANUPULAR EL NAVEGADOR
import org.openqa.selenium.WebElement;            // CLASE PARA INTERACTUAR CON LOS ELEMENTOS DE LA PÁGINA
import org.openqa.selenium.support.FindBy;        // CLASE PARA LOCALIZAR ELEMENTOS.
import org.openqa.selenium.support.PageFactory;   // CLASE PARA INICIALIZAR LOS ELEMENTOS(FindBy) Y CONECTARLOS AL WEBDRIVER.
import org.openqa.selenium.support.ui.WebDriverWait; // ESPERA(MECANISMO o MOTOR DE ESPERA).
import org.openqa.selenium.support.ui.ExpectedConditions; // ESPERA(DEFINICIÓN DE LA CONDICIÓN. EL QUE DEFINE "QUE" ESPERAR").
import java.time.Duration;      // TIEMPO DE ESPERA.


public class SelectorsHome {
	WebDriver driver;
	private WebDriverWait wait;
	
	
	public SelectorsHome(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);// INICIALIZA LOS ELEMENTOS CON @FINDBY(NECESARIO PARA QUE FUNCIONEN).
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));  // INDICA QUE SELENIUM ESPERE HASTA 10 SEG EN QUE SE CUMPLA LA CONDICIÓN(EJ:VISIBILIDAD DE CAMPOS). 
	}
	
//HOME PAGE	
@FindBy(css="span[class='text-3xl leading-10 font-semibold text-black lg:text-3xl lg:leading-[48px] lg:font-semibold']")
WebElement userNameText;
@FindBy(css="h1[class='mb-1.5 text-xl leading-7 font-semibold text-[#000F27E5] lg:mb-2 lg:text-2xl']")
WebElement newPatientButton;
//LOGOUT
@FindBy(css="img[alt='Flecha Baja']")
WebElement arrowButton;
@FindBy(css="div[class='mb-5 flex flex-row px-4 py-2'] button[class='cursor-pointer']")
WebElement logOutButton;


// MÉTODOS

public String getUserName() {
	wait.until(ExpectedConditions.visibilityOf(userNameText));
	wait.until(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(userNameText, "Cargando...")));
    return userNameText.getText();                // AQUÍ INDICO QUE GUARDE EL RETORNO(TEXTO HTML)PARA LUEGO COMPARARLO CON EL USERNAME.
}

public void navigateToRegistrForm() {
	wait.until(ExpectedConditions.visibilityOf(newPatientButton)).click();
	
}
	
public void logOut() throws InterruptedException {
	 long shortPause = 500;
	 wait.until(ExpectedConditions.elementToBeClickable(arrowButton)).click();  // "elementToBeClickable" ESPERA A QUE EL ELEMENTO SE PUEDA CLICKEAR.
	 Thread.sleep(shortPause);                // ESTA ESPERA ES PARA PERSIVIR LA ACCIÓN DE LOGOUT
     wait.until(ExpectedConditions.elementToBeClickable(logOutButton)).click();
     wait.until(ExpectedConditions.urlContains("/auth/login"));   // ESTA ES LA LÍNEA CLAVE PARA VALIDAR LA REDIRECCIÓN AL LOGIN
 }
	
	
}
