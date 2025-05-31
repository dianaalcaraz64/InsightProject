package com.insight.automation.utils;

// LIBRERIA json-simple
import org.json.simple.JSONArray; // CLASE PARA MANEJAR ARRAYS DENTRO DE UN JSON.     
import org.json.simple.JSONObject; // CLASE PARA MANEJAR OBJETOS JSON.
import org.json.simple.parser.JSONParser; // CLASE PARA LEER Y CONVERTIR DE OBJETO JSON A OBJETO JAVA.

//CLASES PROPIAS DE JAVA
import java.io.InputStream;      // CLASE PARA LEER ARCHIVOS BINARIOS/TEXTO.
import java.io.InputStreamReader;  // CONVIERTE EL INPUTSTREAN(DATO) EN TEXTO LEGIBLE PARA JAVA.



public class ConfigReaderLogin {
	
	public static JSONArray leerArray(String archivo, String clave) {
        try {
            InputStream is = ConfigReaderLogin.class.getClassLoader().getResourceAsStream(archivo);//BUSCA EL ARCHIVO JSON(EL CUAL SE PASA POR PARAMETRO EN @DATAPROVIDER(EN EL TEST))
            if (is == null) {
                throw new RuntimeException("No se encontró el archivo: " + archivo); // RESPUESTA POR SI NO ENCUENTRA ESE JSON.
            }

            JSONParser parser = new JSONParser(); // ACA SE LEE EL JSON(ARCHIVO) QUE ESTA EN EL InputStram
            JSONObject jsonObject = (JSONObject) parser.parse(new InputStreamReader(is, "UTF-8"));  // ACA PASAMOS EL OBJETO JSON A OBJETO JAVA(UTF-8, LEGIBLE EN JAVA).

            return (JSONArray) jsonObject.get(clave); // CLAVE(EJ:"USUARIOS" EN ARCHVO JSON),EXTRAE EL VALOR DEL ARRAY DE OBJETOS. EL RETORNO SE USA EN @DATA PROVIDE DEL TEST.

        } catch (Exception e) {
            throw new RuntimeException("Error al leer JSON: " + e.getMessage());// SI POR ALGUNA RAZON NO SE PUEDE LEER EL JSON, EJ: ARCHIVO NO VALIDO, MAL FORMADO ETC. DEVUELVE ESA INCIDENCIA(IMPRIME MENSAJE). AYUDA A IDENTIFICAR EL PROBLEMA.
        }
    }
	
	
	
	
	
	
	
	

}
