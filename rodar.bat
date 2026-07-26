@echo off
"C:\Program Files\Common Files\Oracle\Java\javapath\java.exe" --module-path "C:\javafx\lib" --add-modules javafx.controls,javafx.fxml -cp "bin;lib\sqlite-jdbc-3.53.2.0.jar" MainApp
pause