# Vinilos Mobile

Hemos sido contratados por la empresa The Software Design Company (TSDC) para crear la versión móvil de la aplicación Vinilos. Para este caso se utilizara una API REST que está previamente desarrollada y es muy similar a la API que hoy se esta usando en la versión web de Vinilos. El reto principal es replicar la funcionalidad existente, teniendo en cuenta las restricciones de diseño que imponen los móviles a nivel de hardware, políticas del sistema operativo Android y su modelo de programación. El objetivo es construir una aplicación móvil usando el lenguaje Kotlin.

## Documentación
- [Incepción](https://github.com/andesleodiego24/vinilos-mobile/wiki/Inception)
- [Diseño](https://www.figma.com/file/GOeTqTABwuqAT7UIBKJXLV/Vinilos?type=design&node-id=0-1&mode=design)
- [Tablero Kanban](https://github.com/users/andesleodiego24/projects/1)

## Backend
- [Generalidades](https://misw-4104-web.github.io/GuiasProyecto/generalidades.html#documentaci%C3%B3n-del-api)
- [Código](https://github.com/TheSoftwareDesignLab/BackVynils/blob/master/README.md#description)

## Puesta en marcha local
Para poner esta aplicacion a funcionar es necesario tener configurado un [emulador](https://developer.android.com/studio/run/emulator?hl=es-419) con android o tener un [dispositivo fisico](https://developer.android.com/studio/debug?hl=es-419) conectado al computador. En ambos casos deben estar disponibles para hacer debug de la aplicacion. 
En caso de contar con cualquiera de las dos, los pasos para ejecutar la aplicacion local son los siguientes:
- Clonar el repositorio
- Abrir el proyecto en Android Studio.
- Realizar un Build de la aplicacion, en caso de no tener alguna libreria instalada tomara un tiempo adicional en descargarla para complilar la aplicacion
- Asegurarse que se encuentra seleccionado el app en la seccion de configuracion que se encuentra en la parte superior del IDE
   <br/>
  ![image](https://github.com/andesleodiego24/vinilos-mobile/assets/124963914/4c4e27f8-5d42-46bd-884e-e8439d0a8922)
- Seleccionar el dispositivo que se desea utilizar
  <br/>
  ![image](https://github.com/andesleodiego24/vinilos-mobile/assets/124963914/bfa5487f-7df0-4993-945a-84c6c186ab41)
- Dar clic en run app o debug app. Dependiendo de si desea o no realizar debug
  ![image](https://github.com/andesleodiego24/vinilos-mobile/assets/124963914/5b92efd5-a1d7-45d4-af49-90767d1ba488)
- En caso de no tener problemas de compilacion o alguna clase de error, la aplicacion se ejecutara en su dispositivo seleccionado.
  <br/>
![image](https://github.com/andesleodiego24/vinilos-mobile/assets/124963914/3a21e6c3-fe37-4c8e-af8e-a57fac9b9bc2)

