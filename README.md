# SPRINGBOOT KONG MICROSERVICE
## About the Service
This repository contains a simple springboot microservice that retrieves user 
information based on the id specified in the url field. 
It uses the KONG API GATEWAY to abstract away the microservice itself, 
such that all requests are made to
the Kong Gateway which then redirects it to the springboot application.
The Kong API GUI can be accessed [here](http://localhost:8002) once the application is running.
The data for the application was generated from [Mockaroo](https://www.mockaroo.com/) and is available as a CSV at `microservice/src/main/resources/MOCK_DATA.csv`
This data automatically populates the database when the application starts.

## REQUIRED SOFTWARE
- An IDE ( [Visual Studio Code](https://code.visualstudio.com/download) or [intellij IDEA](https://www.jetbrains.com/idea/download/?source=google&medium=cpc&campaign=APAC_en_AU_IDEA_Branded&term=intellij+idea&content=602143185772&gclid=EAIaIQobChMI-f3uuYnegwMVwqRmAh0_ewXKEAAYASABEgImY_D_BwE&section=windows) )
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Postman API](https://www.postman.com/downloads/)
- A Web Browser ( [Google Chrome](https://www.google.com/chrome/) or [FireFox](https://www.mozilla.org/en-US/firefox/new/))
- [Java](https://www.oracle.com/java/technologies/downloads/) 17 at minimum installed on your local machine.
- [Git](https://www.git-scm.com/downloads)
- [Apache Maven](https://maven.apache.org/)

## Setting Up - CLONE THE PROJECT
- Open a terminal or command prompt (Powershell on Windows and Terminal or linux or macOS)
- Navigate to the directory you want to clone the project. e.g. `cd path/to/your/directory`
- Run `git clone git@github.com:msackey-IW/springboot-kong-microservice.git`
- Alternatively, in the [project repository](https://github.com/msackey-IW/springboot-kong-microservice) you can navigate to `code -> Open with GitHub Desktop` and clone the repository through GitHub Desktop.
- Run `mvn clean package` - This packages the springboot application into a .jar file.
- Run `docker-compose up` - This creates and runs the microservice from the .jar file.
- The endpoints are exposed via a kong gateway through the following url `http://localhost:8000/user/{id}`. 
- The id is field is a dynamic resource and returns all information attached to the user with the specified id.
- The database can be accessed through [PGADMIN4](http://localhost:5050). The default username and password for this service is available at `microservice/docker-compose.yaml`
- The default login credentials for the database are available in `microservice/docker-compose.yaml`

## Setting Up - DOWNLOAD THE PROJECT
- Open and run Docker on your machine.
- Download the application [here](git@github.com:msackey-IW/springboot-kong-microservice.git) by navigating to `code -> download ZIP`.
- Unzip the folder and open the extracted files in your downloaded IDE(Intellij or VSCode).
- In your IDE terminal, navigate to the eCommerce folder using the command `cd eCommerce`.
- Run `mvn clean package -DskipTests` - This packages the springboot application into a .jar file.
- Run `docker-compose up` - This creates and runs the microservice from the .jar file.
- The endpoints are exposed via a kong gateway through the following url `http://localhost:8000/user/{id}`.
- The id is field is a dynamic resource and returns all information attached to the user with the specified id.
- The database can be accessed through [PGADMIN4](http://localhost:5050). The default username and password for this service is available at `microservice/docker-compose.yaml`
- The default login credentials for the database are available in `microservice/docker-compose.yaml`

## KONG API GATEWAY
- The application comes with a preloaded kong service to retrieve user information. 
- However, more services may be attached as required. Visit the Kong [official docs](https://docs.konghq.com/gateway/latest/get-started/services-and-routes/) to learn more about creating services and routes.
- All services can be viewed at `http://localhost:8001/services`
- Routes related to a service may be viewed at `http://localhost:8001/services/YOUR-SERVICE-NAME/routes`
## TESTING THE APPLICATION
- Open Postman API
- Create a new "GET Request" by navigation to `new -> Http -> GET`.
- In the url tab, enter `http://localhost:8000/user/{id}` where the id is a dynamic integer referencing the user id to be retrieved.
- A sample request to `http://localhost:8000/user/1` will return the output below
```json
{
    "id": 1,
    "firstName": "Teodorico",
    "lastName": "Skaife d'Ingerthorpe",
    "age": 18,
    "sex": "Male"
}

```
