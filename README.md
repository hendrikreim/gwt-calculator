Note: This repository is archived. 

GWT German Numerals Calculator
==============================

Short description (taken from task description):
This is a basic calculator, where the input is given via a web interface. 
The calculation requests are handled by a web server, 
which performs the necessary operations and sends the results back to the browser. 
What makes this calculator application so particular, 
is that it understand written german numerals as well as arithmetic operators, 
on top of providing the correct answer of course.

The program supports the ‚+‘, ‚-‘, ‚*‘ and ‚/‘ operators, in both word and symbolic form, 
as well as all numbers from -9.999.999 to 9.999.999, in numeral and digit forms.

Used patterns/technics:
- Dependency Injection via GIN/Guice
- Model View Presenter with Places (HistoryManagement is also handled by the client app)
- TDD for relevant classes e.g. Calculator.java/TermParser.java

Used tools:
- Spring Source Tool Suite
- Google Web Toolkit
- Maven
- GIT

Run Example:
Deploy the war file to apache tomcat or run the project via console with: <project dir> mvn gwt:run