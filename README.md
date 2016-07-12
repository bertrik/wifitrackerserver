# wifitrackerserver
Java backend for my wifitracker

To prepare a build:
* open a command line window at the level where env.bat is situated
* run env.bat from the command line
* cd workspace\gradle
* run gradlew eclipse
* open eclipse, select the 'workspace' directory as workspace
* import the wifitracker project: Open menu File/Import..., select General/Existing projects into Workspace

To prepare a release:
* from the workspace\gradle directory, run gradlew distZip
* find the release in workspace\wifitracker\build\distributions

