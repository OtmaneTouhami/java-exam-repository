module ma.enset.javaexamrepository {
    requires javafx.controls;
    requires javafx.fxml;


    opens ma.enset.javaexamrepository to javafx.fxml;
    exports ma.enset.javaexamrepository;
}