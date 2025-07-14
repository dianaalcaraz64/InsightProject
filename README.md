# 🧪 Automatización de pruebas – Insight Webapp

## 📋 Descripción del sistema

**Insight** es una aplicación web de gestión interna dirigida a profesionales de la salud mental. En esta primera versión, cuenta con un único tipo de usuario: el psicólogo, quien también cumple funciones de administrador.

Entre sus funcionalidades principales se encuentran:

- Registro e inicio de sesión  
- Alta, edición y búsqueda de pacientes

## 🎯 Objetivo de la automatización

Este proyecto tiene como objetivo validar el **flujo crítico** del sistema: desde el login hasta el registro y cierre de sesión del usuario, garantizando la correcta interacción con la UI y la persistencia de datos.

## 🧰 Tecnologías utilizadas

- Java  
- Selenium WebDriver  
- Maven  
- Faker (para generación de datos)  
- Page Object Model (POM)

## ⚙️ Funcionalidades del script

- ✅ Inicio de sesión con credenciales válidas  
- ✅ Verificación del nombre del usuario al iniciar sesión  
- ✅ Registro de nuevos pacientes con datos generados dinámicamente  
- ✅ Validación de navegación y visualización de elementos clave  
- ✅ Cierre de sesión y retorno a la pantalla de login  
- ✅ Esperas dinámicas con `WebDriverWait` y `ExpectedConditions`

## 🧪 Alcance de esta versión

Esta primera versión del script cubre el **camino feliz** de uso del sistema, centrado en:

- Acceso exitoso a la aplicación  
- Registro correcto de pacientes  
- Validaciones básicas post-login

## 🚀 Cómo ejecutar las pruebas

1. Clonar el repositorio  
2. Asegurarse de tener [Java](https://www.oracle.com/java/technologies/javase-downloads.html) y [Maven](https://maven.apache.org/) instalados  
3. Ejecutar desde consola:

```
mvn clean test
```

## 📌 Notas

- Este flujo está enfocado en las funcionalidades esenciales.  
- El proyecto será ampliado para cubrir escenarios negativos, validaciones de errores y pruebas de regresión.

## 👩‍💻 Sobre mí

Proyecto desarrollado por **Diana Alcaraz – QA**.  
Actualmente investigando herramientas de automatización como **n8n** e integraciones con IA para optimizar flujos de trabajo en testing.
