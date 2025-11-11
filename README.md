# JFX-animal-shelter
In this repository is a JavaFX program simulating an animal shelter. Functionality includes pet adoption and surrendering simulated through a binary search tree.

# How does it work?
Upon booting the JavaFX app, there is a TableView containing all data for pets currently in the shelter. When the user selects to adopt a pet from the shelter, we search the binary search tree of pets by its name tag ID. If the pet is found, it is removed from the tree and the table is updated (using observable array list) and is placed in the LinkedList of pets already adopted. Surrendering works in a similar fashion - we search for the pet in the LinkedList also by name tag ID; once removed from the LinkedList, the pet is placed back into the binary search tree. There is also functionality to see what pets the user has already adopted (using an alert message)
