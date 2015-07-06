###A sample service to assign salesguy to customers
This is an sample java based service which uses postgresql database service from heroku. it assigns the customer(already existing) to an available salesguy. the sales guy availability status is updated through an another API.

#####Dependencies
*   java 1.7+
*   Eclipse (Any IDE) to import the project
*   An application server (tomcat or any other)
*   Maven
   
#####Instruction to run the project
*   Download the project from github and try to import it.
*   Set an application server to run this project (preferably Tomcat)
*   Now right click on the project root folder and click on option to 'run on server'

#####APIs details
(**Note:** Both the APIs only take POST request so it good to have a rest client in browser extension to check these APIs)
1. **Customer-login**
```
http://localhost:8080/RestService/rest/customerlogin?custid=2
```
*   This API will assign a particular customer (using custid value in URL) to available sales guy by sending a message having all the details of customer(name, number, emailId) to the salesguy
*   update the status of salesguy as busy and record the start time in database
*   If all the salesguys are busy then it will put the customer in a queue and will assign it to salesguy whoever gets free
*   This API will trigger when any customer (existing customer) start the form signing process.
<br><br>
2.**Update-saslesguy-status**
```
http://localhost:8080/RestService/rest/updatesgstatus?empid=2
```
*   This api gets called when any particular salesemployee get free it will update the end time of salesguy for last attended customer and also change the availability status of the particular salesguy (using empid) in database
*   After updating the status it will also check in the queue if any customer is waiting to be assigned. if yes, then it will assign that customer to this salesguy and update the status of salesguy again.
*   (*Note: it would be really good approch if we can create a mobile app service which will track each salesemployee status and will connect to our servers to update their calling status*)

#####Database details
This service uses postgresql from heroku and currently works on 3 tables:

1. **customer table:** (Information about the registered customer)
`https://dataclips.heroku.com/krxujmcsgvuueknjcwrmeaysjgcy-customer-1`
2. **salesemployee table:** (Information about salesguy status)
`https://dataclips.heroku.com/ssatldfsodxrelnwkmxqpfdinaai-salesemployee-1`

3. **customer-assign table:** (Information about which salesguy attended to whom with start time and end time of call)
`https://dataclips.heroku.com/lihgysstscuwmzcdxlxyqahqjwbl-customerassign-1`
