FOR /F "tokens=* USEBACKQ" %%F IN (`dir /s /b *.java`) DO (
  SET files=%%F
)
ECHO %files%

@REM Start the compilation process
javac -g -d bin %files%

@REM Run the program
java -cp bin Main