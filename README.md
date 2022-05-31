# __SPACE INVADERS__
  
![Screenshot (8)](https://user-images.githubusercontent.com/70664877/167709331-862d2e3c-ea88-45dd-b9af-9a377f61fd4c.png)

  
## Project Description
Asteroids is a single player game that focuses on opbtaining as many points possible by shooting flying asteroids. The user controls a ufo using keys (listed below) and needs to avoid being hit by the invaders whilst shooting at them with lasers. For each invader thats hit, the user earns one point. However, if the ufo collides with an invader, the game is over and the players score restarts at 0. The score can be found at the top left of the game screen. We also have a highest score counter which displays the highest score that the player has earned. There is a level counter as well this shows the user what level they were able to reach. The goal is to be able to reach the highest level possible.

## Usage
Arrow keys: move ufo left and right

WASD keys: move ufo left and right

Enter key: start game

Space bar: shoot lasers

![Screenshot (24)](https://user-images.githubusercontent.com/70664877/167712036-40332353-a72f-4b6a-865e-bd2aae510596.png)

## Classes

### UFO Class
This class focuses on the ufo, the character that the user controls. There is only one ufo in this game. This image is custom produced by myself from pixelart.

![spaceship](https://user-images.githubusercontent.com/70664877/167707583-6f87e484-4ac6-4ab9-b1be-bca45f0d0ccc.png)

### Invaders Class
The invaders is created with an array in oder to have multiple comets falling down the screen at once. This is the object that the user is trying to avoid and shooting to obtain points. This image is produced by our team.

![shot (1)](https://user-images.githubusercontent.com/70664877/167707842-492ec3cd-9fdf-4fff-b0fb-3c8595ce457e.gif)

### Laser Class
The laser object is one that is shot from the ufo and the timing of the shooting is in the users control. This object is created with the use of an array to allow the player to shoot multiple laser on screen.

![laser](https://user-images.githubusercontent.com/70664877/148605929-099cf4b8-3cce-4564-93d0-5d8ba970e52d.png)

### Background Class
One background image is used fill the entire screen. This image represents space and is painted first. 

![bg](https://user-images.githubusercontent.com/70664877/167707742-062834a1-df5a-49fb-91be-4f854e3dfaab.png)

### SleepTime Class
This class is used to control the dropping of the invaders with the use of a timer. An invader is dropped from the top of the screen randomly given the range of .6 to 1 second. A thread is used to allow invaders to fall continously with the timer _and_ have the user shoot lasers.

![Screenshot (10)](https://user-images.githubusercontent.com/70664877/167709829-c9fb90df-cfa6-4ffc-83fa-10b35ba1f456.png)

## Paint method
This method is used to draw objects on the screen and updates their position when necessary. Just one ufo and background are painted for this game. An invader is called randomly multiple times from a range of .6 to 1 second. For the laser, one is painted for each time the user shoots (clicks space bar). Both the invader and laser are called with the use of an array and a for loop. The starting screen text and score are also under this method, each with customized fonts.

![Screenshot (23)](https://user-images.githubusercontent.com/70664877/167711859-fb0f8c55-9f23-458b-a290-7aaff1236058.png)

## Collision
This is one of the most major parts of the game and contributes to the overall game play. With the use of if statements, the code detects when the an object overlaps with another setting off a reaction. For example, the laser collides with an invader, rewarding them with one point. Additionally, if the ufo comes in contact with an invader, the game restarts back to the start screen, the score resets back to 0, and the ufo is placed back at the center of the screen. 

![Screenshot (15)](https://user-images.githubusercontent.com/70664877/167710648-e7c4af4a-8064-46de-9083-622cf5767188.png)

## Game Over
When the users ufo collides with an invader, a reset method is pulled from the ufo class which causes the start screen to return. Inside of the collision if statement between the ufo and invader, the score is set equal to 0. The position of the ufo is placed back to its starting position ready for the next game. Additionally, all movement is stopped which is achieved by setting the velocity for all moving objects back to 0. For the invader, in order to prevent them to continue falling down the screen, all invader are cleared and same goes for the lasers. 

![Screenshot (16)](https://user-images.githubusercontent.com/70664877/167711348-2b6a1073-0bbe-4bdb-8f1a-af2aa962e2dd.png)
![Screenshot (19)](https://user-images.githubusercontent.com/70664877/167711038-64cd33ee-226e-49e4-a085-7a6a38b74cd0.png)
![Screenshot (17)](https://user-images.githubusercontent.com/70664877/167711274-9ba0c4a5-2859-4deb-88d3-fa6e11c3f895.png)

## Creating Objects
Objects are created individually or with the use of an array. Individual objects are created at reguarly while objects from arrays are created from for loops.

![Screenshot (20)](https://user-images.githubusercontent.com/70664877/167711592-0936a45b-4bea-4c18-9bdd-fe4598c915cb.png)
![Screenshot (21)](https://user-images.githubusercontent.com/70664877/167711604-f2c989d2-a29b-462b-9261-dabd2b5d43a7.png)


## Sounds

![Screenshot (12)](https://user-images.githubusercontent.com/70664877/167710239-79f54e13-82cc-4d76-a7e4-6640df430359.png)
![Screenshot (14)](https://user-images.githubusercontent.com/70664877/167710424-dc871492-00f5-4a41-ae4c-804c52be539d.png)


## Roadmap
Future updates are unlikely but if we were too, we wanted the ivaders to turn red for a split second after colliding with the laser. Since there are only 2 colors in this game, I think it would make it a bit more engaging with the addition of a third. 

## Contribution
Anyone is welcome to contribute their own code.

## Acknowledgment
All objects and code were generated by Clarice Park and Ha'ani Tydingco. Inspiration for the game appearence was taken from google and stackoverflow helped for creating the timer for this game.
