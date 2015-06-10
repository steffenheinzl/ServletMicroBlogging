Neu hinzugekommen:
Servlet ShowUserMessages
usermessages.html
Klasse UsersUtil
Klasse JavaApiEnhancements

Das de.fhws.microblog.utils package ist um ein user handling erweitert worden.
Die Klassen UsersUtil und JavaApiEnhancements werden intern von der Klasse MessageUtil verwendet 
und dienen der Persistierung der User.


Nun soll das neuhinzugekomme Servlet ShowUserMessages dahingehend erweitert werden, dass es ausschließlich alle Nachrichten, 
die an den übergebenen Benutzer gerichtet wurden, anzeigt. 
Eine Nachricht wird einem Benutzer zugeordnet, wenn dieser durch ein @-Zeichen adressiert wird, z.B. 
@Joe: Hallo
wird dem User Joe zugeordnet und soll in seinen Nachrichten angezeigt werden. 
Ferner gibt es eine HTML-Form in der Datei usermessages.html, die das ShowUserMessages-Servlet aufruft.
Über http://localhost:8080/ServletMicroBlogging04/ShowUserMessages?user=Joe können bspw. die Joe zugeordneten Nachrichten angerufen werden. 

Suchen Sie nach den TODOs im Projekt und erledigen Sie diese!


