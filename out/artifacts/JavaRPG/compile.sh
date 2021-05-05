rm *.zip
echo 'java -jar JavaRPG.jar' > Play.bat
echo 'java -jar JavaRPG.jar' > Play.sh
echo 'Java -jar JavaRPG.jar' > Play.command
zip JavaRPG-Windows.zip JavaRPG.jar Play.bat
zip JavaRPG-Linux.zip JavaRPG.jar Play.sh
zip JavaRPG-MacOS.zip JavaRPG.jar Play.command
rm Play.*
