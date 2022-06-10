# Computer components web-application

This is a web-application written in Java at the back-end and in pure
HTML, CSS, JavaScript at the front-end. Application is server-based, 
and it uses Spring framework as core.

## Features

- PC configurator
- Catalogue browser
- Configuration saver and price totalizer
- Database administration
- Logging in, out and registration
- CSRF attacks resistance

## Screenshots

![Main page with selections](screens/a.png "Main page with selections")
![Browse page](screens/b.png "Browse page")
![Browse page window](screens/c.png "Browse page window")
![About page](screens/d.png "About page")
![Log in page](screens/e.png "Log in page")
![Registration page displaying an error](screens/f.png "Registration page displaying an error")
![Administration page](screens/g.png "Administration page")
![Administration page displaying the update dialog](screens/h.png "Administration page displaying the update dialog")
![Main page without selections](screens/i.png "Main page without selections")
![Submit order window](screens/j.png "Submit order window")
![User edit window](screens/k.png "User edit window")
![Search in action](screens/l.png "Search in action")

## Build
Execute the following command to build & generate the jar file:
```sh
gradlew bootjar
```

## Installation

This application requires Docker and docker-compose to run.
App uses port 8080 and 5432.

Download the [jar file](https://github.com/vadniks/ComputerComponentsWebApp/blob/master/cursov_templates-0.0.1-SNAPSHOT.jar), [Dockerfile](https://github.com/vadniks/ComputerComponentsWebApp/blob/master/Dockerfile) and [docker-compose file](https://github.com/vadniks/ComputerComponentsWebApp/blob/master/docker-compose.yml).

In the project root or in the directory in which 
you have placed the above-mentioned files execute 
the following command to run the application:
```sh
docker-compose build && docker-compose run
```
after these procedures the application will be available at:
```
http://localhost:8080
```
in any browser, if the launch fails first time 
restart the container, this happens because there's no existing
docker volume of the database, which is created on the first launch.
To stop the app just send the interruption signal by 
holding Ctrl and pressing C, execute this command to
remove its containers:
```sh
docker-compose down
```
