package com.insight.automation.tests;

import org.testng.annotations.Test;                 // 
import com.insight.automation.pages.SelectorsLogUser; // CLASE DEL PROYECTO(DONDE ESTAN LO SELECTORES Y MÉTODOS)
import com.insight.automation.utils.ConfigReaderLogin;     // CLASE DEL PROYECTO(SE UTILIZA PARA MANIPULAR EL JSON)
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeMethod;           //  ANOTACIONES DE TESTNG  
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.json.simple.JSONArray;                    //PARA INTERACTUAR CON JSON
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;               // CLASE PARA CONTROLAR EL NAVEGADOR.
import org.openqa.selenium.chrome.ChromeDriver;     
import java.lang.Thread;                           // CLASE QUE PERMITE HACER UNA PAUSA EN LA EJECUCIÓN DEL SCRIPT.

public class LoginUserTest {
	
	WebDriver driver;                              // DECLARAMOS LA VARIABLE DRIVER.
	SelectorsLogUser loginPage;
	private final String Url_Base = "https://insight-tywa.onrender.com/auth/login"; // DECLARAMOS LA URL DEL LOGIN COMO CONSTANTE GLOBAL.
	
@BeforeMethod
 public void openWroser() {
	WebDriverManager.chromedriver().setup();
	driver = new ChromeDriver();                       
	driver.get(Url_Base);
	driver.manage().window().maximize();
	loginPage = new SelectorsLogUser(driver);         //SE CREA UN OBJETO DEL TIPO SelectorsLogUser, PARA QUE EL NAVEGADOR (DRIVE) PUEDA ENCONTRAR LOS ELEMENTOS Y METODOS DE LA CLASE.
}

@DataProvider(name = "loginData")                     // SE UTILIZA ESTA ANOTACION PARA PASARLE MULTIPLES DATOS AL TEST(SE LE DA EL NOMBRE DE LOGINDATA, REGISTERDATA ETC).
public Object[][] loginData() {
                                                       
    JSONArray usuarios = ConfigReaderLogin.leerArray("com/insight/automation/data/users.json", "usuarios");  // SE LEE EL ARRAY "usuarios" DESDE EL ARCHIVO users.json
    Object[][] data = new Object[usuarios.size()][2];   // JAVA LEE EL JSON Y LO CONVIERTE EN UN ARREGLO BIDIRECCIONAL OBJETOS([2]PORQUE CADA OBJETO TIENE 2 ATRIBUTOS). ES LA FORMA QUE TIENE JAVA DE COMUNICARLE/PASARLE LOS DATOS A TESTNG.

    for (int i = 0; i < usuarios.size(); i++) {         // ACA SE RECORRE CADA OBJETO DENTRO DEL JSON Y SE VA GUARDANDO EN EL ARRAY(Object[][]).
        JSONObject user = (JSONObject) usuarios.get(i);
        data[i][0] = user.get("email");
        data[i][1] = user.get("password");
    }

    return data;   // RETORNA LOS OBJETOS DE A UNO(DE A UN TEST).
}
	


@Test(dataProvider = "loginData")    // EN EL TEST SE HACE REFERENCIA AL DATAPROVIDE(ARREGLO)
public void userLoguinSuccessful(String email, String password) { // LOS PARÁ METROS DE CADA OBJETO
	loginPage.login(email, password);        //TRAEMOS LOS ELEMENTOS Y USAMOS EL METODO DE LOGIN.
		
}

@AfterMethod
public void closeBrowser() {
    System.out.println("--- Navegador abierto 5 segundos para visualización ---");
    try {
        Thread.sleep(5000);                // ESTA LINEA INDICA QUE LA EJECUCIÓN DES SCRIPT SE DETENGA POR 5SEG(SE UTILIZA Thread.YA QUE SÓLO SE QUIERE PAUSAR EN LA HOME ANTES DE CERRAR, NO HAY NINGUNA CONDICIÓN ES ESTE PUNTO).
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();               
        System.err.println("La espera fue interrumpida.");
    }
    if (driver != null) {
        driver.quit();
    }
    System.out.println("--- Navegador cerrado ---");
}



/*@AfterMethod
public void closeBrowser() {
    driver.quit();   */             // CIERRA EL NAVEGADOR LUEGO DE EJEUTAR EL TEST.
	
	
	
	
	
	
}	


