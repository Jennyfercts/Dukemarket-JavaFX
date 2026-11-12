module br.dev.jenny.dukemarket.javafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    
    requires java.sql;

    opens br.dev.jenny.dukemarket.javafx to javafx.fxml;
    opens br.dev.jenny.dukemarket.javafx.controller to javafx.fxml;
    
    exports br.dev.jenny.dukemarket.javafx;
    exports br.dev.jenny.dukemarket.javafx.controller;
    exports br.dev.jenny.dukemarket.javafx.DAO;
    exports br.dev.jenny.dukemarket.javafx.model;
    exports connection;
    
    requires mysql.connector.java;
    requires javafx.graphicsEmpty;
}
