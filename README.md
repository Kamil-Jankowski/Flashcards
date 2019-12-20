# Flashcards

**Stage 6: (ongoing)**

_**Description**<br>
Add some statistics features. We suggest you implement the following:_

* _Action log saves the application log to the given file. Save all lines that have been input in or output to the console to the file. You can use a list to store the lines._
* _Action hardest card prints the term of the card that has the most mistakes. You can store the mistake count in a map. If there are no cards with mistakes, you should print There are no cards with errors.. And for multiple hardest cards, you should list them all, like in the example below._
* _Action reset stats erases the mistake counts for all cards._

_Also you should update serialization/deserialization to store sets of three items (term, definition, mistakes) instead of pairs (term, definition)._
***
**Stage 5:**

_**Description**<br>
Improve the application's interactivity. Ask the user for action and make it._

_Support these actions:<br>_
* _add a card: `add`,_
* _remove a card: `remove`,_
* _load cards from file ("deserialization"): `import`,_
* _save cards to file ("serialization"): `export`,_
* _ask for a definition of some random cards: `ask`,_
* _exit the program: `exit`._

_You can use the following file format. The file consists of pairs of lines. The first line of each pair is a term, and the second line is a definition._

_In this stage, if you try to add a card with an existing term or an existing definition, the application must just reject it by printing an error message._

_When you load cards from a file, you shouldn't erase the cards that aren't in the file. If the imported card already exists, it should update the old one. It is guaranteed, that there won't be any conflicts with definitions in the tests._
***
**Stage 4:**

_**Description**<br>
Imagine a situation: the answer is wrong for the given term, but it is correct for another term. Let's consider situations like this._

_You can use maps. Ask all card's definitions in the order of addition. If the definition is wrong for the current term but it is correct for another, output the original term._

_When the user tries to add a duplicated term or definition, forbid it and ask again until the user inputs a unique one. For now, you are able to implement this without a `try catch` construction. Use the rule: if you can avoid exception-based logic, avoid it!_
***
**Stage 3:**

_**Description**<br>
Your program is able to play using one card. Let's make our game serious and implement a set of cards now!_

_Let the user decide how many cards they would like to keep. First, ask the player to enter the desired number of cards. Then, ask to input the term and the definition of every card. If the initial number was 100, then... well, what a pity!_

_In the end, when all of the cards are defined and saved, your program is finally ready to play! Question the player about all the new words they have entered. The program should give the term and ask for a definition. Let the game begin!__

_**How to submit?**<br>
This stage is auto-graded. The grader will behave as in example, so you can change an internal logic of your code, but the output should be similar. Here are some important notes:<br>_

* _When you ask for a definition of a card, you should write the term of the card in quotes. Example: `Print the definition of "black":`._
* _If the answer is correct, you should print `Correct answer.`. And if the answer is wrong, you should print `Wrong answer. The correct one is "black".`, where `"black"` is the correct definition._
***
**Stage 2:**

_**Description**<br>
We cannot play Flashcards with only one card, right? Let's make our program dynamic! Implement a custom card creation mechanism. Read a term and a definition from the console and create a card._

_After that, a user inputs a line as an **answer** (a definition of the card). Compare the user's answer to the correct definition and print the verdict._

_**How to submit?**<br>
This stage is auto-graded. The grader will input 3 lines (term, definition, answer) and check that your output contains a word:<br>_
* _wrong if the answer isn't equal to the definition,_
* _right if the answer is equal to the definition._

***
**Stage 1:**

_**Description**<br>
You may already know that a **flashcard** is a piece of paper that contains a **term** on one side and a related text you want to remember on another one. Flashcards are often used to learn a foreign language, so let's say this text is just a **definition** of the term._

_We offer you to create an application that emulates a set of flashcards on a screen. While developing this application, you not only learn programming, but also save paper!_

_As a start, implement a program that outputs a single term and its definition. You can print a term and a definition you like in this stage._

_**How to submit?**<br>
This stage is auto-graded. The grader will check that:<br>_
* _you output 4 lines,_
* _the first line is `Card:`,_
* _the third line is `Definition:`._

***
