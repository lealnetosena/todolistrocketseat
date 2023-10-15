http://localhost:8080/h2-console

Driver Class:	org.h2.Driver
JDBC URL: jdbc:h2:mem:todolist
User Name: admin
Password: admin

POST http://localhost:8080/users/
{
    "username":"oi",
    "name":"leal",
    "password":"123"
}

POST http://localhost:8080/tasks/
