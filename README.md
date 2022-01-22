# spring-blog
A simple blog application created using Spring Boot, Spring Security, Spring Data JPA, JWT.


To run this application:

Install MySQL Database and create a Database name: 'ngspringblog'.
Import database schema from project db folder.

Import this application as a maven project and change application.properties db connection according to yours.

Please use postman to check API

Example:
Login:
{
    "username":"reazulhaque20",
    "password":"123456"
}
Method: POST

Response:
{
    "status": 200,
    "message": "Success",
    "username": "reazulhaque20",
    "role": "admin",
    "authenticationToken": "eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJyZWF6dWxoYXF1ZTIwIn0.LftoHiR5wzhqKRlruYtuahRSxHhLdzc_3wiXETsPSFNHcSjVTAAs29ZjMsasrOhSozmpb2b_7pZTSjLG7vUSAyj9tCF28e3Y_1glBqCiwAu_VR0lxJQxG9neSHQhDsRMvTs_i74IfFprc4k3ZtfGXD-3Lb7yDZHnAXNDcIjY1WqF5e3rKDrV-Gre3H5FXdRthLTnvHjcb6-qeivUnvl6zU-vfcqewAedjBYAvfxyl13uojclXVAK9Pyx4PbRownjtFpnaZLE3ek2VZxoYaa_o-aCLVoBeMi0BTb-KYRYsYu_e1Slrec34bkGzLLysixWu7ZH3U-MikgKg8y6DkepbQ",
    "user_Id": 1
}


authenticationToken required for every other request.