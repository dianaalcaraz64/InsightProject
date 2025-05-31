package com.insight.automation.tests;

import org.testng.annotations.Test;
import com.insight.automation.pages.SelectorsLogUser;
import com.insight.automation.pages.SelectorsHome;         // Clase de la página Home
import com.insight.automation.pages.SelectorsRegPatient;   // Clase de la página de Registro de Pacientes
import com.insight.automation.utils.ConfigReaderLogin;
import com.insight.automation.utils.TestDataGeneratorRegisterPat; // Generador de datos de pacientes
import com.insight.automation.data.Patient;                 // Clase Patient
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.AfterMethod;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.lang.Thread; 
import org.testng.Assert; 
import org.openqa.selenium.support.ui.ExpectedConditions; 
import org.openqa.selenium.support.ui.WebDriverWait;     
import java.time.Duration; 


public class LoginAndRegisterPatient {
	
	WebDriver driver;
    SelectorsLogUser loginPage;
    SelectorsHome homePage;
    SelectorsRegPatient regPatientPage;
    private WebDriverWait wait;
    private final String Url_Base = "https://insight-tywa.onrender.com/auth/login";

    @BeforeMethod
    public void setup() { 
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get(Url_Base);
        driver.manage().window().maximize();
        loginPage = new SelectorsLogUser(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //SE ESPERA ESTA QUE LA URL CARGUE CORRECTAMENTE
    }

    @DataProvider(name = "loginData")    
    public Object[][] loginData() {
        JSONArray usuarios = ConfigReaderLogin.leerArray("com/insight/automation/data/users.json", "usuarios");
        Object[][] data = new Object[usuarios.size()][3]; // [3] POR LAS COLUMNAS QUE SE MANEJAN DEL USUARIO(EMAIL, CONTRASEÑA Y NOMBRE DE USUARIO ESPERADO)
        for (int i = 0; i < usuarios.size(); i++) {
            JSONObject user = (JSONObject) usuarios.get(i);
            data[i][0] = user.get("email");
            data[i][1] = user.get("password");
            data[i][2] = user.get("expectedUserName"); 
        }
        return data;
    }

    @Test(dataProvider = "loginData")
    public void userLoginAndRegisterPatientTest(String email, String password, String expectedUserName) throws InterruptedException{ 
        System.out.println("--- Ejecutando test con usuario: " + email + " ---");

        // 1) LOGIN
        loginPage.login(email, password);
        System.out.println("Login exitoso para: " + email);

        // 2) VERIFICACIÓN DE LOGIN Y OBTENCIÓN DEL NOMBRE DE USUARIO LOGUEADO
        homePage = new SelectorsHome(driver); 
        String userNameDisplayed = homePage.getUserName();
        System.out.println("Nombre de usuario mostrado en Home: '" + userNameDisplayed + "'");
        
        // ASERCIÓN PARA VERIFICAR QUE EL NOMBRE DE USUARIO COINCIDE.ESTO DETENDRÁ LA EJECUCIÓN DEL TEST SI LOS NOMBRES NO COINCIDEN, MARCANDO EL TEST COMO FALLIDO.
        Assert.assertEquals(userNameDisplayed.toLowerCase(), expectedUserName.toLowerCase(),"El nombre de usuario mostrado en la página Home no coincide con el esperado (ignorando mayúsculas/minúsculas).");
        System.out.println("Verificación de nombre de usuario exitosa: El nombre mostrado coincide con el esperado.");

        // 3) IR AL FORMULARIO DE REGISTRO DE PACIENTE.
        homePage.navigateToRegistrForm();
        System.out.println("Navegado a la página de registro de paciente.");

        // 4) PREPARANDO LA "INTERFAZ" PARA LA PÁGINA DE REGISTRO.
        regPatientPage = new SelectorsRegPatient(driver); // SE INICIALIZA DESPUES DE NAVEGAR ALA PÁGINA DE REGISTRO.

        // 5) GENERACIÓN DE DATOS ALEATORIOS
        Patient randomPatient = TestDataGeneratorRegisterPat.generateRandomPatient();
        System.out.println("Datos de paciente generados: " + randomPatient.getName() + " " + randomPatient.getSurname());

        // 6) REGISTRAR PACIENTE
        regPatientPage.registerPatient(randomPatient);
        // LUEGO DE HCER CLICK EN "REGISTRAR" SE DEBE REDIRECCIONAR A LA PÁGINA DE "LISTADO DE PACIENTES"
        String expectedPatientListPageUrl = "https://insight-tywa.onrender.com/dashboard/patientlist"; 
       // SE ESPERA HASTA 20 SEG. ESA REDIRECCIÓN
        wait.until(ExpectedConditions.urlContains(expectedPatientListPageUrl));
             
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedPatientListPageUrl),"**FALLO:** No se redirigió a la página de listado de pacientes. URL actual: " + driver.getCurrentUrl());
        System.out.println("Verificación de redirección exitosa: Se navegó a la URL: " + driver.getCurrentUrl());
        
       // 7) LOGOUT
        homePage.logOut();
        System.out.println("Cierre de sesión realizado correctamente.");
        // ASERCIÓN QUE VALIDA SI SE REDIRECCIONÓ A LA PÁGINA DE LOGIN
        Assert.assertTrue(driver.getCurrentUrl().contains("/auth/login"),"FALLO: Después del logout, no se redirigió a la página de login.");
       }                                           // FIN DEL @TEST.
    

    @AfterMethod
    public void tearDown() { 
        System.out.println("--- Navegador abierto 2 segundos para visualización ---");
        try {
            Thread.sleep(2000); // PAUSA PARA VISIALIZAR EL ESTADO FINAL
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // MANEJO DE INTERRUPCIÓN
            System.err.println("La espera fue interrumpida.");
        }
        if (driver != null) {
            driver.quit(); // CIERRE DEL NAVEGADOR
        }
        System.out.println("--- Navegador cerrado ---");
    }
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	


