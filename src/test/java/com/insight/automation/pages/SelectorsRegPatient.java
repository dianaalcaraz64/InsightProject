package com.insight.automation.pages;

//LIBRERIA SELENIUM
import com.insight.automation.data.Patient;
import org.openqa.selenium.WebDriver;             // CLASE PARA MANUPULAR EL NAVEGADOR
import org.openqa.selenium.WebElement;            // CLASE PARA INTERACTUAR CON LOS ELEMENTOS DE LA PÁGINA
import org.openqa.selenium.support.FindBy;        // CLASE PARA LOCALIZAR ELEMENTOS.
import org.openqa.selenium.support.PageFactory;   // CLASE PARA INICIALIZAR LOS ELEMENTOS(FindBy) Y CONECTARLOS AL WEBDRIVER.
import org.openqa.selenium.support.ui.WebDriverWait; // ESPERA(MECANISMO o MOTOR DE ESPERA).
import org.openqa.selenium.support.ui.ExpectedConditions; // ESPERA(DEFINICIÓN DE LA CONDICIÓN. EL QUE DEFINE "QUE" ESPERAR").
//import org.openqa.selenium.support.ui.Select;
//import java.lang.Thread;
import java.time.Duration;      // TIEMPO DE ESPERA.
import org.openqa.selenium.By;

public class SelectorsRegPatient {
	
	WebDriver driver;
	private WebDriverWait wait;
	
	public SelectorsRegPatient(WebDriver driver) { // CONSTRUCTOR
		this.driver = driver;
		PageFactory.initElements(driver, this);   // INICIALIZA LOS ELEMENTOS CON @FINDBY(NECESARIO PARA QUE FUNCIONEN).
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(nameField));  // VA A ESPERAR HASTA 10 SEG.QUE SE CUMPLA LA CONDICIÓN(QUE SE VESUALICE EL CAMPO NOMBRE)
	}	
	
//CAMPOS OBLIGATÓRIOS PARA REGISTRO DE PACIENTE

@FindBy(id="name")
WebElement nameField;
@FindBy(id="surname")
WebElement surnameField;
@FindBy(id="birthdate")
WebElement birthdateField;
@FindBy(id="nationality")
WebElement nationalityField;
@FindBy(id="typeOfIdentification")
WebElement typeOfIdentificationField;
@FindBy(id="identification")
WebElement numIdField;
/*@FindBy(css="input[value='Femenino']")
WebElement sexFemRadioButton;
@FindBy(css="input[value='Masculino']")
WebElement sexMaleRadioButton;
@FindBy(css="input[value='Transgénero']")
WebElement sexTransRadioButton;
@FindBy(css="input[value='No binario']")
WebElement sexNonbinRadioButton;
@FindBy(css="input[value='Bigénero']")
WebElement sexBigenderRadioButton;
@FindBy(css="input[value='Intersexual']")
WebElement sexIntersexRadioButton;
@FindBy(css="input[value='Otro']")
WebElement sexOtherRadioButton;*/
@FindBy(id="email")
WebElement emailField;
@FindBy(id="phone")
WebElement phoneField;
//CAMPOS OPCIONALES PARA REGISTRO DE PACIENTE
@FindBy(css="textarea[name='principalMotive']")
WebElement reasonField;
@FindBy(css="textarea[name='actualSymptoms']")
WebElement symptomsField;
@FindBy(css="textarea[name='recentEvents']")
WebElement eventsField;
@FindBy(css="textarea[name='previousDiagnosis']")
WebElement diagnosisField;
@FindBy(css="textarea[name='profesionalObservations']")
WebElement observationsField;
@FindBy(css="textarea[name='keyWords']")
WebElement wordsField;
@FindBy(css="textarea[name='failedActs']")
WebElement failedActsField;
@FindBy(css="textarea[name='interconsulation']")
WebElement interconsultationsField;
@FindBy(css="textarea[name='patientEvolution']")
WebElement evolutionField;
@FindBy(css="input[name='sessionDay']")
WebElement sessionDateField;
/*@FindBy(css="input[value='Presencial']")
WebElement faceTofaceModeRadioButton;
@FindBy(css="input[value='Virtual']")
WebElement virtualModeRadioButton;
@FindBy(css="input[value='Híbrido']")
WebElement hybridModeRadioButton;*/
/*@FindBy(css="input[value='30']")
WebElement thirtyMinSessionRadioButton;
@FindBy(css="input[value='45']")
WebElement fortyFiveMinSessionRadioButton;
@FindBy(css="input[value='50']")
WebElement fiftyMinSessionRadioButton;
@FindBy(css="input[value='60']")
WebElement sistyMinSessionRadioButton; */
@FindBy(css="input[name='sessionFrequency']")
WebElement frequencyField;
@FindBy(css="input[name='preferedContact']")
WebElement contactField;
@FindBy(css="button[type='submit']")
WebElement registerButton;


//MÉTODOS HAY QUE EDITAR LOS SELECTORES

public void registerPatient(Patient patient) throws InterruptedException  {  // METODO PARA COMPLETAR LOS CAMPOS E INICIAR SESIÓN.
	 long shortPause = 500;
	
	 nameField.sendKeys(patient.getName());// AQUÍ, ESPERA A QUE ESTÉ VISIBLE EL CAMPO Y COMPLETA EL ATRIBUTO NOMBRE DEL OBJETO(S)PACIENTE.
	 Thread.sleep(shortPause);
	 surnameField.sendKeys(patient.getSurname());
	 Thread.sleep(shortPause);
	 birthdateField.sendKeys(patient.getBirthdate().split("T")[0]);// CON [T][0] SE SEPARA LA FECHA Y HORA.
	 Thread.sleep(shortPause);
	 nationalityField.sendKeys(patient.getNationality());
	 Thread.sleep(shortPause);
	 typeOfIdentificationField.sendKeys(patient.getTypeOfIdentification());
	 Thread.sleep(shortPause);
	 numIdField.sendKeys(patient.getIdentification());
	 Thread.sleep(shortPause);
     selectSexRadio(patient.getSex());  // ESTE EL EL MÉTODO PARA SELECCIONAR UN TIPO DE SEXO. ESTÁ LLAMANDO AL SEXO.
     Thread.sleep(shortPause);
     emailField.sendKeys(patient.getEmail());
     Thread.sleep(shortPause);
     phoneField.sendKeys(patient.getPhone());
     Thread.sleep(shortPause);
     
     //AQUÍ COMIENZAN LOS DATOS OPCIONALES.

     if (patient.getPrincipalMotive() != null && !patient.getPrincipalMotive().isEmpty()) {   // AQUÍ DECIMOS QUE SI EL EN EL ATRIBUTO NO ES NULL O NO ESTA VACÍO, 
    	 reasonField.sendKeys(patient.getPrincipalMotive());                           //QUE EJECUTE EL MÉTODO(PERO SI LLEGA A SER NULL O ESTAR VACÍO, EL METODO NO SE EJECUTA Y SALTA AL SIGUIENTE CAMPO).
    	 Thread.sleep(shortPause);
     }
     if (patient.getActualSymptoms() != null && !patient.getActualSymptoms().isEmpty()) {
    	 symptomsField.sendKeys(patient.getActualSymptoms());
    	 Thread.sleep(shortPause);
     }
     if (patient.getRecentEvents() != null && !patient.getRecentEvents().isEmpty()) {
    	 eventsField.sendKeys(patient.getRecentEvents());
    	 Thread.sleep(shortPause);
     }
     if (patient.getPreviousDiagnosis() != null && !patient.getPreviousDiagnosis().isEmpty()) {
    	 diagnosisField.sendKeys(patient.getPreviousDiagnosis());
    	 Thread.sleep(shortPause);
     }
     if (patient.getProfesionalObservations() != null && !patient.getProfesionalObservations().isEmpty()) {
    	 observationsField.sendKeys(patient.getProfesionalObservations());
    	 Thread.sleep(shortPause);
     }
     if (patient.getKeyWords() != null && !patient.getKeyWords().isEmpty()) {
    	 wordsField.sendKeys(patient.getKeyWords());
    	 Thread.sleep(shortPause);
     }
     if (patient.getFailedActs() != null && !patient.getFailedActs().isEmpty()) {
    	 failedActsField.sendKeys(patient.getFailedActs());
    	 Thread.sleep(shortPause);
     }
     if (patient.getInterconsultation() != null && !patient.getInterconsultation().isEmpty()) {
    	 interconsultationsField.sendKeys(patient.getInterconsultation());
    	 Thread.sleep(shortPause);
     }
     if (patient.getPatientEvolution() != null && !patient.getPatientEvolution().isEmpty()) {
    	 evolutionField.sendKeys(patient.getPatientEvolution());
    	 Thread.sleep(shortPause);
     }
     
     if (patient.getSessionDay() != null && !patient.getSessionDay().isEmpty()) {
    	 sessionDateField.sendKeys(patient.getSessionDay());
    	 Thread.sleep(shortPause);
     }

     if (patient.getModality() != null && !patient.getModality().isEmpty()) {
    	    selectModalityRadio(patient.getModality());
    	    Thread.sleep(shortPause);
    	}

     if (patient.getSessionDuration() != null) {                          // SI EL CAMPO ES NULL, NO SE EJECUTA EL MÉTODO. COMO EL RETORNO DEBE SER INTERGER, NO SE TRABAJA CON VACÍOS.
         selectSessionDurationRadio(patient.getSessionDuration());
         Thread.sleep(shortPause);
     }
     
     if (patient.getSessionFrequency() != null && !patient.getSessionFrequency().isEmpty()) {    //SON LOS CAMPOS DE ENTRADA DE TEXTO, PERO ESOS VALORES ALEATÓRIOS SE DEFIENIERON EN UNA LISTA EN DATATEST.
         frequencyField.sendKeys(patient.getSessionFrequency());
         Thread.sleep(shortPause);
     }

     if (patient.getPreferedContact() != null && !patient.getPreferedContact().isEmpty()) {
         contactField.sendKeys(patient.getPreferedContact());
         Thread.sleep(shortPause);
     }

     wait.until(ExpectedConditions.elementToBeClickable(registerButton)).click();
	 
     
 }

                             
 private void selectSexRadio(String sexValue) {                                     //MÉTODOS PARA LOS RADIOBUTTONS, LOS VALORES SE VAN A BUSCAR AL GENERADOR DE DATOS.
     if (sexValue != null && !sexValue.isEmpty()) {
         WebElement radio = driver.findElement(By.cssSelector("input[value='" + sexValue + "']"));
         wait.until(ExpectedConditions.elementToBeClickable(radio)).click();
     }
 }

 private void selectModalityRadio(String modalityValue) {
     WebElement radio = driver.findElement(By.cssSelector("input[value='" + modalityValue + "']"));
     wait.until(ExpectedConditions.elementToBeClickable(radio)).click();
 }

 private void selectSessionDurationRadio(Integer durationValue) {
     if (durationValue != null) {
         WebElement radio = driver.findElement(By.cssSelector("input[value='" + durationValue + "']"));
         wait.until(ExpectedConditions.elementToBeClickable(radio)).click();
     }
 }
}









