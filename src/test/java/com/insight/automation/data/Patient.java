package com.insight.automation.data;

import lombok.Builder; 
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

// TENIENDO EN CUENTA QUE LA CANTIDAD DE ARGUMENTOS DE ESTA CLASE ES MUY GRANDE, NO ES UNA OPCIÓN EL MODELADO TRADICIONAL.
// PARA ESO. SE UTILIZA EL PATRÓN BUILDER. ESTE TIENE MÉTODOS PARA ASIGNARLE ATRIBUTOS A CADA PACIENTE Y UN build() FINAL QUE RETORNA EL OBJETO COMPLETAMENTE CONSTRUIDO.
// SE INSTALÓ EL PLUGIN DE LOMBOK PARA QUE EL IDE (ECLIPSE) RECONOZCA LAS ANOTACIONES DE LA LIBRERIA.

@Getter // AQUÍ INDICO QUE GENERE AUTOMÁTICAMENTE TODOS LOS GET.
@Setter // AQUÍ INDICO QUE GENERE AUTOMÁTICAMENTE TODOS LOS SET.
@Builder // AQUÍ INDICO QUE CONTRUYA EL OBJETO PACIENTE Y LE ASIGNE SUS ATRIBUTOS(INTRNAMENTE Y AUTOMÁTICAMENTE).
@NoArgsConstructor // AQUÍ INDICO QUE GENERE EL CONSTRUCTOR VACÍO.
@AllArgsConstructor // AQUÍ INDICO QUE GENERE EL CONSTRUCTOR CON TODOS LOR ARGUMENTOS(ÚTIL PARA ConfigReader).


public class Patient {
	private String name;
	private String surname;
	private String birthdate;
	private String nationality;
	private String typeOfIdentification;
	private String identification;
	private String sex;
	private String email;
	private String phone;
	private String principalMotive;
	private String actualSymptoms;
	private String recentEvents;
	private String previousDiagnosis;
	private String profesionalObservations;
	private String keyWords;
	private String failedActs;
	private String interconsultation;
	private String patientEvolution;
	private String sessionDay;
	private String modality;
	private Integer sessionDuration;
	private String sessionFrequency;
	private String preferedContact;
	
	
	
	  // MANEJA TAMBIEN EL INGRESO DE CAMPOS VACÍOS ("").
    
}

