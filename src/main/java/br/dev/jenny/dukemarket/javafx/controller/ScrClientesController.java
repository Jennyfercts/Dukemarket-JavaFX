package br.dev.jenny.dukemarket.javafx.controller;

import br.dev.jenny.dukemarket.javafx.DAO.ClienteDAO;
import br.dev.jenny.dukemarket.javafx.model.Cliente;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
public class ScrClientesController implements Initializable {
  
    // determina se o botão "novo" foi ou não clicado.
    private boolean flagNovo;

    /**
     * Initializes the controller class - Clientes
     *
     * */
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Vincula as colunas da TableView à classe cliente
        bindColumns();
        
        // Busca dados
        carregaDados();
    }    
    
    
        @FXML
        TableView <Cliente> tblCliente;

        @FXML
        TableColumn <Cliente, Integer> tcoId;

        @FXML
        TableColumn <Cliente, String> tcoNome;

        @FXML
        TableColumn <Cliente, String> tcoCPF;

        @FXML
        TableColumn <Cliente, Double> tcoEndereco;

        @FXML
        TableColumn <Cliente, Double> tcoCidade;

        @FXML
        TableColumn <Cliente, Double> tcoUF;

        @FXML
        TableColumn <Cliente, Calendar> tcoDataCadastro;


        @FXML
        TextField txtId;

        @FXML
        TextField txtNome;

        @FXML
        TextField txtCPF;

        @FXML
        TextField txtEndereco;

        @FXML
        TextField txtCidade;

        @FXML
        TextField txtUF;

        @FXML
        DatePicker dtpDataCadastro;

        ClienteDAO cDAO;
        Cliente cClicked;   // Armazena o produto clicado na tabela.
        
        
     /**
     * Conecta as colunas da TableView com os atributos da classe Cliente.
     * @return void
     */
    private void bindColumns() {

        // Vincula a coluna tcoId com o atributo id da classe Cliente
        tcoId.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Adiciona uma configuração extra de alinhamento à direita.
        tcoId.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoNome.setCellValueFactory(new PropertyValueFactory<>("nome"));
        tcoNome.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoCPF.setCellValueFactory(new PropertyValueFactory<>("cpf"));

        tcoEndereco.setCellValueFactory(new PropertyValueFactory<>("endereco"));
        tcoEndereco.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoCidade.setCellValueFactory(new PropertyValueFactory<>("cidade"));
        tcoCidade.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoUF.setCellValueFactory(new PropertyValueFactory<>("uf"));
        tcoUF.setStyle("-fx-alignment: CENTER_RIGHT;");

        tcoDataCadastro.setCellValueFactory(new PropertyValueFactory<>("dataCadastro"));
        tcoDataCadastro.setStyle("-fx-alignment: CENTER_RIGHT;");
    }
    
    
    
    /**
     * 
     * Carrega os dados de Cliente que estão na base e vincula a TableView.
     * 
     */

    public void carregaDados(){

        this.cDAO = new ClienteDAO();

        // Carrega view tblProduto com todos os resultados de Cliente.
        this.tblCliente.getItems().setAll(cDAO.getResults());

    }
    
   
    /**
     * Action do botão btnNovo.
     * Limpa os campos e posiciona o focus em txtNome.
     *
     * @return void
     */
    @FXML
    private void btnNovoClick() {
        txtId.setText("auto");
        txtId.setEditable(false);

        txtNome.setText("");
        txtNome.requestFocus();

        txtCPF.setText("");
        txtEndereco.setText("");
        txtCidade.setText("");
        txtUF.setText("");
        dtpDataCadastro.setValue(null);
        
        this.flagNovo = true;
    }
    
    /**
     * Gerencia o click na tabela, identificando qual registro foi selecionado e
     * preenchendo os TextFields.
     */
    @FXML
    public void tblClienteOnMouseClicked() {

        // getSelectedItem devolve um objeto Cliente.
        // cClicked representa o Cliente Clicado
        this.cClicked = tblCliente.getSelectionModel().getSelectedItem();

        if (cClicked != null) {

            txtId.setText(String.valueOf(cClicked.getId()));
            txtId.setEditable(false);

            txtNome.setText(cClicked.getNome());
            txtCPF.setText(cClicked.getCpf());

            txtEndereco.setText(String.valueOf(cClicked.getEndereco()));
            txtCidade.setText(String.valueOf(cClicked.getCidade()));
            txtUF.setText(String.valueOf(cClicked.getUf()));

            // Tratamento da data do cadastro:   
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            formatter = formatter.withLocale(Locale.US);
            LocalDate date = LocalDate.parse(cClicked.getDataCadastro(), formatter);

            dtpDataCadastro.setValue(date);
            dtpDataCadastro.setEditable(false);
          
            this.flagNovo = false;
        }
    }
        
        
        /**
        * cria ou atualiza o cliente de acordo com as informações fornecidas.
        */ 
        @FXML
        public void btnSalvarClick() {
            
            Cliente caux = new Cliente();
            
            caux.setNome( txtNome.getText());
            caux.setCpf( txtCPF.getText());
            caux.setEndereco( txtEndereco.getText());
            caux.setCidade( txtCidade.getText());
            caux.setUf( txtUF.getText());
            caux.setDataCadastro( dtpDataCadastro.getValue()  );
            
            
            if(this.flagNovo){
                // Cria um novo cliente
                cDAO.create(caux);
                
            } else {
                // ATUALIZAÇÃO
                System.out.println("Passei pelo else!!");
                caux.setId(Integer.parseInt(txtId.getText()));
                cDAO.update(caux);
            }
            
            this.carregaDados();
           
    }
        
        
        @FXML
        public void btnSairClick(ActionEvent event){
              Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
              stage.close();
        }
       
}
