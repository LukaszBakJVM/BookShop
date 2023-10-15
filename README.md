# BookShop

The application is a library management in Java (Spring).
Only the administrator can add books.

Borrowing a book for up to 30 days is free, over PLN 5 per day, automatic charging,
and automatic sending of an e-mail reminding about borrowed books and the amount to be paid.
To add an e-mail address from which reminder messages are to be sent You must edit:
src/main/resources/application.properties
spring.mail.username=Your mail
spring.mail.password= Your  pass
Database -> h2
To chang 
You must add database  -> src/main/resources/application.properties.
