# user-creator-test
Servicio de creación de usuarios

El presente proyecto tiene como proposito demostrar las distintas competencias orientadas a servicios RestFul bajo tecnoligia JAVA y su framwork de inyección de dependencias SPring Boot.

*********************************************************************
**                    INSTALACIÓN Y EJECUCIÓN                      **
********************************************************************* 
1. clonar proyecto
   git clone https://github.com/sinfonico7/user-creator-test.git
   cd user-creator-test

2. ejecutar
   mvn spring-boot:run
   
3. abra un navegador pege y visite el siguiente link
   http://localhost:8080/swagger-ui/#/user-controller/createUserUsingPOST

4.- Tras operaciones de agregación de usuarios puede visitar el panel de administracion de persistencia en el siguiente link:
      http://localhost:8080/h2-console
    
    las siguientes opciones deben coincidir en el panel:
      Saved Settings:	Generic H2 (Embeded)
      Setting Name: Generic H2 (Embeded)
      Driver Class: org.h2.Driver
      JDBC URL: jdbc:h2:mem:usuarios
      User Name: banco
      Password: sa
    

*********************************************************************
**                    NOTAS ACLARATORIAS
  Como arquitectura se implementa una de tipo exagonal que permite
  la escalabilidad de la solucón separados principalmente por infraestructura
  dominio y casos de usos para el negocio. Tambien importante destacar que
  he escogido la inyeción de dependencia mediante configuraciones declarando beans
  esto debido a que es mucho mas practico a la hora de hacer Mocking en pruebas unitarias
  y de integración, velando a su vez por la atomicidad de los estados en instancias (singleton)
**
********************************************************************* 

La implementación de la solución se basa bajo el siguiente enunciado.

Desarrolle una aplicación que exponga una API RESTful de creación de usuarios.
Todos los endpoints deben aceptar y retornar solamente JSON, inclusive al para los mensajes de
error.



1 .- Todos los mensajes deben seguir el formato: {"mensaje": "mensaje de error"}

>>>> Se resuelve mediante ErrorHandler, sumado a excepciones custom que permiten dar una visibilidad mas objetiva de los errores, destacar que como log solo se ha dejado
>>>> a nivel de prfundidad de info, puesto siguiendo busnas practicas que determinan que no es necesario logear errorees si ya 
>>>> se gestionan sus excepciones de forma correcta. (https://www.javacodegeeks.com/10-best-practices-to-handle-java-exceptions.html)

2 .- Ese endpoint deberá recibir un usuario con los campos "nombre", "correo", "contraseña", más un listado de objetos "teléfono", respetando el siguiente formato:
  {"name": "Juan Rodriguez","email": "juan@rodriguez.org","password": "hunter2","phones": [{"number": "1234567","citycode": "1","contrycode": "57"}]}
  
  >>>> Se implementa patron DTO (Encapsulación de modelo) https://martinfowler.com/eaaCatalog/dataTransferObject.html
    
3 .- Responder el código de status HTTP adecuado

  >>>> Facilidad de implementaicón gracias al Objeto ResponseEntity<>(object,codigo-http);
  >>>> para Correo existente se determina el codigo 409 (CONFLICT) como el mas apropiado.

4 .- En caso de éxito, retorne el usuario y los siguientes campos: 
      * id: id del usuario (puede ser lo que se genera por el banco de datos, pero sería más deseable un UUID)
      * created: fecha de creación del usuario
      * modified: fecha de la última actualización de usuario
      * last_login: del último ingreso (en caso de nuevo usuario, va a coincidir con la fecha de creación)
      * token: token de acceso de la API (puede ser UUID o JWT)
      * isactive: Indica si el usuario sigue habilitado dentro del sistema.
  
  >>> Exactamente como se solicita, mediante de la ultilidad de Java se genera el UUID para persistir el Id del Usuario, no asi para Id de phone que ha sido Identity 

5 .- Si caso el correo conste en la base de datos, deberá retornar un error "El correo ya registrado".

  >>> Mediante a la implementacion prpuesta por JPARepository y su JPQL se agrega metodo findByEmail que permite la obtención de el usuario y determinar lanzar una UserException (mis disculpas por el nombre)

6 .- El correo debe seguir una expresión regular para validar que formato sea el correcto. (aaaaaaa@dominio.cl)

  >>> Mediante @Valid (javax.validation) se validan campos de forma mas optima como por ejempo @NotBlank o @Pattern que permite el ingreso de una expresión regular para el match
  >>> Estos errores tambien han sido registrados en el error handler (MethodArgumentNotValidException) y ademas han sido formatedos para respetar la convención de error propuesta en el inicio.

7 .- La clave debe seguir una expresión regular para validar que formato sea el correcto. (El valor de la expresión regular debe ser configurable)
  >>> Para la clave se ha dejado directamente en el controlador un simple Patern.match para agregar patron mediante un objeto Environment que permite la obtencion de valores
  >>> registrado en el application.properties, aclarar que no se puede utilizar la misma tecnica de @Patern de java.validation puesto su notación solo permite valores constantes 

8 .- El token deberá ser persistido junto con el usuario
  >>> Para el token se utiliza JWT con algoritmo de 512

● Banco de datos en memoria. Ejemplo: HSQLDB o H2.
  >>> Se utiliza H2 que durante el vuelo y notaciones de Hibernate se levanta la base de datos (construccion de tablas). 
● Proceso de build vía Gradle o Maven.
  >>> Se utiiliza Maven (lo conozco mas, pero tambien he trabajado con Gradle)
● Persistencia con JPA. Ejemplo: EclipseLink, Hibernate u OpenJPA.
  >>> Notaciones de Hibernate para entidades y JPARepository como repository manager.
● Framework SpringBoot.
  >>> Spring Boot 2.6.7
● Java 8+
  >>> Java 11 
● Entrega en un repositorio público (github o bitbucket) con el código fuente y script de
creación de BD.
  >>> Durante el levantamiento se genera la base, no es necesario script
● Diagrama de la solución.

● JWT como token
● Pruebas unitarias
>>> Mockito para pruebas de controlador y unitaria
● Swagger
>>> Swagger para documentacion y pruebas de inserción