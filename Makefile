cr: run clean

cb: build clean

Game.class:
	javac "src\main\java\*.java" -d ./build/ -classpath ./build/

run: Game.class
	cd build && java entry

build: Game.class
	cd ./build/ && jar cvfe ../realms.jar entry *.class
	java -jar realms.jar

clean:
	$(RM) -r ./build/
