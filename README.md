# SPRINGBOOT KONG MICROSERVICE
## About the Service
This repository contains a simple springboot microservice that retrieves user information based on the id specified in the url field. 
It uses KONG API GATEWAY to abstract away the microservice itself, such that all requests are made to
the Kong Gateway which then redirects it to the springboot application. 

## REQUIRED SOFTWARE
- An IDE ( [Visual Studio Code](https://code.visualstudio.com/download) or [intellij IDEA](https://www.jetbrains.com/idea/download/?source=google&medium=cpc&campaign=APAC_en_AU_IDEA_Branded&term=intellij+idea&content=602143185772&gclid=EAIaIQobChMI-f3uuYnegwMVwqRmAh0_ewXKEAAYASABEgImY_D_BwE&section=windows) )
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Postman API](https://www.postman.com/downloads/)
- A Web Browser ( [Google Chrome](https://www.google.com/chrome/) or [FireFox](https://www.mozilla.org/en-US/firefox/new/))
- [Java](https://www.oracle.com/java/technologies/downloads/) 17 at minimum installed on your local machine.
- [Git](https://www.git-scm.com/downloads)

## Setting Up - CLONE THE PROJECT
- Open a terminal or command prompt (Powershell on Windows and Terminal or linux or macOs)
- Navigate to the directory you want to clone the project. e.g. `cd path/to/your/directory`
- Run `git clone git@github.com:msackey-IW/springboot-kong-microservice.git`
- Run `cd microservice`
- Run `docker-compose up`. This will create and run the microservice that retrieves user information.
- The endpoints are exposed via a kong gateway through the following url `http://localhost:8000/user/{id}`. 
- The id is field is a dynamic resource and returns all information attached to the user with the specified id.
- The database can be accessed though [PGAMIN4](http://localhost:5050). 
- The default login credentials for the database are available in `microservice/docker-compose.yaml`
## Setting Up - DOWNLOAD THE PROJECT
- Open and run Docker on your machine.
- Download the application [here](git@github.com:msackey-IW/springboot-kong-microservice.git) by navigating to `code -> download ZIP`.
- Unzip the folder and open the extracted files in your downloaded IDE(Intellij or VSCode).
- In your IDE terminal, navigate to the eCommerce folder using the command `cd eCommerce`.
- Run `docker-compose up`. This will create and run postgreSQL which is available via [PGAMIN4](http://localhost:5050), the eCommerce springboot application, along with prometheus and grafana for observability. 