# Mi Portal - Plataforma de E-Commerce

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

## Instrucciones de Despliegue

1 **Configuración del entorno de desarrollo**
El entorno de desarrollo escogido fue Eclipse 2024-03 en su versión de plataforma independiente. 
Este entorno de desarrollo se puede descargar de está página [Eclipse 2024-03](https://www.eclipse.org/downloads/packages/).

Una vez instalado Eclipse, se debe instalar la versión de Wildfly 31.0.1 usada en el proyecto y la versión de Apache Tomcat 10.1.23 para desplegar los proyectos por niveles.
Para descargar Wildfly, se puede visitar el siguiente enlace [Wildfly 31.0.1](https://www.wildfly.org/downloads/)
Para descargar Apache Tomcat, se puede visitar el siguiente enlace [Apache Tomcat 10.1.23](https://tomcat.apache.org/download-10.cgi)

Es recomendable manejar dentro del directorio raíz dos carpetas para guardar donde se alojaran los servidores dentro de nuestro equipo y donde se alojara el ambiente de desarrollo de eclipse.
La siguiente imagen es un ejemplo de como se encuentran las dos carpetas dentro de una maquina virtual:
![Imagen1](https://github.com/KironStylo/ProyectoArquitectura/assets/105558468/2c82c280-2cb7-45e0-a237-38c7a3082b0a)





1. **Despliegue del Nivel de Presentación**:
   - Configure el contenedor Tomcat y despliegue el WAR del nivel de presentación.
   - Asegúrese de que Tomcat esté configurado correctamente y en funcionamiento.

2. **Despliegue del Nivel de Lógica y Datos**:
   - Configure el contenedor Wildfly y despliegue los EAR correspondientes al nivel de lógica y datos.
   - Verifique la conexión a la base de datos MySQL y asegúrese de que Wildfly esté en funcionamiento.

3. **Integración de Aplicaciones Externas**:
   - Siga las instrucciones de los repositorios de [SendEmail](https://github.com/KironStylo/SendEmail) y [Portador](https://github.com/DanielFlorido/Portador) para desplegar y configurar las aplicaciones de envío de correos y cálculo de tarifa.

4. **Configuración del Servicio de Recomendaciones**:
   - Clone y despliegue el servicio de [ArtificialInteligenceApi](https://github.com/Cam1101/ArtificialInteligenceApi).
   - Asegúrese de que el servicio de recomendaciones esté en funcionamiento y correctamente integrado
