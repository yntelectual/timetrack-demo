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

Running
-------
```docker run -d -p 9080:8080 --env LEGACY_SYSTEM_URL=http://172.17.0.2:8080 yntelectual/timetrack-frontend```

You should expose the container port ```8080``` on some host port and you need to pass in the URL of the legacy syste mvia env param ```LEGACY_SYSTEM_URL```.
Note that the URL needs to be accessible from the newly created container(until the linking is done via docker network or compose).   


Open issues
-----------
* container linking via compose or docker network (as docker link is deprecated)
* validation
* testcases

        

