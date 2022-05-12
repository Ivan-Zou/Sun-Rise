=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: ivanzou
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. (Collections): I use collections to implement the current clouds on the game screen. I use
  LinkedLists to store the current cloud object on the game screen. LinkedList is appropriate here because
  the clouds always moves down and will always eventually move off of the screen, so whenever they are outside
  the screen I want to remove it from the LinkedList. Also, I generate clouds that needs to be added
  to the LinkedList so the game is continuous/endless. The removal and addition of clouds to the
  LinkedList also happens in the middle of the game. The size of the LinkedList is constantly changing
  during the game so 2d arrays or arrays in general would not be appropriate here. Also, I can have
  the same type of clouds on the screen at the same time and LinkedLists allows duplicates. Also,
  it is easy to remove and add elements to the ends of the LinkedList which is good for me because
  I always remove the first element since the new clouds will be added to the end of the list, and
  the order of the LinkedList never changes so as each cloud (the first cloud element of the Linked
  List) moves off the screen I will remove it.

  2. (Inheritance and Subtyping): I use Subtyping by creating an abstract parent class of Cloud, which
  will be extended by the three child class clouds, NormalCloud, PointCloud, and ShrinkSunCloud. The
  abstract parent class Cloud have variables (x, y, width, height, direction, and color) and
  methods (move(), detectCollision(), detectPassing(), isOffScreen(), addPassingPoint(), and draw())
  that the three child class clouds all use but do not need to declare or implement. Also, the
  abstract parent class Cloud has an abstract method (ability()) that the three child class clouds
  need to Override and implement. An abstract method is appropriate for ability() since each of the
  child class clouds have different abilities that does different things, so their implementations
  are different. The abstract method will be Overridden in the child classes using dynamic dispatch.

  3. (JUnit Testable Component): I use JUnit Testing to test the different methods across my different
  classes. I created methods that return values such as ints and booleans, and I tested if those
  methods returned the correct values. Once I passed the test of the methods that return a value, I
  then create tests for my methods that are void and performs a certain action. I would call the
  void method in the test and then used my tested non-void methods to test if my void method does
  the correct action. Also, I tested for edge cases for the methods that have edge cases, and I
  tested. This is an appropriate use of JUnit Testing because then I can test if my methods
  does what it needs to do.

  4. (File I/O): I use File I/O to store the high score, the time/duration of each game, and the
  score of each game, and to read the saved high score from the high score text file and output it
  to the game screen. This is an appropriate use of JUnit Testing because I write to the files to
  save the game data of the game session, and I read the files to get the high score to output to the
  game screen. This allows me to store the high score even when the player closes the game and then
  relaunch the game.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.

  (Sun): This class is where I create the sun object that the player controls in the game. The
  function of this class is to manage the position or movement of the sun, the creation/drawing of
  the sun, change the direction of the sun, and detect and act on the collision between the sun and
  the edge of the screen.

  (Cloud): This class is an abstract class that will be that parent class of the NormalCloud,
  ShrinkSunCloud, and PointCloud. This abstract class will have an abstract method for the
  different abilities of for each of the child class. From the Cloud parent class, the child classes
  will inherit the x, y, width, height, direction, and color variables. The child classes of cloud
  will also inherit the move(), detectCollision(Sun sun), detectPassing(Sun sun), isOffScreen(),
  addPassingPoint(GameField gameField), and draw(Graphics g) methods. Each of the methods the
  child classes inherit will be implemented in the cloud class, so they don't need to be implemented
  in the child classes. That is fine because those methods should be the same for the child classes.

  (NormalCloud): This class is the child/subclass of Cloud. It is where I will make implementations
  for the NormalCloud object. In this class, I override the ability method from the parent class,
  Cloud. The ability of the NormalCloud is that if the sun collides with the NormalCloud then the
  game ends.

  (PointCloud): This class is the child/subclass of Cloud. It is where I will make implementations
  for the PointCloud object. In this class, I override the ability method from the parent class,
  Cloud. The ability of the PointCloud is that if the sun collides with or is touching the
  PointCloud then the points are added to the score. That means that the longer the sun touches the
  PointCloud the more points is earned.

  (ShrinkSunCloud): This class is the child/subclass of Cloud. It is where I will make implementations
  for the ShrinkSunCloud object. In this class, I override the ability method from the parent class,
  Cloud. The ability of the ShrinkSunCloud is that if the sun collides with the ShrinkSunCloud then
  the radius of the sun decreases, thus the sun shrinks. The radius of the sun is lower bounded by 20
  pixels.

  (GenerateCloud): This class randomly generates the three different clouds based on the probability
  of their spawn, and the different clouds will be generated with random widths. This class generates
  one set or on row of clouds at a time. In each set or row of clouds, there are two clouds of the
  same type, the only difference is their widths, and either if it's a left cloud or a right cloud.
  Since each set or row of clouds always have 2 clouds, I have a method that returns an array of 2
  randomly generated clouds that will be added to the LinkedList of clouds in my GameField class.
  This class also has a method that takes in two ints and returns a random int in between the
  two int values, which I use to randomly generate the different clouds and their random widths.

  (Direction): This is an enum class that acts as a "label" for the direction of the sun and if the
  cloud is the left cloud or the right cloud. The enum for the direction of the sun is used to keep
  track of the current direction the sun is moving. The enum for the direction of the clouds is used
  to keep track of which side of the screen the cloud is on.

  (Screens): This is an enum class that acts as a "label" for the different screens of the game.
  The different screens includes the start screen, the 3 instruction screens, playing screen, game
  over screen, and the error screen. This enum keeps track of the current screen the game is in.

  (GameTimer): This class keeps track of the time/duration of each game. In other words, this class
  keeps track of how long the player survives during each game. This class uses System.nanoTime() to
  store/keep track of the duration of each game. The class also has a method that write the time
  of each game into the game time text file.

  (Scores): This class keeps track of the scores and high score of the game. This class reads and
  writes to the high score text file. It reads the high score file to retrieve the high score, and
  if the player ever gets a score higher than the previous high score this class has a method that
  sets the new high score and writes it to the high score file. Also, similar to the GameTimer class
  this class keep track of each and every score of each game the player plays and writes the scores
  to the scores text file.

  (Constants): This class stores all of my game constants that I use throughout my entire game.
  For example, the starting x and y positions of the sun, the game window size, the string of the
  file addresses, and much more. The function of this class is to make it easier to find and change
  the constant numbers I use in my game. This makes adjusting values like the window size, or the
  probability of a certain cloud spawning easier since it is all in one place, and I don't have to
  look through my classes to change a value.

  (GameField): This class is the main playing area of my game. This is the class where I combine
  all the classes I made, instantiate them as objects, and form the way my game will run. I create
  all the background images in this class, and I override the paintComponent method to draw all the
  game objects, such as the sun and the different clouds and all the different screens of my game.
  Essentially, the GameField class is where everything comes together to form my amazingly difficult
  game!

  (RunSunRise): This class is where the GUI of my game is created. The functions of this class is
  to create the window and panels of my game. This where my game runs/is set up.

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?

I was getting a lot of exception when trying to read the high score from the file, such as
NullPointerException, IndexOutOfBoundException, and NumberFormatException. I spent hours trying to
figure out how to fix it. I pinpointed where the exceptions were coming from using the messages
printed in the terminal/console. I realized the problem was in my getHighScoreFromString method in
my Scores class. I fixed my problem with a try catch block, and when any exceptions are caught
I set the high score to 0. This makes sense because the exception means there is a problem in
the high score file, such as missing number, incorrect formatting, or it's empty, so there are no
high scores to get, so high score is set to 0. The file will then correct itself after ever game
since the high score file is changed after every game.

Making my methods JUnit Testable was also pretty hard. Throughout my coding process, I had to change
or make small adjustments to my methods such that it is testable and does not rely on the GUI. I
did this by creating several small helper methods rather than one big method that did a lot of
things.

Also, I tried using the GameTimer class I created to keep track of the time for each game to help
generate the clouds in time intervals. I wasn't able to make this implementation, so I ended up
using an int variable to act as timer that resets everytime a cloud is generated, and a cloud is
generates everytime the timer int variable reaches less than or equal to 0.

I also made attempts to use the GameTimer to make the ShrinkSunCloud shrink the cloud under a timer,
but I utterly failed after spending many hours trying to find a way. I ended up making the
ShrinkSunCloud shrink the sun for the rest of the game, and made the generation of a ShrinkSunCloud
pretty rare at a 5% chance of spawning. It's not a bug, it's a feature. :)

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?

I would say I have a good separation of functionality. I created many classes for different
functions in my game, such as Scores, GameTimer, and GenerateClouds. Also, I implemented separate
classes for the objects of my game such as Sun and Cloud, where cloud has subtypes of a Normal
Cloud, Point Cloud and Shrink Sun Cloud.

I would say my private state encapsulation is pretty well. All of my variables in my GameField
class and my other classes are private, so the variables in my GameField and my other classes can
not be changed without a setter or getter method. Also, none of my instance variables in my
GameField have setter methods, so the variable themselves will not directly be changed.

If given the chance, I would refactor the way my classes are organized and the naming for some of
my variables and methods in some of my classes. To organize my classes, I would create packages
of the different types of classes, such as game object classes (sun and the clouds), classes that
keep track of information and read or write to the file (GameTimer and Score) (This package could
be named "Game Info"), and possibly a package for my enum classes. Also, I would change the naming
for my variables and methods by finding a shorter name that still explains the purpose of the
variable or method. I also would add inner classes for the different classes like Sun, Cloud, etc.
to my Constants class to better organize and separate the game constant variables.


========================
=: External Resources :=
========================

- Cite any external resources (images, tutorials, etc.) that you may have used 
  while implementing your game.
https://stackoverflow.com/questions/1466240/how-to-set-an-image-as-a-background-for-frame-in-swing-gui-of-java
(Helped me set the background of my game using java swing and java awt)

https://www.techiedelight.com/measure-elapsed-time-execution-time-java/
(Helped me keep track of time and find elapsed time)