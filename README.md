# distribuido-tarea6
Al código fuente utilizado en la práctica de laboratorio de Servicios Web REST (que se encuentra en el GIT de la asignatura) agregue una nueva clase "Factura" que exponga varios servicios bajo el path "/facturas"

Un servicio POST que reciba datos de factura (ruc, razon social, fecha, monto) y realice la impresión en el log (log.info) de esos datos y seguido su nombre y apellido.
Un servicio GET que retorne una lista de 5 facturas creadas de forma estática.
Ejecute su cambio con POSTMAN y agregue a la evidencia de las captura de pantalla de la prueba.


Debe adjuntar en esta pregunta los archivos fuentes que modificó (*.java) agregando comentarios en el código con su nombre (la parte modificada) y la captura de pantalla de la prueba en postman (jpg, jpeg, png, etc) 

En las capturas debe estar en visto en el log del Wildfly, log.info con "Modificado por NOMBRE APELLIDO" en cada respuesta del servidor.
