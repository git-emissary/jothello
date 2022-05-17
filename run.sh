export CLASSPATH=/home/runner/.m2/repository/junit/junit/4.8.2/junit-4.8.2.jar:/home/runner/.m2/repository/org/hamcrest/hamcrest/2.2
javac -classpath .:$CLASSPATH -d classes src/main/java/com/game/othello/*.java src/test/java/com/game/othello/*.java
java -classpath classes:$CLASSPATH com.game.othello.Othello