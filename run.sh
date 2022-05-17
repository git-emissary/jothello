javac -classpath .:$CLASSPATH -d classes src/main/java/com/game/othello/*.java src/test/java/com/game/othello/*.java
java -classpath classes:$CLASSPATH com.game.othello.Othello