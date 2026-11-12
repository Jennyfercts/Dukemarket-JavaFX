package br.dev.jenny.dukemarket.javafx.controller;

import br.dev.jenny.dukemarket.javafx.App;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author sesi2dia
 */
public class MenuPrincipalController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    public void btnProdutosClick() throws IOException {
            
            try{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("scrProdutos.fxml"));
                
                /**
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */
                
                Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                Stage stage = new Stage();
                
                stage.setTitle("CRUD Produtos");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e){
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "failed to create new Window scrProdutos.", e);
            }
            
        }
    
    @FXML
    public void btnClientesClick() throws IOException {
            
            try{
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("scrClientes.fxml"));
                
                /**
                 * if "fx:controller" is not set in fxml
                 * fxmlLoader.setController(NewWindowController);
                 */
                
                Scene scene = new Scene(fxmlLoader.load(), 900, 600);
                Stage stage = new Stage();
                
                stage.setTitle("CRUD Clientes");
                stage.setScene(scene);
                stage.show();
            } catch (IOException e){
                Logger logger = Logger.getLogger(getClass().getName());
                logger.log(Level.SEVERE, "failed to create new Window scrClientes.", e);
            }
            
        }
    
}
    

