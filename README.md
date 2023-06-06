# Overview 
The Nisum-api api provide the endpoint for create, retrive one and all users.

# Installation/Usage
The examples in this repository offer sample REST API calls, including valid http methods, headers, and body content, where applicable. 
Sample responses are also included. Requests are submitted by standard REST call methods: Postman, application code, etc.

# API EndPoint

- CREATE USER POST http://localhost:8080/api/user/create

- GET ALL USERS GET http://localhost:8080/api/user/all

- GET A SPECIFIC USER GET {http://localhost:8080/api/user/{id}

# JSON FORMAT 
Create a user need a json format, it's the next: 
{
	"name": "Juan Rodriguez",
	"email": "juan@rodriguez.org",
	"password": "hunter2",
	"phones": [
		{
			"number": "1234567",
			"citycode": "1",
			"contrycode": "57"
		}
	]
}

