Laboratorio de WebServices

**Elementos a utilizar**
* JavaEE, JDK 1.8
* IDE Eclipse, elegir la opción Eclipse for Java EE
* IDE Eclipse, instalar Jboss Tools (Menú: Help -> Eclipse MarketPlace. Buscar "Jboss Tools"). Este paso solo es necesario para crear un proyecto nuevo.
* Instalar Jboss tools 
* Instalar SOAP-UI
* Instalar PostMan para Google Chrome
* Instalar el motor de base de datos Postgresql

**Descargar el proyecto**

Via SSH:
    
    git clone git@gitlab.com:fmancia/sd.git
    
Via HTTPS:
    
    git clone https://gitlab.com/fmancia/sd.git
    
Via transferencia de archivos por parte del Profesor

    
**Importar proyecto**
 * Abrir el IDE Eclipse
 * Menú File -> Import -> Existing Maven Project
 * Dentro de los archivos descargados, especificar la carpeta "sd\lab-ws\servidor\personas"
 * Finalizar
 * El IDE comenzará a importar las librerías de Maven
 
**Base de datos**
 * Crear una base de datos.
 * Configurar los datos de configuración y acceso en la clase "Bd.java".

```sql
CREATE TABLE persona
(
    cedula integer NOT NULL,
    nombre character varying(1000),
    apellido character varying(1000),
    CONSTRAINT pk_cedula PRIMARY KEY (cedula)
)
WITH (
   OIDS=FALSE
 );
```

**Verificación del código fuente**
 * Verificación de la clase JaxRsActivator.java
 * Verificación de la clase Bd.java
 * Verificación de la clase Persona.java
 * Verificación de la clase PersonaDAO.java
 * Verificación de la clase PersonaService.java
 * Verificación de la clase PersonaRESTService.java

**Deployar en Servidor**
 * Desde el IDE Eclipse, configurar el servidor de aplicaciones Wildfly10 (Verificar la guía de clase anterior sobre el laboratorio de JavaRMI)
 * Deploy del proyecto "personas" en el servidor Wildfly


**Verificación con herramientas: POSTMAN / SOAP-UI / Browser**
 * GET http://localhost:8080/personas/rest/personas
 * GET http://localhost:8080/personas/rest/personas/3298639
 * GET http://localhost:8080/personas/rest/personas/cedula?cedula=160160
 * POST http://localhost:8080/personas/rest/personas/
 * DELETE http://localhost:8080/personas/rest/personas/150150
 * Imágenes de ejemplo de utilización de POSTMAN para la petición POST en el directorio: https://gitlab.com/fmancia/sd/tree/master/lab-ws/cliente-postman
 

**Tarea**
 * Proyecto Servidor: Modificar el proyecto de ejemplo del profesor para incluir más funcionalidades.
    * Se deben crear servicios RESTful necesarios para la administración de "asignaturas"
    * Las asignaturas están asociadas a una persona.
    * Las asignaturas deben estar creadas en base de datos.
    * Los servicios RESTful deben poder:
        * crear/modificar/listar/borrar asignaturas.
        * asociar y desasociar una asignatura a una persona.
        * listar todas las asignaturas de una persona.
        * listar todos los alumnos de una asignatura.
		
 * Proyecto Cliente: Crear un programa cliente que realice que mediante los servicios rest del servidor lo siguiente:
	* Crear un proyecto Java Standalone, que pueda ser ejecutado simplemente en consola a través de una clase principal y con el método "main".
	* Investigar el consumo de servicios rest utilizando las clases: java.net.HttpURLConnection; java.net.URL; 
	* Para el parseo JSON puede utilizar cualquier librería como Json-Java. Ejemplo de una de sus clases org.json.JSONObject; 
	* Creación de una persona, solicitando datos al usuario.
	* Listado de personas existentes. Impresión en consola.
	* Listado de asignaturas de una persona. Impresión en consola.
	* Creación de una asignatura.
 
 * Forma de entrega:
     * Enviar un email al profesor:  fernandomancia@gmail.com 
	 * En el asunto del correo debe decir "Tarea RestFul - NOMBRE APELLIDO"
     * El email debe contener el link del repositorio personal del alumno/a con la posibilidad de que el usuario "fmancia" del gitlab.com
     * El email debe contener imagenes en PNG o JPG de los prints de pantalla de las pruebas a los restful solicitados.
     * En el link del repositorio gitlab del alumn@ debe estar el código fuente de la tarea solicitada.
	 * Se aceptan trabajos de 1 o 2 integrantes. En el código fuente del cliente se deberá agregar como comentario los nombres completos de cada uno.
     * Se debe entregar el Sábado 25/05/2019
	 
	 
**Ejemplo de llamado a servicio REST utilizando métodos de Java Estandar**

        try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestMethod("GET");
			
			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Error HTTP - código : " + conn.getResponseCode()+" : "+conn.getResponseMessage());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String output;
			System.out.println("Impresión del contenido de la respuesta: \n");
			while ((output = br.readLine()) != null) {
				System.out.println(output);
			}

			conn.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}