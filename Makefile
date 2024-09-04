SRC_DIR = src
BIN_DIR = bin

SOURCES := $(shell find $(SRC_DIR) -name '*.java')

compile:
	@mkdir -p $(BIN_DIR)
	javac -d $(BIN_DIR) $(SOURCES)

run: compile
	java -cp $(BIN_DIR) App

clean:
	rm -rf $(BIN_DIR)/*
