Mi Portal - Plataforma de E-Commerce
Descripción del Proyecto
Mi Portal es una plataforma de ventas en línea diseñada para priorizar la privacidad de los datos del cliente. Este proyecto ha sido desarrollado utilizando Jakarta 10 y Eclipse, estructurando el sistema en tres niveles principales: presentación, lógica y datos.

Arquitectura del Proyecto
Niveles del Sistema
Nivel de Presentación:

Desarrollado en Jakarta EE.
Desplegado en un contenedor Tomcat.
Incluye interfaces de usuario para la navegación y gestión de productos, carrito de compras, y recomendaciones personalizadas.
Nivel de Lógica:

Desarrollado en Jakarta EE.
Desplegado en un contenedor Wildfly.
Gestiona la lógica de negocio, como la administración de productos, pedidos y usuarios.
Nivel de Datos:

Desarrollado en Jakarta EE.
Desplegado en un contenedor Wildfly.
Maneja la persistencia de datos utilizando JPA y base de datos MySQL.
Integración con Aplicaciones Externas
Mi Portal se integra con dos aplicaciones .NET para ampliar su funcionalidad:

Envío de Correos:

Repositorio: SendEmail
Descripción: Esta aplicación se encarga de enviar correos electrónicos, como confirmaciones de pedidos y notificaciones de envío a los clientes.
Cálculo de Tarifa:

Repositorio: Portador
Descripción: Esta aplicación calcula la tarifa de envío de un pedido basado en la dirección de entrega proporcionada por el cliente.
Servicio de Recomendaciones con Inteligencia Artificial
Repositorio: ArtificialInteligenceApi
Descripción: Un servicio desarrollado para ofrecer recomendaciones de categorías de productos basadas en el historial de compras del usuario, utilizando técnicas de inteligencia artificial.
Manejo Sencillo de Pods
Repositorio: pods
Descripción: Un repositorio que facilita la integración y gestión de pods, permitiendo una edición sencilla de un pod específico dentro del sistema.
Integrantes del Proyecto
Juan Camilo Parrado  
Daniel Florido  
Santiago Molina Aranza  
Santiago Gallo Jaimes  
Santiago Camilo Rey Benavides  

Instrucciones de Despliegue
Despliegue del Nivel de Presentación:

Configure el contenedor Tomcat y despliegue el WAR del nivel de presentación.
Asegúrese de que Tomcat esté configurado correctamente y en funcionamiento.
Despliegue del Nivel de Lógica y Datos:

Configure el contenedor Wildfly y despliegue los EAR correspondientes al nivel de lógica y datos.
Verifique la conexión a la base de datos MySQL y asegúrese de que Wildfly esté en funcionamiento.
Integración de Aplicaciones Externas:

Siga las instrucciones de los repositorios de SendEmail y Portador para desplegar y configurar las aplicaciones de envío de correos y cálculo de tarifa.
Configuración del Servicio de Recomendaciones:

Clone y despliegue el servicio de ArtificialInteligenceApi.
Asegúrese de que el servicio de recomendaciones esté en funcionamiento y correctamente integrado con el nivel de lógica.
Gestión de Pods:

Clone el repositorio de pods.
Siga las instrucciones del repositorio para la integración y gestión de pods dentro del sistema.
Contacto
Para más información sobre este proyecto, por favor contacte a los integrantes del equipo a través de los medios proporcionados en la documentación interna.
