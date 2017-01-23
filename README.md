timetrack-frontend: Simplistic frontend for timetrack legacy system
========================

What is it?
-----------

This is a very simple frontend for Timetrack legacy system. Project is built on Jee7 using CDI, EJB and JAXRS. 


System requirements
-------------------

* jdk 1.8_x
* maven > 3.2.0
* docker

 
Building
---------------

1. cd to the project folder.
2. run the maven build
    ```mvn clean install docker:build```

This should resutl in an error-free build and a new docker image should be created in your local repo under name ```yntelectual/timetrack-frontend```

Running(standalone)
-------
The whole build is managed via maven, including the docker build.

1. ```docker run -d -p 9080:8080 --env LEGACY_SYSTEM_URL=http://172.17.0.2:8080 yntelectual/timetrack-frontend```
2. Navigate to ```http://localhost:9080/timetrack-frontend/``` (based on your port mapping) 

You should expose the container port ```8080``` on some host port and you need to pass in the URL of the legacy system via env param ```LEGACY_SYSTEM_URL```.
Note that the URL needs to be accessible from the newly created container(until the linking is done via docker network or compose).   

Running via docker compose
--------------------------

If you would like to run the whole setup including the legacy container on a single host, use the provided docker compose setup.  
Compose exposes the frontend on port 8080 of the host machine. 

1. build the project using ```mvn clean install docker:build```
2. start the project using ```docker-compose up```
3. navigate to ```http://localhost:8080/timetrack-frontend```

Open issues
-----------
* ~~container linking via compose or docker network (as docker link is deprecated)~~
* validation
* testcases

Changelog
-----------       
* added docker compose build
* fixed handling of empty string/null for email in time entry list
* improved paging in list view
* improved navigation
