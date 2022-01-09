# School Manager Application

### Program
The task of the program is to manage students.  
BaseURL: `/school`

### Entities:
* Student

### Student
Attributes:
* First name (required, max 50 letter)
* Last name (required, max 50 letter)
* Age (number)


| Http method | Endpoint                        | Description                         |
| ------------ | ---------------------------    | ----------------------------------- |
| GET          | `"/api/students"`               | get all student                     |
| GET          | `"/api/students/{id}"`          | get student by id                   |
| GET          | `"/api/students?{name}&{age}"`  | get students by fragment of name, and/or age|
| POST         | `"/api/students"`               | create student                      |
| PUT          | `"/api/students/{id}"`          | update student by id                |
| DELETE       | `"/api/students/{id}"`          | delete student by id                |
|              | `"/actuator/health"`            | health check                |
|              | `"/swagger-ui/index.html"`      | swagger UI                |

The authentication is commented out in pom.xml and AppSecurityConfig.java, because
Im confused after a time, while Im trying to passed a test with active authentication.
So I commanted out instead. This way the tests are passed. The other way(uncomment authentication)
 are the authentication working, everything authenticated, except actuator/health, this one 
I leave public access.  
Im sorry for this, but unfortunately we dont learn the security on course, 
like the versioning neither.  
Im working the security only the quiz-game hobby project, where I need to secure only 
the admin page, where user can manage the database.  
I didnt even start the versioning, just read about it, and its not clear to me, what is 
the good way:
 - URI
 - media type
 - @Projection