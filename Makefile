# Reference: https://www.cs.swarthmore.edu/~newhall/unixhelp/javamakefiles.html

# Clear default targets for building .class from .java if any.
.SUFFIXES: .java .class

# Target for creating .class from .java
.java.class:
	javac $*.java

# Our .java files
JAVAS = Tile.java Hand.java Shuffler.java Board.java Player.java AI.java comGUI.java FrameTest.java

default: classes

# .class files by replacing .java in $(JAVAS)
classes: $(JAVAS:.java=.class)

clean:
	rm -f *.class
