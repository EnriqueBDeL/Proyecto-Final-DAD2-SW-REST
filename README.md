<div align="center">

# 🎓 SISTEMA DE GESTIÓN UNIVERSITARIA Web (REST)

### Práctica 2 - Desarrollo de Aplicaciones Distribuidas II

![Java](https://img.shields.io/badge/Java_EE-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Tomcat](https://img.shields.io/badge/Apache_Tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=black)
![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)
![Jersey](https://img.shields.io/badge/Jersey_REST-FF0000?style=for-the-badge&logo=java&logoColor=white)
![HTML5](https://img.shields.io/badge/HTML5-E34F26?style=for-the-badge&logo=html5&logoColor=white)
![JavaScript](https://img.shields.io/badge/JavaScript-F7DF1E?style=for-the-badge&logo=javascript&logoColor=black)
![Status](https://img.shields.io/badge/Status-En_desarrollo-orange?style=for-the-badge)
<br>

<p align="center">
  <b>Una solución basada en servicios web REST para la administración académica distribuida.</b><br>
  Desarrollo de una API RESTful y un cliente web frontend sin dependencias de bases de datos externas.
</p>

</div>

---

## 📋 Descripción del Proyecto

[cite_start]Este proyecto consiste en el desarrollo de los conceptos teóricos de aplicaciones distribuidas mediante la implementación de un servicio web REST[cite: 12]. [cite_start]El objetivo es dotar a la plataforma de las funcionalidades completas de un sistema de gestión universitaria[cite: 13].

[cite_start]La aplicación consta de dos partes claramente diferenciadas[cite: 15]:
1. [cite_start]Un **servicio REST** que centraliza la lógica y la gestión del portal, desarrollado con la librería Jersey[cite: 16, 18].
2. [cite_start]Una **página cliente (HTML o JSP)** que integra los elementos necesarios de interfaz y llamadas asíncronas mediante JavaScript para hacer uso del servicio REST[cite: 17].

[cite_start]*Nota: Para esta versión del proyecto, los datos se manejan sin el uso de una base de datos externa, valorándose la integración y el intercambio de datos en memoria con los servlets de la práctica anterior[cite: 19, 20].*

---

## ⚙️ Arquitectura y Despliegue

El sistema está diseñado bajo una arquitectura cliente-servidor, separando de forma clara la lógica de negocio (API REST) de la interfaz gráfica de usuario. 

Para garantizar la portabilidad y correcta ejecución, el sistema requiere la siguiente infraestructura:

| Componente | Tecnología | Función Principal / Requisito |
| :---: | :---: | :--- |
| **Servidor** | Apache Tomcat | Servidor de aplicaciones web sobre el que se despliega el servicio REST. |
| **IDE** | Eclipse | Entorno de desarrollo recomendado para el proyecto. [cite_start]El proyecto exportado debe llamarse con el DNI[cite: 42]. |
| **Librería REST** | Jersey | [cite_start]Framework de Java utilizado para la creación de los endpoints[cite: 18]. |
| **Persistencia** | Memoria / Sesión | Almacenamiento local temporal. No se requiere conexión a motores BBDD SQL externos. |

---

## 🚀 Funcionalidades

El sistema está dividido en distintas entregas incrementales según las convocatorias. Al encontrarse en estado de **desarrollo**, los hitos se dividen de la siguiente manera:

### 🎯 Entrega Final (Mayo)
- [ ] [cite_start]**Titulaciones:** CRUD completo de todas las carreras de la universidad[cite: 24].
- [ ] [cite_start]**Asignaturas:** CRUD de asignaturas[cite: 25].
- [ ] [cite_start]**Profesores:** Funcionalidad para asignar profesores a una asignatura específica[cite: 26].

### 🌞 Entrega Julio
- [ ] [cite_start]**Seguridad y Usuarios:** CRUD de usuarios del sistema, permitido de forma exclusiva para administradores[cite: 33].
- [ ] [cite_start]**Alumnado:** CRUD para la gestión de la base de alumnos[cite: 34].
- [ ] [cite_start]**Matriculación:** Alta y baja (matriculación y desmatriculación) de alumnos en las diferentes asignaturas[cite: 35].

### 🌟 Convocatoria Especial
- [ ] [cite_start]**Aulas:** CRUD para la administración de espacios y aulas[cite: 38].
- [ ] [cite_start]**Gestión de Espacios:** CRUD para gestionar la relación y asignación entre asignaturas y aulas[cite: 39].

---

## 🛠️ Tecnologías y Herramientas

| Tecnología | Uso en el proyecto |
| :--- | :--- |
| **Java EE / J2EE** | Ecosistema base para la aplicación web distribuida. |
| **Jersey** | [cite_start]Implementación del servicio REST[cite: 18]. |
| **HTML / JSP** | [cite_start]Maquetación y estructura de la página cliente[cite: 17]. |
| **JavaScript** | [cite_start]Lógica de cliente para consumir los endpoints REST[cite: 17]. |
| **Apache Tomcat** | Despliegue del contenedor web. |

---

## 👥 Autores

<div align="center">

  <table>
    <tr>
      <td align="center">
        <a href="https://github.com/EnriqueBDeL">
          <img src="https://github.com/EnriqueBDeL.png" width="100px;" alt="Foto Enrique"/><br>
          <sub><b>EnriqueBDeL</b></sub>
        </a>
      </td>
      <td align="center">
        <a href="https://github.com/Agata-gp">
          <img src="https://github.com/Agata-gp.png" width="100px;" alt="Foto Agata"/><br>
          <sub><b>Agata-gp</b></sub>
        </a>
      </td>
    </tr>
  </table>

  <br>
  [cite_start]<i>[ Desarrollado para la asignatura Desarrollo de Aplicaciones Distribuidas II, Grado en Ingeniería Informática, UCAM [cite: 3, 5, 6] ]</i>
  
</div>
