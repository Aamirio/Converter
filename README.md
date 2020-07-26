Converter
------------------------------------------------------------------------------------------------------------------------
### How to open project in Intellij

1) Click on File -> New -> Project from existing sources -> double-click on pom.xml in Converter directory
------------------------------------------------------------------------------------------------------------------------
### How to run the Converter application

1) In your terminal/command console use command `mvn clean package` to create an executable jar of the application

2) Run the jar from the generated target directory with the following command
   `java -jar target/Converter-1.0-SNAPSHOT.jar`

3) The following commands can be used once the application is running:

    * To convert the numbers in a file to words provide the file path (e.g. /Users/Bob/Docs/myFile.txt) and hit Enter
    * To quit program Hit 'Q' and hit Enter
------------------------------------------------------------------------------------------------------------------------
### Design choices

This program follows the single responsibility principle along with loose coupling of key classes. These are the roles of the following 
classes: 

* `NumberExtractor` Accepts text as a String argument, extracts the number from it and returns it at words. Implements 
the `Extractor` interface which has just one method `String extract(String text)`
* `FileOutputter` Reads a file using the given filePath String argument and returns information from the file using the 
given Extractor implementation.
* `ConverterApp` Runs the application and asks the user to enter the file path. It is also responsible for passing an 
instance of `NumberExtractor` to construct a `FileOutputter` instance. If there was a further requirement to extract 
file information in a different way you would only need to create another `Extractor` implementation and construct the
`FileOutputter` instance using this new implementation, there would be no need to change any code in FileOutputter class.   