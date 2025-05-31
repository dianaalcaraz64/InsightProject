package com.insight.automation.utils;

import com.insight.automation.data.Patient;
import com.github.javafaker.Faker;          // SE UTILIZA ESTA LIBRERÍA PARA GENERAR DATOS ALEATORIAMENTE.

import java.text.Normalizer;
//import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.lang3.RandomStringUtils;


public class TestDataGeneratorRegisterPat {

	private static Faker faker = new Faker(new Locale("es", "AR")); 

    public static Patient generateRandomPatient() {
      
    	
        String[] sexTypes = {"Masculino", "Femenino", "Transgenero", "NoBinario", "Bigenero", "Intersexual", "Otros"};
        String[] modalityTypes = {"Presencial", "Virtual", "Hibrido"};
        String[] identificationTypesOptions = {"DNI", "CURP", "Pasaporte", "Cedula de Ciudadania"}; 
        String[] sessionDaysOptions = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"};
        String[] sessionFrequenciesOptions = {"Semanal", "Quincenal", "Mensual", "Bimestral"};
        String[] preferredContactsOptions = {"Email", "Teléfono", "WhatsApp", "SMS"};
        Integer[] sessionDurationOptions = {30, 45, 50, 60}; 

       
        boolean shouldFillPrincipalMotive = faker.bool().bool();
        boolean shouldFillActualSymptoms = faker.bool().bool();
        boolean shouldFillRecentEvents = faker.bool().bool(); 
        boolean shouldFillPreviousDiagnosis = faker.bool().bool(); 
        boolean shouldFillProfesionalObservations = faker.bool().bool(); 
        boolean shouldFillKeyWords = faker.bool().bool(); 
        boolean shouldFillFailedActs = faker.bool().bool(); 
        boolean shouldFillInterconsultation = faker.bool().bool();
        boolean shouldFillPatientEvolution = faker.bool().bool(); 
        boolean shouldFillSessionDay = faker.bool().bool(); 
        boolean shouldFillSessionDuration = faker.bool().bool(); 
        boolean shouldFillSessionFrequency = faker.bool().bool(); 
        boolean shouldFillPreferedContact = faker.bool().bool(); 
        
        // GENERACIÓN DE DATOS

        Date birthDateAsUtilDate = faker.date().birthday(1, 120); 
        LocalDateTime birthDateTime = birthDateAsUtilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime(); 
       
        String formattedBirthDate = birthDateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")); // EL FRONTEND HOY RECIBE LA FECHA CON ESTE FORMATO.
        
        
        // NAME: STRING, MAXLENGTH: 50, MINLENGTH: 2, PATTERN: ^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$
        String name = faker.name().firstName();
        if (name.length() < 2) name = RandomStringUtils.randomAlphabetic(2);
        if (name.length() > 50) name = name.substring(0, 50);

        // SURNAME: STRING, MAXLENGTH: 50, MINLENGTH: 2, PATTERN: ^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$
        String surname = faker.name().lastName();
        if (surname.length() < 2) surname = RandomStringUtils.randomAlphabetic(2);
        if (surname.length() > 50) surname = surname.substring(0, 50);

        // NATIONALITY: STRING, MAXLENGTH: 30, MINLENGTH: 4, PATTERN: 
        String nationality = faker.address().country();
        nationality = Normalizer.normalize(nationality, Normalizer.Form.NFD);             // AQUI SE ELIMINAN ACENTOS Y GUIONES.TODO LO QUE NO SEAN LETRAS, SE MANTIENEN LOS ESPACIOS
        nationality = nationality.replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
        nationality = nationality.replaceAll("[-']", "");
        nationality = nationality.replaceAll("\\s+", " ").trim(); // SE ELIMINAN LOS MULTIPLES ESPACIOS, AL INICIO/FINAL
        
        if (nationality.length() < 4) {
            nationality = RandomStringUtils.randomAlphabetic(4); 
        }
        if (nationality.length() > 30) {
            nationality = nationality.substring(0, 30);
        }

        // typeOfIdentification: STRING, MAXLENGTH: 50, MINLENGTH: 0, PATTERN: ^[a-zA-ZáéíóúÁÉÍÓÚñÑ\s]+$
        String selectedTypeOfIdentification = faker.options().option(identificationTypesOptions);
        if (selectedTypeOfIdentification.length() > 50) selectedTypeOfIdentification = selectedTypeOfIdentification.substring(0, 50);

        // identification: string, maxLength: 20, minLength: 9, pattern: ^[a-zA-Z0-9\-]+$
        String identification;                                        // A LOS FINES DE LA PRUEBA, SE VAN A MANEJAR ALEATORIAMENTE ESTAS OPCIONES DE IDENTICACIÓN.
        if ("CURP".equals(selectedTypeOfIdentification)) {
            identification = RandomStringUtils.randomAlphanumeric(18).toUpperCase();
        } else if ("DNI".equals(selectedTypeOfIdentification) || "Cédula de Ciudadanía".equals(selectedTypeOfIdentification)) {
            identification = faker.number().digits(faker.number().numberBetween(8, 10));
        } else { // PASAPORTE U OTROS GENÉRICOS
            identification = RandomStringUtils.randomAlphanumeric(faker.number().numberBetween(9, 20));
        }
        while (identification.length() < 9 || identification.length() > 20) {
            identification = RandomStringUtils.randomAlphanumeric(faker.number().numberBetween(9, 20));
        }

        // sex: SexType string, Enum: [Masculino, Femenino, Transgénero, No Binario, Bigénero, Intersexual, Otro]
        String sex = faker.options().option(sexTypes);

        // email: string($email), minLength: 1
        String email = faker.internet().emailAddress();

        String phone = faker.number().digits(faker.number().numberBetween(8, 12));


        // CONSTRUCCION DE OBJETO PACIENTE
        return Patient.builder()
            // CAMPOS OBLIGATORIOS
            .name(name)
            .surname(surname)
            .birthdate(formattedBirthDate)
            .nationality(nationality)
            .typeOfIdentification(selectedTypeOfIdentification)
            .identification(identification)
            .sex(sex)
            .email(email)
            .phone(phone) 

            // CAMPOS OPCIONALES 
            .principalMotive(shouldFillPrincipalMotive ? faker.lorem().sentence(5) : "")
            .actualSymptoms(shouldFillActualSymptoms ? faker.lorem().paragraph(2) : "")
            .recentEvents(shouldFillRecentEvents ? faker.lorem().paragraph(2) : "")
            .previousDiagnosis(shouldFillPreviousDiagnosis ? faker.lorem().paragraph(2) : "")
            .profesionalObservations(shouldFillProfesionalObservations ? faker.lorem().paragraph(2) : "")
            .keyWords(shouldFillKeyWords ? faker.lorem().words(3).toString().replace("[", "").replace("]", "") : "")
            .failedActs(shouldFillFailedActs ? faker.lorem().sentence() : "")
            .interconsultation(shouldFillInterconsultation ? faker.lorem().sentence() : "")
            .patientEvolution(shouldFillPatientEvolution ? faker.lorem().paragraph(2) : "")
            .sessionDay(shouldFillSessionDay ? faker.options().option(sessionDaysOptions) : "")
            .modality(faker.options().option(modalityTypes))
            .sessionDuration(shouldFillSessionDuration ? faker.options().option(sessionDurationOptions) : null)
            .sessionFrequency(shouldFillSessionFrequency ? faker.options().option(sessionFrequenciesOptions) : "")
            .preferedContact(shouldFillPreferedContact ? faker.options().option(preferredContactsOptions) : "")
            
            .build();
    }
}
	
	
	
	

