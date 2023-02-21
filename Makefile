cr: run clean

cb: build clean

Game.class:
	javac "src\main\java\project\*.java" -d ./build/ -classpath ./build

run: Game.class
	cd build && java project.main

build: Game.class
	cd ./build/ && jar cvfe ../project.jar project.main project/*.class
	java -jar project.jar

clean:
	$(RM) -r ./build
