# Online Payment Service.

Payments is Restful API service which provides REST services like register,credit,debit,transfer etc. 
The documentation for the services is provided below.



Steps to follow to run the service:
1. Download "target/executable.jar" jar file to your machine
2. Run "java -jar executable.jar"
The service comes up and the tomcat server starts on 8080 port.


Rest API's available in the service:

1. Register the account:
    Request URL: "http://localhost:8080/tutorial/rest/payments/register"
    Request Method: POST
    Content-Type: application/json
    Authorization: Not Needed
    Payload format: 
      {
        "userId": "3",
        "name": "sai12345",
        "balance": "400"
      }
2. Get the Account details given account id:
    Request URL: "http://localhost:8080/tutorial/rest/payments/accountdetails/{AccountId}"
    Request Method: GET
    Content-Type: NA
    Authorization: Not Needed
    Payload: NA
3. Get all the Accounts registers:
    Request URL: "http://localhost:8080/tutorial/rest/payments/existingusers"
    Request Method: GET
    Content-Type: NA
    Authorization: Not Needed
    Payload: NA
4. Credit into particular Account given account id and amount:
    Request URL: "http://localhost:8080/tutorial/rest/payments/credit/{AccountId}"
    Request Method: POST
    Content-Type: application/json
    Authorization: Not Needed
    Payload format: 
      {
        "amount":"200"
      }
5. Debit from particular Account given account id and amount:  
    Request URL: "http://localhost:8080/tutorial/rest/payments/debit/{AccountId}"
    Request Method: POST
    Content-Type: application/json
    Authorization: Not Needed
    Payload format: 
      {
        "amount":"200"
      }
6. Deregister a particular Account:
    Request URL: "http://localhost:8080/tutorial/rest/payments/delete/{AccountId}"
    Request Method: GET
    Content-Type: NA
    Authorization: Not Needed
    Payload: NA
7. Dergister all the Accounts:
    Request URL: "http://localhost:8080/tutorial/rest/payments/deleteall"
    Request Method: GET
    Content-Type: NA
    Authorization: Not Needed
    Payload: NA
    
8. Transfer money from one Account to another: 
    Request URL: "http://localhost:8080/tutorial/rest/payments/debit/{AccountId}"
    Request Method: POST
    Content-Type: application/json
    Authorization: Not Needed
    Payload format: 
      {
        "fromAcc": "1",
        "toAcc": "3",
        "amount": "200"
      }
      
      
 Additional details for the project are provided in wiki:
 https://github.com/skosgi242/payments.wiki.git
