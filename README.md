## Film Query Project

## Overview

In this application, a user is prompted to explore information on a range of films.
Users can look up a film by its id or search for a film using a keyword.

## Topics

* Print Menu
Users are shown a menu of three options. Users can look up a film by it's id, a keyword, or exit the program.

* methods

The startUserInterface() method contains a switch statement that processes user input.

*Taking User Input
User input is retrieved using a scanner. The user is prompted to enter a number between 1 and 3. Depending on their answer, the user is then prompted to either enter a film id or a film keyword. Both options return film information as well as a list of actors for each film.

## Classes, Objects, Interfaces

* The Film and Actor classes/objects were created based on information found in the sdvid database. These objects are called in the DatabaseAccessorObject Class.

* The DatabaseAccessor Interface defines methods found in the DatabaseAccessorObject class.

* The DatabaseAccessorObject class is the location where sdvid database content is parsed into film and/or actor objects depending on the method called.

*Lessons learned
In this project I learned more about how drivers operate and how code is interpreted via the JDBC and mySQL.
