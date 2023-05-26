# TestProject

This assignment contains selenium test suit and api test suite that tests the basic functionalities on the https://qa-task.immedis.com/ 

Technologies Used: Java 11; Maven 3.9.1; JUnit 4; Selenium Server 3.2.0;

Dependencies: Selenium WebDriver (version 3.2.0) Edgedriver.exe (version EdgeDriver 113.0.1774.0); Rest Assured version 3.0.7; JSON version 20210307

Used browser: Microsoft Edge version 113.0.1774.50

Please note that it has been tested only under Edge browser version 113.0.1774.50. I added ChromeDriver version 113.0.5672.63 in drivers folder on the project. The set property should be changed for this driver. 

File organization: 
src/test/java/ApiTests/tests: Contains the Api tests; 
src/test/java/ApiTests/runners: Contains the test runner file for api tests; 
src/test/java/SeleniumTests/tests: Contains the UI selenium tests; 
src/test/java/SeleniumTests/runners: Contains the test runner file for selenium tests;  
src/main/resources/drivers: Contains the drivers;
