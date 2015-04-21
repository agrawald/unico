#RESTful and SOAP web service 

The application is a enterprise Java application that implements RESTful and SOAP web services.

The RESTful service will expose two methods:
```
public String push(int i1, int i2);
```
which returns the status of the request to the caller as a String. The two parameters will be added to a JMS queue.
```
public List<Integer> list();
```
which returns a list of all the elements ever added to the queue in the order added as a JSON structure. 

The SOAP service will expose the following method as operations:
```
public int gcd();
```
which returns the greatest common divisor* of the two integers at the head of the queue. These two elements will subsequently be discarded from the queue and the head replaced by the next two in line.
```
public List<Integer> gcdList();
```
which returns a list of all the computed greatest common divisors. 
```
public int gcdSum();
```
which returns the sum of all computed greatest common.

#How to Execute

##Add an Application User
This quickstart uses secured management interfaces and requires that you create the following application user to access the running application.
```
UserName: quickstartUser	
Realm: ApplicationRealm	
Password: quickstartPwd!!	
Roles: guest
```
To add the application user, open a command prompt and type the following command:

    For Linux:   $EAP_HOME/bin/add-user.sh -a -u 'quickstartUser' -p 'quickstartPwd1!' -g 'guest'
    For Windows: $EAP_HOME\bin\add-user.bat  -a -u 'quickstartUser' -p 'quickstartPwd1!' -g 'guest'

##Configure the JBoss EAP Server
You configure the the JMS test queue by running JBoss CLI commands. For your convenience, this quickstart batches the commands into a configure-jms.cli script provided in the root directory of this quickstart.

Before you begin, back up your server configuration file
If it is running, stop the JBoss EAP server.
```
Backup the file: $EAP_HOME/standalone/configuration/standalone-full.xml
```
After you have completed testing this quickstart, you can replace this file to restore the server to its original configuration.

Start the JBoss EAP server by typing the following:
```
 For Linux:  $EAP_HOME/bin/standalone.sh -c standalone-full.xml
 For Windows:  $EAP_HOME\bin\standalone.bat -c standalone-full.xml
```
Review the configure-jms.cli file in the root of this quickstart directory. This script adds the test queue to the messaging subsystem in the server configuration file.

Open a new command prompt, navigate to the root directory of this quickstart, and run the following command, replacing EAP_HOME with the path to your server:
```
 For Linux: $EAP_HOME/bin/jboss-cli.sh --connect --file=configure-jms.cli 
 For Windows: $EAP_HOME\bin\jboss-cli.bat --connect --file=configure-jms.cli  
 ```
You should see the following result when you run the script:
```
 The batch executed successfully.
 {"outcome" => "success"}
```
##Start the JBoss EAP Server with the Full Profile

Open a command prompt and navigate to the root of the JBoss EAP directory.
The following shows the command line to start the server with the full profile:
```
 For Linux:   $EAP_HOME/bin/standalone.sh -c standalone-full.xml
 For Windows: $EAP_HOME\bin\standalone.bat -c standalone-full.xml
```
##Build and Execute
You can execute the application using the below maven command, however before this EAP_HOME should be set
```
mvn clean install jboss-as:deploy
```

##Test Cases
A SOAP UI Test suite has been provided and located at the following location

```
rest-soap-ws/src/test/soapui/REST-Project-1-soapui-project.xml
```

The Soap UI test suite contains the unit test cases for REST as well as SOAP WS calls. A load test is also provided (under REST SOAP Test Suite: Push->GCD) to test the concurrency for 20 users simultaneously.

##Assumptions
* As per the requirement REST would act as the producer of the message in JMS, whereas SOAP WS would be the consumer of the JMS message
* The JMS message produced (via REST -> push) and consumption (via SOAP -> gcd) would happen only when we send the appropiate request. No consumption or production of the message would happen automatically
* Considering the database to save JMS messages si not provided, due to which the JMS message is not saved in case of JMS queue failure. 
* Both REST and SOAP WS calls would be executed seperately on different machines by different users. 
* Also, as the database is not available, the JMS message produced and JMS message consumed would be kept in a static list on there respective classes.
* To consume the messages from the queue, we are waiting for 5000 ms after which the consumer would throw a JMSException with error code ERR101. If no message is found to be consumed. 
