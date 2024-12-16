<h1 align= "center"> Miniproyecto Maven </h1>

<h2> 1. Configuración de Maven:</h2>

 <b> Añadir las dependencias de MySQL y JUnit.</b>

Para realizar este proceso añadimos dentro del archivo pom.xml las dependencias de MySQL y JUnit, las
cuales nos permitirán acceder a la base de datos y realizar las pruebas unitarias respectivamente.

<b> Crear los perfiles de Maven para las bases de datos maestro y réplica.</b>

Para realizar este proceso he creado un archivo db.properties en la carpeta resources donde he 
indicado las variables necesarias para la conexión (driverClassName, que es la clase necesaria
para conectarnos a la base de datos, url, que indica la dirección en la que se encuentra la base de
datos, usuario y contraseña de esa base de datos) y a continuación he creado una carpeta dev y otra prod
donde he creado un archivo db.properties en cada una, indicando en el de la carpeta dev los datos de la
primera base de datos y en el de la carpeta prod el de la segunda.
<h2>2. Desarrollo de la Aplicación:</h2>
<b> Crear las clases necesarias para gestionar las operaciones CRUD con JDBC.</b>

Para poder gestionar las operaciones CRUD necesitamos principalmente estas clases:

<ul>
<li> Clase ConexionDB.
<li> Clase Usuario. </li>
<li> Clase UsuarioDAO. </li>
<li> Clase TestMain. </li></ul>

<b> Implementar la lógica para insertar, leer, actualizar y eliminar usuarios en la base
de datos.</b>

Para poder implementar la lógica he seguido los siguientes pasos:

<ol>
<li>En la clase ConexionDB crearemos el método para conectarnos a la base de datos, de tal
forma que, dependiendo del perfil elegido, nos conectemos al perfil deseado.</li>
<li>Después en la clase Usuario introduciremos las variables o campos que forman parte de nuestra
base de datos (en este caso id,nombre,email y fechadecreación).</li>
<li>Posteriormente en la clase UsuarioDAO (DAO hace referencia a Data Access Object -Objeto de Acceso
a Datos-) y en ella crearemos la lógica para realizar las operaciones CRUD. </li>
<li>Finalmente utilizaremos la Clase MainTest para realizar las operaciones CRUD.</li> </ol>

<h2>3. Pruebas Unitarias:</h2>
<b> Investigar la implementación de pruebas unitarias utilizando JUnit para verificar las
operaciones CRUD.</b>

Una vez que se han creado las clases y los métodos necesarios se pueden realizar pruebas
unitarias desde el archivo TestMain que he creado, para realizar las pruebas se crea la tabla
usuarios sobre la base de datos a la que apunte el perfil que hemos seleccionado (he añadido
también un perfil de h2 para simular una prueba, además de los dos perfiles de bases de datos
reales) para posteriormente comprobar que se pueden crear, leer, actualizar y borrar datos,
por último borramos la tabla por completo para asegurarnos de que no se queda ningún dato que
pueda comprometer nuevas pruebas.

<h2>5. CI/CD:</h2>
<b> Investigar cómo configurar una integración básica con Jenkins, GitHub Actions o
GitLab CI para automatizar la construcción y ejecución de pruebas.</b>

En este caso he decidido elegir GitHub Actions, una vez que hemos subido nuestro repositorio a GitHub
(algo que podemos hacer directamente desde Intellij gracias a su integración con Git y Github) tendremos
que realizar los siguientes pasos:
<ol>
<li>Dentro de nuestro repositorio elegimos la opción Actions.</li>
<li>En la nueva pestaña elegimos el workflow (flujo de trabajo) que utilizaremos, en mi caso he elegido
"Java with Maven", dentro del grupo de Continuous Integration, y le damos a Configure.</li>
<li>Se creará dentro de tu repositorio en la carpeta .github/workflows un archivo .yml, que es el
encargado de definir el flujo de trabajo de CI y especifica los pasos que GitHub Actions debe seguir para construir el proyecto, ejecutar las pruebas y proporcionarte los resultados. Podremos cambiar la
configuración del archivo .yml y, para finalizar, damos a Commit Changes para guardarlo. </li></ol>
