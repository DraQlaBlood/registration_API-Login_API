# registration_API-Login_API
Users registration with email verification 

this API use:

Java 
Sping Boot
Spring Mail To send email to user
MongoDB as dbase
Feign for communications via HTTP with other  Microservices
Swagger2

From Postman, use the endpoint : "localhost:2001/users/registration" to register user 
with:
  "pseudo":   type (String) 
	"email":    type (String) 
	"password": type (String) 
	"contact":  type (String) 
  
  Process:
  After the POST method , user is saved in DB, user account is enabled(false);
  User receive an mail to verify their email(email contains a verification token)
  Once user click on the link the token is verified and the server set user.enabled to TRUE 
  From here user will be able to proceed with login.
  
  Note: This API does not contains Authentication process .I will create another API for User Login
  This API requires the Location API to provide user account Location (IP address, city, country, Lat and Longitude)
  
  Get Location API from : git clone https://github.com/DraQlaBlood/Mars_Backend_Utils_API.git
  This repository might contain other related back-end processes APIs
