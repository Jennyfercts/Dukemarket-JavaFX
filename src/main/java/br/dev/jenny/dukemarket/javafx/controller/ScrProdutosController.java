package br.dev.jenny.dukemarket.javafx.controller;

import br.dev.jenny.dukemarket.javafx.App;
import br.dev.jenny.dukemarket.javafx.DAO.ProdutoDAO;
import br.dev.jenny.dukemarket.javafx.model.Produto;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author sesi2dia
 */
public class ScrProdutosController implements Initializable {

    // determina se o botão "novo" foi ou não clicado.
    private boolean flagNovo;

    /**
     * Initializes the controller class - Produtos
     *
     * */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Vincula as colunas da TableView à classe produto
        bindColumns();
        
        // Busca dados
        carregaDados();
    }    
    
    
        @FXML
        TableView <Produto> tblProduto;

        @FXML
        TableColumn <Produto, Integer> tcoId;

        @FXML
        TableColumn <Produto, String> tcoCodBarras;

        @FXML
        TableColumn <Produto, String> tcoDescricao;

        @FXML
        TableColumn <Produto, Double> tcoQtd;

        @FXML
        TableColumn <Produto, Double> tcoValorCompra;

        @FXML
        TableColumn <Produto, Double> tcoValorVenda;

        @FXML
        TableColumn <Produto, Calendar> tcoDataCadastro;


        @FXML
        TextField txtId;

        @FXML
        TextField txtCodBarras;

        @FXML
        TextField txtDescricao;

        @FXML
        TextField txtQtd;

        @FXML
        TextField txtValorCompra;

        @FXML
        TextField txtValorVenda;

        @FXML
        DatePicker dtpDataCadastro;

        ProdutoDAO pDAO;
        Produto pClicked;   // Armazena o produto clicado na tabela.
        
        
     /**
     * Conecta as colunas da TableView com os atributos da classe Produto.
     * @return void
     */
    private void bindColumns() {

        // Vincula a coluna tcoId com o atributo id da classe Produto
        tcoId.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Adiciona uma configuração extra de alinhamento à direita.
        tcoId.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoCodBarras.setCellValueFactory(new PropertyValueFactory<>("codBarras"));
        tcoCodBarras.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));

        tcoQtd.setCellValueFactory(new PropertyValueFactory<>("qtd"));
        tcoQtd.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoValorCompra.setCellValueFactory(new PropertyValueFactory<>("valorCompra"));
        tcoValorCompra.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoValorVenda.setCellValueFactory(new PropertyValueFactory<>("valorVenda"));
        tcoValorVenda.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        tcoDataCadastro.setStyle("-fx-alignment: CENTER_RIGHT;");
    }
    
    
    /**
     * 
     * Carrega os dados de Produto que estão na base e vincula a TableView.
     * 
     */

    public void carregaDados(){

        this.pDAO = new ProdutoDAO();

        // Carrega view tblProduto com todos os resultados de produto.
        this.tblProduto.getItems().setAll(pDAO.getResults());

    }
    
   
    /**
     * Action do botão btnNovo.
     * Limpa os campos e posiciona o focus em txtId.
     *
     * @return void
     */
    @FXML
    private void btnNovoClick() {
        txtId.setText("auto");
        txtId.setEditable(false);

        txtCodBarras.setText("");
        txtCodBarras.requestFocus();

        txtDescricao.setText("");
        txtQtd.setText("");
        txtValorCompra.setText("");
        txtValorVenda.setText("");
        dtpDataCadastro.setValue(null);
        
        this.flagNovo = true;
    }
    
    /**
     * Gerencia o click na tabela, identificando qual registro foi selecionado e
     * preenchendo os TextFields.
     */
    @FXML
    public void tblProdutoOnMouseClicked() {

        // getSelectedItem devolve um objeto Produto.
        // pClicked representa o Produto Clicado
        this.pClicked = tblProduto.getSelectionModel().getSelectedItem();

        if (pClicked != null) {

            txtId.setText(String.valueOf(pClicked.getId()));
            txtId.setEditable(false);

            txtCodBarras.setText(pClicked.getCodBarras());
            txtDescricao.setText(pClicked.getDescricao());

            txtQtd.setText(String.valueOf(pClicked.getQtd()));
            txtValorCompra.setText(String.valueOf(pClicked.getValorCompra()));
            txtValorVenda.setText(String.valueOf(pClicked.getValorVenda()));

            // Tratamento da data do cadastro:
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            formatter = formatter.withLocale(Locale.US);
            LocalDate date = LocalDate.parse(pClicked.getDataCadastro(), formatter);

            dtpDataCadastro.setValue(date);
            dtpDataCadastro.setEditable(false);

            this.flagNovo = false;
        }
    }
        
        
        /**
        * cria ou atualiza o produto de acordo com as informações fornecidas.
        */ 
        @FXML
        public void btnSalvarClick() {
            
            Produto paux = new Produto();
            
            paux.setCodBarras( txtCodBarras.getText());
            paux.setDescricao( txtDescricao.getText());
            
            paux.setQtd( Double.valueOf(txtQtd.getText()));
            paux.setValorCompra( Double.valueOf(txtValorCompra.getText()));
            paux.setValorVenda( Double.valueOf(txtValorVenda.getText()));
            
            
            if(this.flagNovo){
                // Cria um novo produto
                pDAO.create(paux);
                
            } else {
                
                // ATUALIZAÇÃO
                paux.setId( Integer.valueOf(txtId.getText()));
                pDAO.update(paux);
            }
            
            this.carregaDados();
           
    }
        
        @FXML
        public void btnExcluirClick(){
            if(this.pClicked != null){
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert. setTitle("Confirma Exclusão do Produto?");
                alert.setHeaderText(pClicked.getDescricao());
                alert.setContentText("Tem certeza que deseja excluir?");
                
                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    this.pDAO.delete(this.pClicked.getId());
                    
                    
                } 
                this.tblProduto.getSelectionModel().clearSelection();
                pClicked = null;
                } else {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert. setTitle("Ops!");
                    alert.setHeaderText("Atenção!");
                    alert.setContentText("Você deve selecionar um registro antes de excluí-lo!");
                    
                    alert.showAndWait();
                }
    }
}
        
        


