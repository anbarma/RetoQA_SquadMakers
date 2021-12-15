RETO QA SquadMakers
Se quiere testear la web https://www.saucedemo.com/
Este proyecto se ha desarrollado con el lenguaje Java y Selenium

CONTENIDO
1. Se han creado dos clases para las características solicitadas
   1. La clase "ComprarArticulo" para testear la compra de un artículo
      1. Dividida en 10 test que irán testeando cada paso por separado
   2. La clase "HacerLogout" para testear al hacer logout
      1. Dividida en 5 test que van testeando cada paso por separado
2. Se ha creado la carpeta driver donde se incluye el driver del navegador Chrome
   Este proyecto ha sido testeado con la versión 96.0.4664.110 de Chrome
3. Archivo README.md con información detallada

EJECUCIÓN
1. Acceder al directorio src/test/java del proyecto SauceDemoProject y modificar la ruta de donde se encuentra el driver de Chrome en su equipo. 
   Para ello, hay que cambiar la línea 25 y 22 en cada una de las clases donde pone System.setProperty("webdriver.chrome.driver", "C:\\driver\\chromedriver.exe");
2. Importar el proyecto, para su desarrollo se ha usado IntelliJ.
3. Hacer click derecho en cada clase y seleccionar Run.