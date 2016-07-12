# wifitrackerserver
Java backend for my wifitracker

To prepare a build:
* open a command line window at the level where env.bat is situated
* run env.bat from the command line
* cd workspace\gradle
* run gradlew eclipse
* open eclipse, select the 'workspace' directory as workspace
* import the wifitracker project: Open menu File/Import..., select General/Existing projects into Workspace

To work on the REST service accepting JSON requests from the ESP wifitracker:
* Open file RestServer.java
* right-click and select Run As .. Java application
* start a web browser and select URL http://localhost:3000/tracker/v1/ping
	-> you should see "pong!" to verify the REST server is working

To work on the REST client sending JSON to Mozilla or Google:
* Open file Submitter.java
* Edit the main method:
	* select an URL to submit to (either Google or Mozilla)
	* fill in your Google API key, input file, output file, etc.

To prepare a release:
* from the workspace\gradle directory, run gradlew distZip
* find the release in workspace\wifitracker\build\distributions
	
