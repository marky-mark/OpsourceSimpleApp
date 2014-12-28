
Execute the following commands to setup your database
unix-shell>mysql -uroot

CREATE USER 'dimensiondata'@'localhost' IDENTIFIED BY 'd1m3nS10n5000';
GRANT ALL PRIVILEGES ON * . * TO 'dimensiondata'@'localhost';
EXIT:

unix-shell>mysql -u dimensiondata -pd1m3nS10n5000

CREATE DATABASE servers DEFAULT CHARACTER SET utf8;
EXIT;

unix-shell> mvn clean package
unix-shell> cd liquibase
unix-shell> mvn liquibase:update