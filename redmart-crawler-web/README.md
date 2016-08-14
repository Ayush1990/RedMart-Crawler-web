Assumptions has taken care :
 1. Mongo db needs to be installed in the deployed machine
 2. Mongo db user : admin, password : admin123, db_name : admin, port : 27017
 3. The application is compatiable with swagger for rest call
 4. For unit test cases in-memory mongodb is being used
 5. For UI standalone angular-js is being used

Steps :
 1. git clone https://github.com/Ayush1990/RedMart-Crawler-web
    a. cd redmart-crawler-web 
 2. mvn clean install
 3. mvn tomcat7:redeploy
 4. Endpoint http://localhost:8080/redmart-crawler-web/api/v1/product/{restServiceName}
 5. For checking the rest call in swagger :
    a. go to http://petstore.swagger.io/
    b. put http://localhost:8080/redmart-crawler-web/api/swagger.json in the textbox and click on Explore
 6. Supported restcall :
    1.http://localhost:8080/redmart-crawler-web/api/v1/product/totalBrandwisePrice
    2.http://localhost:8080/redmart-crawler-web/api/v1/product/totalMatchedCount
    3.http://localhost:8080/redmart-crawler-web/api/v1/product/totalOverpricedCount
    4.http://localhost:8080/redmart-crawler-web/api/v1/product/totalSamepricedCount
    5.http://localhost:8080/redmart-crawler-web/api/v1/product/totalUnderpricedCount
    6.http://localhost:8080/redmart-crawler-web/api/v1/product/totalUnmatchedCount
    7.http://localhost:8080/redmart-crawler-web/api/v1/product/totalCount?productType=redmart 
    8.http://localhost:8080/redmart-crawler-web/api/v1/product/totalCount?productType=competitor
    9.http://localhost:8080/redmart-crawler-web/api/v1/product/_redmartFileupload 
   10.http://localhost:8080/redmart-crawler-web/api/v1/product/_competitorFileupload
 7.open the index.html under redmart-ui folder

