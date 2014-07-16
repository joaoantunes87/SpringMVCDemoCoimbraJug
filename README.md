<h1>Spring MVC Demo CoimbraJug</h1>

This repository is the code produced for a simple Demo how to build a Website and Rest Api using the Spring MVC Frameworks.

<h3>Features</h3>

<h5>This website will have only two pages:</h5>
<ul>
  <li>Root Page - Where all tasks are shown and a form for creating new ones</li>
  <li>Task Page - Page where the details of a specific task are shown</li>
</ul>

<h5>Besides the Website pages we will have access to a simples Rest Api in Json:</h5>
<ul>
  <li>Fetch Task Details - GET /todos/{id} e.g http://localhost/todos/1</li>
  <li>Edit Task Details - PUT /todos/{id} e.g http://localhost/todos/1</li>
  <li>Remove Task - DELETE /todos/{id} e.g http://localhost/todos/1</li>
</ul>

<h3>How To Run</h3>

<ol>
  <li>Install maven</li>
  <li>Execute mvn clean install in root dir</li>
  <li>Deploy the *.war found at /target in your J2EE Container (e.g Apache Tomcat or Jetty )</li>
  <li>Go to a web brower and access the url http://localhost:8080 ( this url can vary according your configurations )
</ol>

<strong>If you just want to do some experimentations you can run a jetty server with Web App with a simple command: "mvn jetty:run". Then go to the web browser: http://localhost:8080/</strong> 

