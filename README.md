# dev-bdd-functional-testing

## cucumber-spring-integration
#### Spring Boot Application integrated with Cucumber Test Cases(Behavior Driven Development) and Unit Test Cases (Test Driven Development)

## Introduction 

- Behavior Driven Development (BDD) is a **software engineering practice** that promotes collaboration between 3 Amigos(Dev, QA, PO/BA) to specify the behavior of a system using a ubiquitous language.

- Test Driven Development (TDD) is a **software development approach** in which test cases are developed first to specify and validate what the code will do. 

- Unit Testing is a type of **software testing** where individual units or components of the system are automatically tested.




In this sample microservice, we show you how to write [cucumber](https://cucumber.io) test cases for spring boot applications that use Behavior Driven Development, along with unit tests that use [Junit](https://junit.org/junit5/docs/current/user-guide/) & [Mockito](https://www.vogella.com/tutorials/Mockito/article.html).

## Development Steps : 

## <b>Step 1</b> : Dependencies

Include the following dependencies in your pom.xml file to enable Spring with Cucumber 

```xml
 <properties>		
	<cucumber.version>7.2.0</cucumber.version>
 </properties>

   <dependencies>
	   <dependency>
		<groupId>io.cucumber</groupId>
		<artifactId>cucumber-java</artifactId>
		<version>${cucumber.version}</version>
		<scope>test</scope>
	   </dependency>
	   <dependency>
	        <groupId>io.cucumber</groupId>
	        <artifactId>cucumber-junit</artifactId>
	        <version>${cucumber.version}</version>
	        <scope>test</scope>
	    </dependency>
	    <dependency>
	        <groupId>io.cucumber</groupId>
	        <artifactId>cucumber-spring</artifactId>
	        <version>${cucumber.version}</version>
	        <scope>test</scope>
	   </dependency>
    <dependencies>
```
## <b>Step 1.1</b> : For Cucumber Report Generation, add the plugin below
```xml
<plugin>
	<groupId>net.masterthought</groupId>
	<artifactId>maven-cucumber-reporting</artifactId>
	<version>5.4.0</version>
	<executions>
		<execution>
			<id>execution</id>
			<!-- report generation is happening at this phase, so dont change -->
			<phase>verify</phase>
			<goals>
				<goal>generate</goal>
			</goals>
			<configuration>
					<!-- Project name which will be displayed at the top of the report -->
				<projectName>dev-bdd-functional-testing Reports</projectName>
					<!-- set this to "true" to bypass generation of Cucumber Reports entirely, defaults to false if not specified. -->
				<skip>false</skip>
					<!-- Path of the directory where reports will be generated -->
				<outputDirectory>${project.build.directory}/cucumber-reports</outputDirectory>
					<!-- optional, defaults to outputDirectory if not specified -->
				<inputDirectory>${project.build.directory}/cucumber-reports</inputDirectory>
				<jsonFiles>
					<!-- supports wildcard or name pattern -->
					<param>**/*.json</param>
				</jsonFiles>
					<!-- optional, set true to fail build on test failures -->
				<checkBuildResult>false</checkBuildResult>
			</configuration>
		</execution>
	</executions>
</plugin>

```

## <b>Step 2</b> : Define the Cucumber Runner Test

```java
@RunWith(Cucumber.class)
@CucumberOptions(features = { "src/test/resources/features"},  
				    plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
				    			 "junit:target/cucumber-reports/Cucumber.xml",
				    		         "html:target/cucumber-reports/Cucumber.html"},
publish=true)
public class AcceptanceTestSuite {
}
```

## <b>Step 3</b> : Feature files should be created under the src/test/resources folder
- In Cucumber, you'll use Feature Files to describe your tests in Descriptive language (like English). It serves as both an automation test script and as a living document. A feature file can contain one scenario or many scenarios, but it usually contains a list of scenarios. 
- Right Click on the resources folder and create a new file. Cucumber recognizes the stories (or features, as they're called in Cucumber) automatically if the files have the 'feature' extension.



```feature
Feature: 
```

## <b>Step 4</b> : StepDefinitions
- Right Click on your feature file and Run as Cucumber Feature. 
- In the console, window, cucumber has suggested that you implement these missing methods so that the Steps specified in the Feature file can be traced to Java methods, which can be executed when executing the feature file.
- Create a new package under the src/test/java for step definition files and add the missing methods generated above by creating the new class.


### UserStepDefinitionTest.java : 
```java



```
## <b>Step 5</b> : Source Code
- When you run the feature file you see the errors on the console. Write the minimal production code to make the test passes.

## <b>Step 6</b> : Run the build and check the output under target folder

```java
mvn clean install
 
```



