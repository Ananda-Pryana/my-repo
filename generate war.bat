setlocal
cd C:\zIMC\eclipse-oxygen\imc-master\ws\test
call "C:\zIMC\eclipse-oxygen\apache-maven-3.5.0\bin\mvn" clean package
xcopy /y C:\zIMC\eclipse-oxygen\imc-master\ws\test\target\demo.war C:\apache-tomcat-9.0.64\webapps\
xcopy /y C:\zIMC\eclipse-oxygen\imc-master\ws\test\target\demo.war C:\Users\Ananda\Downloads\
pause
endlocal
