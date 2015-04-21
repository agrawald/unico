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
