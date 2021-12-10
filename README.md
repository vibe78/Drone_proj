Drone Application:

This is a drone application that allows client to deliver medication with the help of the drone. making drugs delivery easy
This is a CLI application designed with java.

**requirements**
-java version "1.8.0_281"
-mySQl

**DataBase Structure**
Data powered by MySql which was ran on a Docker container and was accessed locally by the drone
application. Drugs are loaded with help of the HashMap while the drone are built in the data base.
-Database Name: drone_db
-Table Name: air_d
-Table Entity: Id,Name,Serial_num,Weight
 
A client can:
-Can load a drone with medication
-Can check loaded medication
-List Of drone

The data of the drones are preloaded so Client have access to the Drone. So a client do not have acces of loading a Drone directly.
 As Well a client can laod a Drug to drone: Drug-Name,Drug-weight,Drug-code.