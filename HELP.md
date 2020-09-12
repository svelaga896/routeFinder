# Getting Started

#RouteFinder Project: 
#Description: This project will return is there any route exists between two given cities

In a nutshell:

1. RouteFinder project will read the route information from a static text file (cities.txt), which is  placed under resouces folder.
   RouteController -> Service-> Loader(This is where actual loading of the text file is happening) 

2. Using rest end point with Get method, By passing city1 and city2 as query String params, route exists or not is validated against the text file data.


...Detailed documentation about  project, installation, etc will goes here..


#How to use the project:
Download the project from Git Repo, this is a springboot maven project and run the "RouteApplication" as a java project.

Jacococ plugin in enabled, if it runs with code coverage complete report can be downloaded /target/sites/....


... More can be added here.