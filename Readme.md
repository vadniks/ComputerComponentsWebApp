# Computer components web-application

This is aweb-application written in Java at the back-end and in pure
HTML, CSS, JavaScript at the front-end. Application is server-based 
and it uses Spring framework as core.

## Features

- PC configurator
- Catalogue browser
- Configuration saver and price totalizer
- Database administraion
- Logging in, out and registration
- CSRF attacks resistance

## Installation

Dillinger requires Docker and docker-compose to run.
App uses port 8080 and 5432.

Download the [jar file](https://github.com/vadniks/ComputerComponentsWebApp/blob/master/cursov_templates-0.0.1-SNAPSHOT.jar), [Dockerfile](https://github.com/vadniks/ComputerComponentsWebApp/blob/master/Dockerfile) and [docker-compose file](https://github.com/vadniks/ComputerComponentsWebApp/blob/master/docker-compose.yml).

In the project root or in the directory in which 
you have placed the above mentioned files execute 
the following command to run the application:
```sh
docker-compose build && docker-compose run
```
and this command to stop it:
```sh
docker-compose down
```
after these procedures the application will be available at:
```
http://localhost:8080
```
in any browser.
