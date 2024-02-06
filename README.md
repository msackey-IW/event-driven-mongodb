# EVENT DRIVEN USER INFORMATION SERVICE

## ABOUT THE SERVICE
This repository uses event driven architecture to allow users to be added to a database. A publisher microservice fires a series of messages containing the details of an individual to the event broker(solace), after which subscriber service will pick up the data and add it to database. 

## SOLACE
Solace is an event broker that enables message-oriented middleware services that routes information between applications, devices and user interfaces. Learn more about solace and how it functions by visiting their [official docs](https://docs.solace.com/).

## REQUIRED SOFTWARE
- An IDE ( [Visual Studio Code](https://code.visualstudio.com/download) or [intellij IDEA](https://www.jetbrains.com/idea/download/?source=google&medium=cpc&campaign=APAC_en_AU_IDEA_Branded&term=intellij+idea&content=602143185772&gclid=EAIaIQobChMI-f3uuYnegwMVwqRmAh0_ewXKEAAYASABEgImY_D_BwE&section=windows) )
- [Docker](https://www.docker.com/products/docker-desktop/)
- [Postman API](https://www.postman.com/downloads/)
- A Web Browser ( [Google Chrome](https://www.google.com/chrome/) or [FireFox](https://www.mozilla.org/en-US/firefox/new/))
- [Java](https://www.oracle.com/java/technologies/downloads/) 17 at minimum installed on your local machine.
- [Git](https://www.git-scm.com/downloads)
- [Apache Maven](https://maven.apache.org/)

## SETTING UP - CLONE THE PROJECT
- Open a terminal or command prompt (Powershell on Windows and Terminal or linux or macOS)
- Navigate to the directory in which you want to clone the project as shown below
```powershell
cd path/to/your/directory
```
- Run 
```powershell
git clone git@github.com:msackey-IW/event-driven-user-service.git
```
- Alternatively, in the [project repository](https://github.com/msackey-IW/event-driven-user-service) you can navigate to `code -> Open with GitHub Desktop` and clone the repository through GitHub Desktop.
- Run the code snippet below in the terminal.
```powershell
cd event-driven-user-service
```
- Run the code snippet below in the terminal based on your operating system.
```powershell
<# Linux/MacOs #>
mvn clean package -DskipTests
OR
./mvnw clean package -DskipTests

<# Windows #>
mvn clean package -DskipTests
OR
.\mvnw clean package -DskipTests 

```
The above packages the springboot application into a .jar file.

- Run the code snippet below in your terminal.
```powershell
docker-compose up -d
```
The above creates and runs the microservice from the .jar file in detached mode.

## TESTING THE APPLICATION

- Open the solace graphical user interface using the url below.
```
http://localhost:8081/#/login
```
- The default username and password are both `admin`.
- Once logged in, click on the white tab with the `default` header. 
- In the menu of items on the left sidebar, scroll down and click the `Try Me!` tab.
- On the right hand side, under the Subscriber Header, Click on the `Connect` button.
- In the same section, enter the topic `Topic/Person/Add` in the input box currently containing the phrase `try-me` (Delete this prior to adding above).
- Click the Subscribe button.
- Heading back to the terminal used to set up the application, enter the command below.
```
mvn exec:java@exec-publisher
```
- Your terminal should display a list of individuals that have been successfully published to any listeners.
- Heading back to the Solace GUI, you should now notice a list of published users in the subscriber tab.
