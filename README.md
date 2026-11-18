# BatchFlow

Plataforma de liquidación batch y conciliación con Java, COBOL, Kafka y Angular.

**Estado:** documentación inicial; implementación sin iniciar. Proyecto formativo nuevo, separado de Ledgerly. No implica conexión a una red de pagos ni experiencia con un mainframe real.

## Documentación

La fuente canónica es [docs/latex/master.tex](docs/latex/master.tex). Incluye los capítulos de `docs/latex/chapters/`: alcance, método, arquitectura, contenerización, contratos, pruebas, iteraciones y decisiones. No se duplican en Markdown.

[Documento maestro PDF](output/pdf/master.pdf), versión 0.2. La edición documental existente permite entregar este PDF; el procedimiento reproducible de compilación LaTeX dentro de Docker se implementará junto al contenedor de documentación. No existe todavía ese Dockerfile ni un comando de compilación en contenedor verificado.

## Ejecución íntegra en Docker

El entorno objetivo utiliza Docker Engine y Compose con contenedores Linux. Compilación, tests y ejecución se realizarán en contenedores: Maven/JDK, GnuCOBOL, Node/Angular y LaTeX. El equipo necesita Docker y Git; las herramientas de lenguaje locales no son requisito de ejecución.

Los servicios Java, el gateway, Angular, PostgreSQL, Kafka y el proveedor de identidad se orquestarán con Compose. Cada servicio que invoque COBOL incluirá su ejecutable y runtime nativos; no llamará a WSL desde el contenedor. Las imágenes serán versionadas y los secretos/datos locales quedarán fuera de Git.

Los Dockerfiles, Compose y perfiles de desarrollo/pruebas aún no están implementados. Los construirá el alumno por iteraciones con revisión del tutor. No se anuncian comandos de arranque que todavía no se hayan verificado.

## Trabajo por iteraciones

Objetivo observable -> riesgo principal -> pruebas -> implementación del alumno -> revisión -> actualización de decisiones.

JUnit, Mockito y pruebas de integración acompañan a cada caso de uso. El primer bloque de desarrollo será un contenedor de herramientas Maven y el módulo mínimo de `settlement-service`, seguido del objeto de valor monetario y sus tests. No necesita levantar todavía toda la infraestructura.
