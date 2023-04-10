
# Tech Stack 

Java | SpringBoot | Lombok | PostMan | MySQL | Git


## Deployment

To deploy this project copy this code in your application.properties file and give your credentials.

```bash
 #db specific properties
spring.datasource.url=jdbc:mysql://${DB_HOST:localhost}:${DB_PORT:3306}/${DB_NAME:dbname}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=${DB_USERNAME:root}
spring.datasource.password=${DB_PASSWORD:password}

#ORM s/w specific properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```


## User End Points
```
POST : /users (To register a user.)
GET : /users/{id} (To retreive a user by Id.)
PUT : /users/{id} (To update a user by Id.)
DELETE : /users/{id} (To delete a user by Id.)
GET : /analytics/users (Retrieve the total number of users.)
GET : /analytics/users/top-active (Retrieve the top 5 most active users, based on
the number of posts.)
```
## Post End Points

```
POST : /posts: Create a new post. The request should include the user_id.
GET : /posts/{id} (Retrieve a post by id)
PUT : /posts/{id} (Update a post's content by id)
DELETE : /posts/{id} (Delete a post by id.)
POST : /posts/{id}/like (Increment the like count of a post by id.)
POST : /posts/{id}/unlike (Decrement the like count of a post by id. The count
should not go below 0.)
GET : /analytics/posts (Retrieve the total number of posts.)
GET : /analytics/posts/top-liked (Retrieve the top 5 most liked posts)