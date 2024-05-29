 Mi Portal - Plataforma de E-Commerce

## Descripción del Proyecto

Mi Portal es una plataforma de ventas en línea diseñada para priorizar la privacidad de los datos del cliente. Este proyecto ha sido desarrollado utilizando Jakarta 10 y Eclipse, estructurando el sistema en tres niveles principales: presentación, lógica y datos.

## Arquitectura del Proyecto

### Niveles del Sistema

1. **Nivel de Presentación**:
   - Desarrollado en Jakarta EE.
   - Desplegado en un contenedor Tomcat.
   - Incluye interfaces de usuario para la navegación y gestión de productos, carrito de compras, y recomendaciones personalizadas.

2. **Nivel de Lógica**:
   - Desarrollado en Jakarta EE.
   - Desplegado en un contenedor Wildfly.
   - Gestiona la lógica de negocio, como la administración de productos, pedidos y usuarios.

3. **Nivel de Datos**:
   - Desarrollado en Jakarta EE.
   - Desplegado en un contenedor Wildfly.
   - Maneja la persistencia de datos utilizando JPA y base de datos MySQL.

## Integración con Aplicaciones Externas

Mi Portal se integra con dos aplicaciones .NET para ampliar su funcionalidad:

1. **Envío de Correos**:
   - Repositorio: [SendEmail](https://github.com/KironStylo/SendEmail)
   - Descripción: Esta aplicación se encarga de enviar correos electrónicos, como confirmaciones de pedidos y notificaciones de envío a los clientes.

2. **Cálculo de Tarifa**:
   - Repositorio: [Portador](https://github.com/DanielFlorido/Portador)
   - Descripción: Esta aplicación calcula la tarifa de envío de un pedido basado en la dirección de entrega proporcionada por el cliente.

## Servicio de Recomendaciones con Inteligencia Artificial

- Repositorio: [ArtificialInteligenceApi](https://github.com/Cam1101/ArtificialInteligenceApi)
- Descripción: Un servicio desarrollado para ofrecer recomendaciones de categorías de productos basadas en el historial de compras del usuario, utilizando técnicas de inteligencia artificial.

## Manejo Sencillo de Pods

- Repositorio: [pods](https://github.com/DanielFlorido/pods)
- Descripción: Un repositorio de prueba que realizamos para acceder a un pod creado con el proveedor Inrupt para hacer operaciones sencillas de modificar, agregar, eliminar y ver una dirección de un usuario dado su WebID.

## Integrantes del Proyecto

- **Juan Camilo Parrado**
- **Santiago Rey Benavides Camilo**
- **Santiago Gallo Jaimes**
- **Santiago Molina Aranza**
- **Daniel Florido**

## Proceso de configuración en Eclipse (Despliegue)

## 1. **Obtener las tecnologías**
El entorno de desarrollo escogido fue Eclipse 2024-03 en su versión de plataforma independiente de manera local el proyecto.
Este entorno de desarrollo se puede descargar de está página [Eclipse 2024-03](https://www.eclipse.org/downloads/packages/).

Una vez instalado Eclipse, se debe instalar la versión de Wildfly 31.0.1 usada en el proyecto y la versión de Apache Tomcat 10.1.23 para desplegar los proyectos por niveles.
Para descargar Wildfly, se puede visitar el siguiente enlace [Wildfly 31.0.1](https://www.wildfly.org/downloads/)
Para descargar Apache Tomcat, se puede visitar el siguiente enlace [Apache Tomcat 10.1.23](https://tomcat.apache.org/download-10.cgi)

Además el proyecto se ejecuta con Java 17 debido a la compatibilidad con los contenedores 

## 2. **Configuración de los servidores de Wildfly de datos y lógica**

**2.1 Configuración de servidores de Wildfly de datos**
Es recomendable manejar dentro del directorio raíz dos carpetas para guardar donde se alojaran los servidores dentro de nuestro equipo y donde se alojara el ambiente de desarrollo de eclipse.
La siguiente imagen es un ejemplo de como se encuentran las dos carpetas dentro de una maquina virtual:

![Imagen1](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/2c82c280-2cb7-45e0-a237-38c7a3082b0a)

Asi se debe ver la carpeta al final despues de haber descargado y descomprimido los archivos dentro de un directorio de nuestra directorio raiz en el equipo que nos encontremos:

![Imagen2](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/6d86d29c-ce15-494d-85ef-0509a6cc3aed)

Es importante tener en cuenta que las configuraciones de los servidores Wildfly es crítica en una arquitectura por niveles, por lo que los archivos "standalone.xml" cambian de acuerdo al rol que cumple el contenedor. Primero, se va empezar explicando la configuración para la carpeta de Wildfly de datos:

Primero en la ruta mostrada en la siguiente imagen, es importante tener un carpeta nombrada mysql.
![Imagen3](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/a43c4c0d-0e09-4fc5-8a36-52c964ee135c)

Segundo, se debe tener una carpeta llamada main, la cual contendra un archivo module.xml y el connector de MySQL para permitir a Wildfly tener un DataSource con el motor de base de datos MySQL.
![Imagen4](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/ce328e9e-d7fc-43a9-9463-7f1e5065ef01)

Para descargar el conector de MySQL se puede obtener de la siguiente plataforma [JConnector](https://dev.mysql.com/downloads/connector/j/).

El module xml se tiene que ver así y lo único que se debe cambiar es la versión seleccionada del conector a la base de datos MySQL como se muestra en el siguiente fragmento:

```xml
<module xmlns="urn:jboss:module:1.5" name="com.mysql">
    <resources>
        <resource-root path="mysql-connector-j-8.3.0.jar" />
    </resources>
    <dependencies>
        <module name="javax.api"/>
        <module name="javax.transaction.api"/>
    </dependencies>
</module>
```

Tercero, para configurar el standalone.xml, se debe indicar el nombre de la conexión a la base de datos, y también se debe crear desde el WorkBench de MySQL una base de datos con un usuario con permisos para manejar aquella base de datos, se recomienda usar la siguiente configuración en la que solamente se debe cambiar el nombre de la base de datos, el nombre de usuario y contraseña que tiene permisos sobre la base de datos.
```xml
<datasource jndi-name="java:/MiPortalDS" pool-name="MiPortalDS" enabled="true" use-java-context="true" statistics-enabled="${wildfly.datasources.statistics-enabled:${wildfly.statistics-enabled:false}}">
    <connection-url>jdbc:mysql://localhost:3306/miportal</connection-url>
    <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
    <driver>mysql</driver>
    <security user-name="miportal" password="miportal"/>
    <validation>
        <valid-connection-checker class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLValidConnectionChecker"/>
        <validate-on-match>true</validate-on-match>
        <exception-sorter class-name="org.jboss.jca.adapters.jdbc.extensions.mysql.MySQLExceptionSorter"/>
    </validation>
</datasource>
<drivers>
    <driver name="h2" module="com.h2database.h2">
        <xa-datasource-class>org.h2.jdbcx.JdbcDataSource</xa-datasource-class>
    </driver>
    <driver name="mysql" module="com.mysql">
        <driver-class>com.mysql.cj.jdbc.Driver</driver-class>
        <xa-datasource-class>com.mysql.cj.jdbc.MysqlXADataSource</xa-datasource-class>
    </driver>
</drivers>
```
Además de esta configuración, también se debe especificar el puerto por donde el servidor de datos de Wildfly recibira las peticiones del servidor de lógica. En el siguiente fragmento de xml se muestra que se debe cambiar el off-set del puerto a 400 para que el puerto escuche desde el puerto con número 8480 como se muestra a continuación:

```xml
<socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:400}">
        <socket-binding name="ajp" port="${jboss.ajp.port:8009}"/>
        <socket-binding name="http" port="${jboss.http.port:8080}"/>
        <socket-binding name="https" port="${jboss.https.port:8443}"/>
        <socket-binding name="management-http" interface="management" port="${jboss.management.http.port:9990}"/>
        <socket-binding name="management-https" interface="management" port="${jboss.management.https.port:9993}"/>
        <socket-binding name="txn-recovery-environment" port="4712"/>
        <socket-binding name="txn-status-manager" port="4713"/>
        <outbound-socket-binding name="mail-smtp">
            <remote-destination host="${jboss.mail.server.host:localhost}" port="${jboss.mail.server.port:25}"/>
        </outbound-socket-binding>
</socket-binding-group>
```
Para verificar el funcionamiento de que el servidor esta conectado con la base de datos MySQL, se puede ir a linea de comandos y ejecutar el bat de standalone donde se podra apreciar si la conexión con la base de datos desde el contenedor de Wildfly fue exitosa. Se debe ir hasta al directorio donde se encuentra el bat el cuál es "bin" como se muestra en la pantalla y se abre una consola de comandos:
![Imagen7](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/e46ce3d8-fa96-4904-8283-9cd2a291122f)

En la consola de comandos se puede evidenciar que la conexión con la base de datos fue exitosa dado que el nombre "MiPortalDS" aparece en la línea de comandos:\
![Imagen6](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/dbbd2654-083a-4c40-8d48-339ed2b6042b)



**2.2 Configuración de servidores de Wildfly de lógica**
Se debe acceder desde el directorio de archivos a la carpeta del servidor de Wildfly Lógica y entrar al fichero "standalone" y luego al "configuration" para acceder al "standalone.xml".
![Imagen5](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/01548ea2-7aa2-4474-ae21-815237b5b5f5)

La configuración del Widlfly de lógica es más sencilla puesto que solo requiere repetir el paso anterior y cambiar el puerto del servidor a 8280 cambiando el offset a 200 en el standalone.xml.
```xml
<socket-binding-group name="standard-sockets" default-interface="public" port-offset="${jboss.socket.binding.port-offset:400}">
        <socket-binding name="ajp" port="${jboss.ajp.port:8009}"/>
        <socket-binding name="http" port="${jboss.http.port:8080}"/>
        <socket-binding name="https" port="${jboss.https.port:8443}"/>
        <socket-binding name="management-http" interface="management" port="${jboss.management.http.port:9990}"/>
        <socket-binding name="management-https" interface="management" port="${jboss.management.https.port:9993}"/>
        <socket-binding name="txn-recovery-environment" port="4712"/>
        <socket-binding name="txn-status-manager" port="4713"/>
        <outbound-socket-binding name="mail-smtp">
            <remote-destination host="${jboss.mail.server.host:localhost}" port="${jboss.mail.server.port:25}"/>
        </outbound-socket-binding>
</socket-binding-group>
```

## 3. **Configuración del entorno de desarrollo Eclipse**
Ahora en Eclipse se deben hacer otras configuraciones para que se instancien los contenedores Wildfly y el servidor de Tomcat desde el IDE directamente.

La primera configuración es agregar los Server Runtime para los tres tipos de servidores teniendo presente el estilo arquitectónico definido de niveles que se debe mantener incluso en la creación de los tiempos de ejecución de los servidores como se muestra a continuación:

Se debe ir al menu de preferencias y en la barra de búsqueda escribir "Runtime" para agregar los runtime de cada uno de los servidores:
![Imagen9](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/d73cee0e-98a6-4daa-98e7-1fbf84dcee00)

Luego en el menu se deben ir agregando uno por uno los runtime de los servidores como se muestra en las siguientes tres imagenes:

La primera imagen muestra la configuración del runtime del servidor de datos de Wildfly:

![Imagen10](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/0450a59f-8989-4b35-834f-7987a4c2e832)

La segunda imagen muestra la configuración del runtime del servidor de lógica de Wildfly:

![Imagen11](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/8c39c16f-8e35-4ae9-9b1b-d83757d4096c)

La tercera imagen muestra la configuración del runtime del servidor de presentación de Tomcat:

![Imagen13](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/0aaf5c79-fdc2-4cbc-9aea-70b0491207a1)

Ahora se deben crear los servidores cuyos runtimes seran efectivamente los creados anteriormente y serviran para desplegar los proyectos de este repositorio.

Para crear un servidor, basta con ir a la vista de consola e indicar que se va a crear un nuevo servidor y las consideraciones a tener cuenta es que la versión del servidor debe ser igual a la del runtime con el fin de evitar conflictos de desarrollo. De esta forma, se asegurá un despliegue correcto y seguro de cada uno de los proyectos (Datos EAR, Logica EAR, WebProyecto).

Así se debe ver la vista de los servidores:
![Imagen16](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/bb5d5c95-835d-424b-b88b-fcbe3103f21b)

## 3. **Configuración para integración con Primefaces JSF en Eclipse**
Finalmente, fue un reto encontrar las librerias correctas para hacer funcionar la parte de la presentación Java Server Faces con Tomcat desde Eclipse y se mencionan las librerías utilizadas para usar la biblioteca de componentes de Primefaces que fueron los siguientes.

Librerias utilizadas:
- Libreria GSON 2.1.1.0
- Libreria Hibernate Core 6.5.2
- Libreria Jakarta Faces 3.0.3
- Libreria Jakarta Faces Api 3.0.0
- Libreria Jakarta Servlet api 5.0.0
- Libreria Primefaces - 14.0.0 - jakarta
- LIbreria Primefaces - extensiones - 14.0.0 - jakarta


1. **Despliegue del Nivel de Presentación**:
   - Configure el contenedor Tomcat y despliegue el proyecto WebProyecto.

2. **Despliegue del Nivel de Lógica**:
   - Configure el contenedor Wildfly y despliegue el EAR de Logica EAR.
     
3. **Despliegue del Nivel de Lógica**:
   - Configure el contenedor Wildfly y despliegue el EAR de Datos EAR.

4. **Integración de Aplicaciones Externas**:
   - Siga las instrucciones de los repositorios de [SendEmail](https://github.com/KironStylo/SendEmail) y [Portador](https://github.com/DanielFlorido/Portador) para desplegar y configurar las aplicaciones de envío de correos y cálculo de tarifa.

5. **Configuración del Servicio de Recomendaciones**:
   - Clone y despliegue el servicio de [ArtificialInteligenceApi](https://github.com/Cam1101/ArtificialInteligenceApi).
   - Asegúrese de que el servicio de recomendaciones esté en funcionamiento y correctamente integrado
   
## Videos de demostración:
En estos videos se evidencia el funcionamiento del proyecto y una breve explicación del código realizado:
Primer video donde se hace la ejecución del proyecto y la explicación del modelo: [Video 1](https://www.youtube.com/watch?v=pmWFzzU_NB4)
Segundo video donde se hace la ejecución y se muestra la parte implementada de PODS y el funcionamiento de las recomendaciones [Video 2](https://youtu.be/SpSvXu7DXGE)

