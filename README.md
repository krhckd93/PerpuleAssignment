# Perpule-Assignment
  Simple RESTful web service.
  ## Project Details
    * Written in Java. 
    * Uses Google Cloud Platform SQL service to store data.
    * Deployed on Google App Engine.
    * Jersey library used for JAX-RS API.
  ## APIs
    * To create, list and delete users.
    * To create, list and delete customers.
    * To login users.
  ## Authentication Guidelines
    * Create a new user
    * Login with the correct credentials, on successful login authentication token is returned.
    * To access the other APIs, pass the authentication token in the request header as "auth" key.
