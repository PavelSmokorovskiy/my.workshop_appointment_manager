# Workshop appointment manager
Spring based web application which allows to make an appointment in a car workshop. Without a UI frontend. All Operations available over a Rest API. For the data
storage using a simple in memory storage (List, Map etc.). But it should be easy to replace the
in-memory storage later with a real database. 
~~~~~
The following functions available:
Creating a user with the following fields:
* Username (unique)
* Email (unique)
* City
* Postal code
* Country
Deleting an existent user
Creating a car workshop with the following fields:
* Company Name (unique)
* Car trademarks specializes in (example BMW, VW)
* City
* Postal code
* Country
Deleting an existent car workshop
Searching for all car workshop in a specific city
Creating an appointment between a user and a car workshop. 
An appointment has the following fields:
* Username
* Users car trademark
* Company name
* Date and time
Changing the date and time of an existent appointment
Deleting an existent appointment
~~~~~