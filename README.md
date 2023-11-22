# sample-company

## Steps to Run 
``` 
gradle clean build 
docker compose up -d 
java -jar build/libs/sample-company-0.0.1-SNAPSHOT.jar
```

### Run tests
````
gradle test
````

### Database
````
http://localhost:8180/?pgsql=postgres&username=myuser&db=mydatabase&ns=sample_company&select=computer
````

### Swagger UI

```
User: test
Password: test

http://localhost:8091/swagger-ui/index.html
```

### Sample Requests (Requires Basic Authentication)

#### Get All Computers
````
curl --location 'localhost:8091/computers' \
--header 'Authorization: Basic dGVzdDp0ZXN0' \
--header 'Cookie: JSESSIONID=0FE8E77AD2A7264856304B6BF5367E18'
````

#### Get Computer By ID
````
curl --location 'localhost:8091/computers/2004' \
--header 'Authorization: Basic dGVzdDp0ZXN0' \
--header 'Cookie: JSESSIONID=0FE8E77AD2A7264856304B6BF5367E18'
````

#### Get Computers for Employee by Employee ID
````
curl --location 'localhost:8091/employees/1000' \
--header 'Authorization: Basic dGVzdDp0ZXN0' \
--header 'Cookie: JSESSIONID=0FE8E77AD2A7264856304B6BF5367E18'
````

#### Delete Computer
````
curl --location --request DELETE 'localhost:8091/computers/2004' \
--header 'Authorization: Basic dGVzdDp0ZXN0' \
--header 'Cookie: JSESSIONID=0FE8E77AD2A7264856304B6BF5367E18'
````

#### Assign Computer to employee

````
curl --location --request PATCH 'localhost:8091/computers/2002' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dGVzdDp0ZXN0' \
--header 'Cookie: JSESSIONID=0FE8E77AD2A7264856304B6BF5367E18' \
--data '{
    "employee_id": 1003
}'
````

#### Remove Computer of employee

````
curl --location --request PATCH 'localhost:8091/computers/2002' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dGVzdDp0ZXN0' \
--header 'Cookie: JSESSIONID=0FE8E77AD2A7264856304B6BF5367E18' \
--data '{
    "employee_id": null
}'
````

#### Create new Computer

````

curl --location 'localhost:8091/computers' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dGVzdDp0ZXN0' \
--header 'Cookie: JSESSIONID=0FE8E77AD2A7264856304B6BF5367E18' \
--data '{
    "computer_name": "DEV2008", 
    "description": "Development Machine", 
    "ip_address": "172.80.79.269", 
    "mac_address":"AA:12334:OOPCF:5668",
    "employee_id": 1000
}'
````

#### Update Computer Details

````
curl --location --request PUT 'localhost:8091/computers/2002' \
--header 'Content-Type: application/json' \
--header 'Authorization: Basic dGVzdDp0ZXN0' \
--header 'Cookie: JSESSIONID=0FE8E77AD2A7264856304B6BF5367E18' \
--data '{
    "mac_address": "AA:12334:OOPCF:5658",
    "computer_name": "BUS2002",
    "ip_address": "172.80.79.257",
    "employee_abbreviation": "mmu",
    "description": "TESTING Machine"
}'
````