# user-creator-test
Servicio de creación de usuarios

El presente proyecto tiene como proposito demostrar las distintas competencias orientadas a servicios RestFul bajo tecnoligia JAVA y su framwork de inyección de dependencias SPring Boot.

# Como construir el project usando Docker

docker build -t ${project_name} .
docker run -it -p 8080:8080 ${project_name}

*********************************************************************
# Instalación y ejecución
********************************************************************* 

NOTA : Como pr-requisitos asegúrese  tener instalado maven en su equipo local, acá un manual -> https://maven.apache.org/install.html

1. clonar proyecto
    
    git clone https://github.com/sinfonico7/user-creator-test.git
    
    cd user-creator-test

2. ejecutar la siguiente línea de comando:
    
    mvn spring-boot:run
   
3. abra un navegador visite el siguiente link
   http://localhost:8080/swagger-ui/#/user-controller/createUserUsingPOST
   
   NOTA: Cuando agregue el usuario la expresión regular que actualmente se ha dejado es la siguiente: "grupos de letras de la a-z minusculas".
   por ejemplo, si intenta crear el usuario con un password de valor "haunter2" recibiara un json con el mansaje que el campo password no respeta la expresión,
   ingrese las variaciones para obtener los distintos resultados.

4.- Tras operaciones de inserción puede verificar los registros en el panel de administración de H2 en el siguiente link:
      
   http://localhost:8080/h2-console
    
    las siguientes opciones deben coincidir en el panel:
      
      Saved Settings:	Generic H2 (Embeded)
      Setting Name: Generic H2 (Embeded)
      Driver Class: org.h2.Driver
      JDBC URL: jdbc:h2:mem:usuarios
      User Name: banco
      Password: sa
    
AVISO: Se agrega funcionalidad con docker, puede descargar la imagen de la siguiente forma:

     docker pull finostroza/user-creator-docker:v1.0.0

  # Notas sobre la solución
  Como arquitectura se implementa una de tipo exagonal que permite
  la escalabilidad de la solución separados principalmente por infraestructura
  dominio y casos de usos para el negocio. También importante destacar que
  he escogido la inyección de dependencia mediante configuraciones declarando beans
  esto debido a que es mucho mas practico a la hora de hacer Mocking en pruebas unitarias
  y de integración, velando a su vez por la "atomicidad" de los estados en instancias (singleton)
  


La implementación de la solución se basa bajo el siguiente enunciado.

Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de
error.



1 .- Todos los mensajes deben seguir el formato: {"mensaje": "mensaje de error"}

> Se resuelve mediante ErrorHandler, sumado a excepciones custom que permiten dar una visibilidad mas objetiva de los errores, destacar que como log solo se ha dejado
> a escala de profundidad de info, puesto siguiendo buenas practicas que determinan que no es bueno "logear" y capturar excepciones (es una u otra).
> (https://www.javacodegeeks.com/10-best-practices-to-handle-java-exceptions.html)

2 .- Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más un listado de objetos "teléfono", respetando el siguiente formato:
  {"name": "Juan Rodriguez","email": "juan@rodriguez.org","password": "hunter2","phones": [{"number": "1234567","citycode": "1","contrycode": "57"}]}
  
  > Se implementa patron DTO (Encapsulación de modelo) https://martinfowler.com/eaaCatalog/dataTransferObject.html
    
3 .- Responder el código de status HTTP adecuado

  > Facilidad de implementación gracias al Objeto ResponseEntity<>(object,codigo-http);
  > para Correo existente, se determina el codigo 409 (CONFLICT) como el mas apropiado.

4 .- En caso de éxito, retorne el usuario y los siguientes campos: 
      * id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería más deseable un UUID)
      * created: fecha de creación del usuario -> @Temporal
      * modified: fecha de la última actualización de usuario
      * last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha de creación)
      * token: token de acceso de la API (puede ser UUID o JWT)
      * isactive: Indica si el usuario sigue habilitado dentro del sistema.
  
  > Exactamente como se solicita, mediante de la utilidad de Java se genera el UUID para persistir el Id del Usuario, no así para Id de phone, que ha sido Identity. 

5 .- Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya registrado".

  > Mediante a la implementación propuesta por JPARepository y su JPQL se agrega método findByEmail que permite la obtención de el usuario y determinar lanzar una UserException (mis disculpas por el nombre) o no.

6 .- El correo debe seguir una expresión regular para validar que formato sea el correcto. (aaaaaaa@dominio.cl)

  > Mediante @Valid (javax.validation) se validan campos de forma más optima como por ejemplo @NotBlank o @Pattern que permite el ingreso de una expresión regular para el match
  > Estos errores también han sido registrados en el error handler (MethodArgumentNotValidException) y además han sido formateados para respetar la convención de error propuesta en el inicio.

7 .- La clave debe seguir una expresión regular para validar que formato sea el correcto. (El valor de la expresión regular debe ser configurable)
  
  > Para la clave se ha dejado directamente en el controlador un simple Pattern.match para agregar patron mediante un objeto Environment que permite la obtención de valores
  > registrados en el application.properties, aclarar que no se puede utilizar la misma técnica de @Pattern de java.validation puesto su notación solo permite valores constantes 

8 .- El token deberá ser persistido junto con el usuario
  > Para el token se utiliza JWT con algoritmo de ES512

Banco de datos en memoria. Ejemplo: HSQLDB o H2.
  
  > Se utiliza H2 que durante el vuelo y notaciones de Hibernate se levanta la base de datos (construccion de tablas). 

Proceso de build vía Gradle o Maven.
  
  > Se utiliza Maven (lo conozco más, pero también he trabajado con Gradle)

Persistencia con JPA. Ejemplo: EclipseLink, Hibernate u OpenJPA.
  
  > Notaciones de Hibernate para entidades y JPARepository como repository manager.

Framework SpringBoot.
  
  > Spring Boot 2.6.7

Java 8+
  
  > Java 11 

Entrega en un repositorio público (github o bitbucket) con el código fuente y script de creación de BD.
  
  > Durante el levantamiento se genera la base, no es necesario script

JWT como token
  > Se implementa JWT
Pruebas unitarias
  > Mockito para pruebas de controlador y unitaria
Swagger
  > Swagger para documentación y pruebas de inserción

# Diagrama de la solución
Entidades

![github-small](https://github.com/sinfonico7/user-creator-test/blob/main/src/main/resources/images/entitydiagram.png)

Uml (adjunto archivo en resources/images)

![github-small](https://github.com/sinfonico7/user-creator-test/blob/main/src/main/resources/images/uml2.png)