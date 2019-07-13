
You have to import one project. Import all others like `File` -> `New Module from Existing Sources`.

---

## Eureka client dashboard

After starting all services, navigate to 

    http://localhost:8761/

## Services 

For accessing `movie-catalog-service` you have to call:

    http://localhost:8085/catalog/foo
    
User id can be any

Also, other services are available:

    http://localhost:8087/ratingsdata/movies/100
    
    http://localhost:8087/ratingsdata/user/1
    
    http://localhost:8086/movies/100
    
## Hystrix Dashboard

For viewing it open

    http://localhost:8085/hystrix
    
Enter URL for monitoring
    
    http://localhost:8085/actuator/hystrix.stream        