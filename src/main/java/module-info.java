module ma.enset.javaexamrepository {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires static lombok;

    opens ma.enset.javaexamrepository to javafx.fxml;
//    opens ma.enset.javaexamrepository.controller to javafx.fxml;
    opens ma.enset.javaexamrepository.entity to javafx.base;

    exports ma.enset.javaexamrepository;
}