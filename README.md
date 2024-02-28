# EVENT DRIVEN USER INFORMATION SERVICE

## ABOUT THE SERVICE
This repository uses event driven architecture to allow users to be added to a MongoDB - a document based database. A publisher microservice fires a series of messages containing the details of an individual to the event broker(solace), after which subscriber service will pick up the data and add it to MongoDB. 

## SOLACE
Solace is an event broker that enables message-oriented middleware services that routes information between applications, devices and user interfaces. Learn more about solace and how it functions by visiting their [official docs](https://docs.solace.com/).

## REQUIRED SOFTWARE
- An IDE ( [Visual Studio Code](https://code.visualstudio.com/download) or [intellij IDEA](https://www.jetbrains.com/idea/download/?source=google&medium=cpc&campaign=APAC_en_AU_IDEA_Branded&term=intellij+idea&content=602143185772&gclid=EAIaIQobChMI-f3uuYnegwMVwqRmAh0_ewXKEAAYASABEgImY_D_BwE&section=windows) )
- [Docker](https://www.docker.com/products/docker-desktop/)- Version 4.26.1 or lower
- [Postman API](https://www.postman.com/downloads/)
- A Web Browser ( [Google Chrome](https://www.google.com/chrome/) or [FireFox](https://www.mozilla.org/en-US/firefox/new/))
- [Java](https://www.oracle.com/java/technologies/downloads/) 17 at minimum installed on your local machine.
- [Git](https://www.git-scm.com/downloads)
- [Apache Maven](https://maven.apache.org/)

## SETTING UP - CLONE THE PROJECT
- Ensure you have Docker up and running.
- Open a terminal or command prompt (Powershell on Windows and Terminal or linux or macOS)
- Navigate to the directory in which you want to clone the project as shown below
```powershell
cd path/to/your/directory
```
- Run 
```powershell
git clone git@github.com:msackey-IW/event-driven-web-user-service.git
```
- Alternatively, in the [project repository](https://github.com/msackey-IW/event-driven-web-user-service) you can navigate to `code -> Open with GitHub Desktop` and clone the repository through GitHub Desktop.
- Run the code snippet below in the terminal.
```powershell
cd event-driven-web-user-service
```
- Run the code snippet below in your terminal.
```powershell
docker-compose up -d
```
- The above command creates 2 event driven microservices(publisher and subscriber), solace, a postgeSQL database and pgAdmin to access the postgres db. 
- The publisher fires a list of user details to the topic `Q/Users/Add` to which the consumer subscribes.
- The subscriber then takes all the data and persists it in the posgresSQL database.
- Due to the size fo the images, the application may take some time to get up and running. Allow for up to 10 minutes.

## TESTING THE APPLICATION
- Open POstman API
- Navigate to  `menu -> file -> import`
- Upload the JSON file in the postman folder in the main directory.
- Open and `Add Person To Database` request and press send.
- The above publishes a post request to  add a person to the database which the subscriber can access.
- Open the MongoDB graphical user interface by navigating to the url below in your web browser.
```
http://localhost:8081
```
- Login using the credentials below.
```json
{
    "username/email": "admin",
    "password": "example"
}
```

- Once logged in, click the `users` button.
- Click the `view` button.
- You should now have access to all the users added to the database.
- Run the command below to close down all running docker containers.
  ```bash
  docker-compose down
  ```

