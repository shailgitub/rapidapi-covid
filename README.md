# rapidapi-covidstates

## Details to execute\test endpoint URLs with  covid19-statistics API  

### Steps for database setup: 

Database Used: MySql database, 

Database Editor: MySql Workbanch

Need to create database with name ‘covid9statistics’, no need to create any table, API will autoatically create all tables when first endpoint will be executed.

Update database username and password in aplication.properties file in below properties:

spring.datasource.username= root

spring.datasource.password= welcome123

REST client used: Postman
Code Editior used: IntelliJ IDEA

### Steps to execute code in intelliJ IDEA

Project code should be cloned through the Github URL provide

Once the code will be cloned, maven dependencies will be loading automatically

Once finished, use maven clean then compile task to build the project

Right click on Covid19statisticsApplication.java file and run as Java Application 

Once the application is up now can hit the endpint urls from an REST client like Postman

## EndPoint URLs Details

Use postman or any client to run below endpoints URL:

### EndPoint 1: http://localhost:8080/apicovid/saveallstats

URL type: GET

Database: Required in local machine, automatically will create database tables once endpoint will be hit, make sure to create database in local using MySql/MySql Workbanch with name :covid9statistics 
#### RequestBody/Header: Not required to pass through client(Postman)
 Details: This Get endpoint URL can be used to save all state’s statistics data in to database which is received/fetched as response from COVID-19 Coronavirus Statistics API
 First it will fetch data from covid19api using provided endpint url by passing authentification details X-RapidAPI-Host/X-RapidAPI-Key through header  and then will save the response to database table
#### Whenever we will call this URL, every time it will take latest data from COVID-19 Coronavirus Statistics API and will update current records available in database

### EndPoint 2: http://localhost:8080/apicovid/addstatus

URL type: POST

#### RequestBody/Header: Single record should be passed in requestbody through postman and header values should be passed not required to pass through client(Postman)
Details: URL can be used to add one custom record of covid19 status from Posmtan request body

Database: Required in local machine

RequestBody/Header: below request body can be pass(value should be changed) through client(Postman)


{

	"keyId": "Albania12",
				
        "country": "Albania12",
				
        "city": null,
				
        "province": null,
				
        "confirmed": 271825,
				
        "deaths": 3474,
				
        "recovered": 0,
				
        "lastUpdate": "2022-02-03T04:21:01.000+00:00"
    }

### EndPoint 3: http://localhost:8080/apicovid/apiallstates

URL type: GET

Database: Not required

### RequestBody/Header: Not required to pass through client(Postman)

Details: Fetch for USA for all state's statistics from covid19-statistics API

### EndPoint 4: http://localhost:8080/apicovid/total

URL type: GET

Database: Not required

### RequestBody/Header: Not required to pass through client(Postman)

Details:Fetch data for the entire world from covid19static API

### EndPoint 5: http://localhost:8080/apicovid/pagination?pageSize=4&pageNo=2
URL type: GET

Database: Required in local machine

###  RequestBody/Header: Not required to pass through client(Postman)

Details: URL will fetch data from database based on pagination stratage like page size and page No, we can modify booth neumerical values accordingly

### EndPoint 6: http://localhost:8080/apicovid/getallBwDates/2021-12-20/2022-03-03

URL type: GET

Database: Required in local machine

###  RequestBody/Header: Not required to pass through client(Postman)

Details: URL will fetch data from database based on start date an end date

### EndPoint 7: http://localhost:8080/apicovid/json2pojo

URL type: GET

Database: Not required

### RequestBody/Header: Not required to pass through client(Postman)

Details: JSON to POJO conversion for testing 

