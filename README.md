# Garden Simulation

## Project Scope

**What will this application do?**
- This application is a virtual garden simulation that aims to provide a simplistic rendition of real-life gardening 
  and/or farming. Users are able to start their own garden, and plant seeds. To maintain plants, users can water and 
  fertilize then (according to each plant's specifications). Ripe plants can be harvested, and sold to earn more money
  to purchase more plants for the garden. 


**Who are the users?**
- This application is targeted towards people who enjoy playing traditional RPG games. Furthermore, it is catered towards people who
enjoy the pixel aesthetic, and a simple, non-strenuous game to pass time.
  

**Why does this project of interest to you?**

- This project is of particular interest to me because I am personally enjoy playing role-playing games (RPGs).
  Inspired by the PC game "Stardew Valley", I am interested to create my own interpretation of it. The collaborative 
  feature of this game is also inspired by my personal preference for collaborative games over single-player games. 
  Designing and implementing this application is, therefore, an attempt to create the "perfect" game for myself.

## User Stories
- As a user, I want to be able to start a garden, and plant a new seed in it.
- As a user, I want to be able to purchase a seed from a store, and add it to my garden.
- As a user, I want to be able to select a plant in the garden and remove it.
- As a user, I want to be able to select a ripe plant in my inventory and sell it. 
- As a user, I want to be able to keep track of how much a plant is watered or fertilized.

## References 
- Override of equals method in Plant based on https://dzone.com/articles/java-cloning-even-copy-constructors-are-not-suffic
- Pixel art is an adaptation (and inspired by) the following sources:
  - Stardew Valley
  - https://www.pinterest.ca/pin/pixel-retro-font-videogame-type-8bit-alphabet-letters-and-numbers-download-a-free-preview-or-high-quality-adobe-ill--616078424011848245/
  - https://www.shutterstock.com/search/pixel-fence?image_type=vector
  - https://www.freepik.com/premium-vector/simple-home-with-pixel-art-style_18262961.htm
  - https://www.shutterstock.com/image-vector/seamless-pattern-8-bit-pixel-art-1536471818
  - https://www.vecteezy.com/vector-art/10807136-treasure-chest-pixel-art

# Logging Notes:
- Users always have 100 coins when they first start a new account, and no matter how much they spend. This is why the
 log doesn't log the first purchase (cactus) decreasing the user's balance. The log only logs the balance when there is
a noticeable change in the balance after a purchase (potato).

![Logging.png](https://github.students.cs.ubc.ca/CPSC210-2022W-T2/project_v6l7y/blob/135f9706d99e7ae92a5e76de089c2dadd9202b2d/Logging.png)

# Future Improvements
In order to reduce the duplication and the number of subclasses, I would first make the abstract Plant class to be a concrete
class. Then, within the Plant class I would create a static hashmap that contains all 11 plant names with their associated 
unique prices. To retrieve a certain plant, there would be a method (e.g. getPlant()) that passes in a String plantName (e.g., getPlant(String PlantName))
which would serve as the key to retrieve the associated price. With this approach, I would be able to remove all the individual 
plant subclasses (and extends). 

I would also refactor my GardenApp to adhere to the Single Responsibility Design heuristic. Currently, GardenApp contains all 
the methods that run my TUI and GUI. These methods can be refactored into the following classes to increase cohesion and
readability: "GardenKeeping", "Shopping", "InventoryCheck", "Banking". "GardenKeeping" would be responsible for functionalities relating
to watering, feeding, harvesting and uprooting plants. "Shopping" would solely contain methods relating to buying plants from 
the store. "InventoryCheck" would contain methods allowing users to sell their current harvested plants. Lastly,
"Banking" would have methods related to showing the current balance and increasing/decreasing the balance. By refactoring these methods
into their own classes, my application's design is more cohesive. 
