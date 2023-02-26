cr: run clean

cb: build clean

Game.class:
	javac "src\main\java\core\utils\*.java" -d ./build/ -classpath ./build
	javac "src\main\java\core\components\*.java" -d ./build/ -classpath ./build
	javac "src\main\java\core\*.java" -d ./build/ -classpath ./build

run: Game.class
	cd build && java core.Main

build: Game.class
	cd ./build/ && jar cvfe ../core.jar core.Main core/*.class core/utils/*.class core/components/*.class
	java -jar project.jar

clean:
	$(RM) -r ./build
