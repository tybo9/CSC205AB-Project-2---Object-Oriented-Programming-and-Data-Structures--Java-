CSC205AB
Program2


You are to write a Breakout game, using Java applets for animation.   The requirements for this program are designed so that you will also use an ArrayList, Generics, an abstract class, polymorphism, and file I/O.  Please read the following instructions very carefully, as they attempt to explain how the various parts of the animation work; then you can update the applet, using your knowledge of ArrayLists, Generics, abstract classes, and polymorphism. 

Existing classes:   There are a number of existing classes that are already written and ready for use.  They are contained in the file Breakout.java (they are not defined as public so they don’t have to be in a file that matches their name).  Please look them over carefully to see what functionality is available.  The classes (implemented inside Breakout.java) are:

•	Ball:  this is defined as a subclass of Rectangle (even though that is geometrically invalid!).   The reason for this is so Ball can inherit data for its x coordinate, y coordinate, width, and height.  It also adds some data of its own (see the code) and methods (among others) to:
o	bounceLeftRight()  (which changes the angle at which it moves after bouncing off a left or right side),
o	bounceTopBottom()   (which changes the angle at which it moves after bouncing off a top or bottom) 
o	move()  (change its coordinates), 
o	freeze()/unfreeze()    itself, 
It also inherits important methods from the Rectangle class
o	translate – changes its x and y coordinates respectively by the 2 numbers that are passed in
o	intersects(Rectangle other) – returns true if it intersects another Rectangle 
o	intersection(Rectangle other) – returns the Rectangle that is the intersection

•	Paddle: this is also defined as a subclass of Rectangle so it inherits the same data/methods that are described above for the Ball class.  The fact that Ball and Rectangle are both Rectangles means that code can ask a Ball if it intersects a Paddle, so it can easily tell when to make things happen.

•	Wall: this is not in its own file, but rather in the Breakout.java file.  Wall is also a subclass of Rectangle to it is easy to determine if the ball has hit a wall.  

•	The Breakout class (which is an Applet):  if you look at the code for the Breakout class, you will notice it has a number of things:
 
1.	It is a subclass of Applet, so it inherits data and methods as well as the ability to function as an Applet.  For example, if you want to know the height of the applet you can ask it through the    this.getHeight();    method.
2.	It implements the KeyListener interface, which gives it the ability to “listen” for keystrokes from the keyboard.  If you were to look up the KeyListener interface in the documentation, you would find that anything that implements it must have the KeyPressed, KeyReleased, and KeyTyped methods.  These are all there; KeyPressed and KeyReleased set/unset Boolean flags which remember that the left or right arrow keys are or are not being pressed.
3.	It has a method called init.   For an Applet, the init method is called once when the Applet is created and it is used to initialize things.  In this sense, it works like a constructor.   In the init method, instances of 3 other classes are added to the Applet:
•	A KeyListener is added, but since it is a KeyListener itself, it defines itself to the the KeyListener
•	A timer is also created, which is “set” to go off periodically and will set off an ActionListener method.  There is another class defined in the Breakout.java file called BounceActionListener, which implements ActionListener.  Its required method (actionPerformed) will simply tell the Applet to repaint itself, thus providing the timing for the animation.
•	A MouseListener is added that will listen for mouse clicks.  To be different, I defined a new class (in the same Breakout.java file) called BounceMouseListener.   If you find it and look at it, you will see that it implements the MouseListener interface, which requires 5 methods and allows it to listen for mouse events.  All the methods are there but the only one that is actually used is mouseClicked, which listens for when a button is clicked.
4.	It has a method called update, which is actually called by paint and redraws the whole Applet.  It receives a Graphics object from whatever browser is calling and controlling the Applet; the Graphics object is like a “pen” in that it has the ability to draw specific things and also change its color to whatever is passed in.  The update methods has code to use “double buffering” in which an image is create (with its own Graphics object), everything is written to the image, and then the whole image is written to the Applet.  This eliminates flickering.  In the update method, various components are told to move and to draw themselves (passing a Graphics object).  This is also where we check if the ball intersects other things.   See the Java documentation for the methods a Graphics object can use to draw things. 

What you need to code:
1.	Create an abstract class called DrawableBrick, which should be a subclass of Rectangle (so it also inherits the same data and methods defined above in the description of Ball; these will be important).   Also make it have an additional data item to store points (this will be used by its subclasses).  There should be a getPoints() method which returns the points.  Finally, make it have an abstract method called draw(Graphics g), which will have to be implemented by any class which is a subclass of DrawableBrick.
2.	The relationship between the classes that implement your bricks is shown below.   See Create 4 subclasses of DrawableBrick.  The details are listed below.


has data:		has methods: 
	x, y, width, height (ints)  	boolean intersects(Rectangle r)
	Rectangle intersection(Rectangle r)



	has data:	has method:				       color (a Color)	    getPoints()
	points (an int)	     
	  	has abstract method:
		    draw(Graphics g);








				(  all implement their own method:     draw(Graphics g)  )


a)	The RedBrick class is given as an example.   Change it so it is a subclass of DrawableBrick and has no data (all will be inherited).  Its constructor should only accept ints for  x, y, width, and height (not points).   Its constructor should also “hard-code” the number of points that it is worth.  Its draw method draws it using the Graphics object that it receives.   You should take out the diagnonal line that is in the example one.

b)	The XBrick class should be set up like the RedBrick class, except that when it draws itself, it draws a white rectangle with diagonal lines in addition to the outline around the edge.   You can “hard-code” a different amount of points for it.

c)	The StripeBrick class should be set up like the RedBrick class, except that when it draws itself, it draws a white rectangle with some horizontal “stripes” in addition to the outline around the edge.   You can “hard-code” a different amount of points for it.

d)	The OvalBrick should be set up like the RedBrick class, except that when it draws itself, it draws 2 ovals inside itself.  These ovals can be any color you want.  Check the documentation for the Graphics class to tell a Graphics object how to draw a filled oval.  You can “hard-code” a different amount of points for it.

See the image below for what the different bricks look like.

3.	In Breakout’s data, declare an ArrayList that will hold DrawableBricks. 
4.	In Breakout’s init() method, actually create the ArrayList that will hold DrawableBricks.  Then open a file called bricklist.txt (OK to hardcode the name, but make sure it is correct).   The file might look something like what is shown below (FOR EXAMPLE – this is the file that would produce the game shown above.).

2
3
OvalBrick
XBrick
OvalBrick
XBrick
RedBrick
StripeBrick

This file is telling you to have 2 rows, each with 3 columns.  After the numbers should be 2x3 = 6 lines, each with a type of DrawableBrick.  Create each type of DrawableBrick, carefully calculating the x, y, width, and height.  Put it into the ArrayList that you created.

(You should experiment with changing bricklist.txt to have different number and types of bricks…)
The rows should exactly fit across the usable space (not including the walls).  Their height can be any number you want.   The gap at the top should be at least 3 times the ball diameter.

NOTE:  You can read the name of the brick type with a .next().   If you decide to use .nextLine(), remember what happens when you try to read an int ( using nextInt() )and then a line ( using nextLine() ). 

5.	In Breakout’s update() method, you will have to provide the actual logic that will make it all work.  After the ball is told to move, there is existing code which checks to see if the ball hit a wall or a paddle.  You have to add code to check if the ball it any one of the various bricks that are in your ArrayList – if so, tell the ball to bounce (correctly – either a “side bounce” or a “top/bottom bounce”), update the number of points (see the comment in the Breakout.java update() code), and tell the ArrayList to remove that particular brick.

Remember:
•	When you are calculating coordinates, the x coordinate is the distance from the left and the y coordinate is the distance from the top.
•	The x, y, width, and height data fields are all inherited from Rectangle.  Do not redefined them; if you do, the intersects() method will probably not work correctly.
•	Since Breakout is a subclass of Applet, it has access to all of the methods of an Applet.  That is how it can get its width and its height.


Comments and formatting:   It is very important that you organize your code to use comments, constants (when feasible) and correct indenting/whitespace.  Please make your variable names meaningful and use Java conventions for choosing their names (start with lower case).


Extra Credit:   You can earn up to 15 pts. in extra credit by enhancing your game in some way. 

•	Please submit your extra credit into a separate assignment called BreakoutEC

•	If you add an enhancement, please submit a .txt file that explains you enhancements so I know what to look for.   Some suggestions:
o	make the ball bounce slightly differently if it hits the middle of the paddle as opposed to the edge of the paddle
o	add sounds
o	put in more than one ball
o	subtract points if the ball misses the paddle
o	make the whole game resizable
o	create your own type of Exception and throw it if the brickfile.txt lists an unimplemented type of DrawableBrick
o	create different types of DrawableBricks.   (see below)

•	If you create new types of DrawableBricks, please name them ExtraBrick1, ExtraBrick2, and ExtraBrick3 (so I can create a test generator that knows what to expect).   Different types of DrawableBricks might hold an image or do a “gradient paint” (Google this, including the Java keyword).  



Handing in your program:   Be sure that your program compiles and runs – even if it does not use FileIO or all of the DrawableBrick types.   

Please submit all of your files via Canvas.   The testing program will automatically substitute default files if any of yours are not implemented/submitted, so do not “forget” to include them as there will be a penalty for any missing components.   The expected files are:

DrawableBrick.java
RedBrick.java    (updated)
XBrick.java, 
StripeBrick.java,
OvalBrick.java
ExtraBrick1.java        (if you want)
ExtraBrick2.java        (if you want)
ExtraBrick3.java        (if you want)

Breakout.java    (updated)
enhancements.txt
(anything else you want to submit)
