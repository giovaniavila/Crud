package resource.provajuliana;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javax.swing.*;
import javax.swing.text.html.ImageView;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import static java.lang.Float.parseFloat;
import static java.time.LocalDate.parse;
import static resource.provajuliana.ConnectionFactory.getConnection;
import static resource.provajuliana.alunoDao.deletarAluno;


public class HelloController implements Initializable {

    @FXML
    private TableColumn<Aluno, Float> alturaColumn;

    @FXML
    private Button btnEditar;

    @FXML
    private Button btnExcluir;

    @FXML
    private Button btnSalvar;

    @FXML
    private TableColumn<Aluno, Integer> cpfColumn;

    @FXML
    private TableColumn<Aluno, Date> dataNascColumn;

    @FXML
    private TableColumn<Aluno, Float> imcColumn;

    @FXML
    private TableColumn<Aluno, String> nomeColumn;

    @FXML
    private TableColumn<Aluno, Float> pesoColumn;

    @FXML
    private TableView<Aluno> table;

    @FXML
    private TextField txtAltura;

    @FXML
    private TextField txtCpf;

    @FXML
    private DatePicker txtDataNasc;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPeso;

    ObservableList<Aluno> listAluno;

    int index = -1;

    Connection conn;
    ResultSet rs = null;
    PreparedStatement pst = null;

    @FXML
    void editar(ActionEvent event) {
        alunoDao aluno = new alunoDao();

    }

    @FXML
    void excluir(ActionEvent event) {
        int myIndex = table.getSelectionModel().getSelectedIndex();
        String nome = String.valueOf(table.getItems().get(myIndex).getNome());
        int cpf = Integer.parseInt(String.valueOf(table.getItems().get(myIndex).getCpf()));
        float altura = Float.parseFloat(String.valueOf(table.getItems().get(myIndex).getAltura()));
        float peso = Float.parseFloat(String.valueOf(table.getItems().get(myIndex).getPeso()));
        Date dataNasc = Date.valueOf(table.getItems().get(myIndex).getDataNasc().toLocalDate());
        float imc = Float.parseFloat(String.valueOf(table.getItems().get(myIndex).getImc()));

        Aluno aluno = new Aluno(nome,cpf,altura,peso,dataNasc,imc);
        alunoDao dao = new alunoDao();

        dao.deletarAluno(aluno);

    }


    @FXML
    void salvar(ActionEvent event) {
        String nome =txtNome.getText();
        int cpf = Integer.parseInt(txtCpf.getText());
        float altura = Float.parseFloat(txtAltura.getText());
        float peso = parseFloat(txtPeso.getText());
        Date dataNac = Date.valueOf(txtDataNasc.getValue());
        float imc = peso / (altura * altura);

        //Criando novo aluno através de instância
        Aluno aluno = new Aluno(nome,cpf,altura,peso,dataNac,imc);
        alunoDao dao = new alunoDao();

        //acessando o método adicionar aluno
        dao.adicionarAluno(aluno);


        //limpando os campos de texto
        txtNome.setText("");
        txtCpf.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        nomeColumn.setCellValueFactory(new PropertyValueFactory<Aluno, String>("nome"));
        cpfColumn.setCellValueFactory(new PropertyValueFactory<Aluno, Integer>("cpf"));
        alturaColumn.setCellValueFactory(new PropertyValueFactory<Aluno, Float>("altura"));
        pesoColumn.setCellValueFactory(new PropertyValueFactory<Aluno, Float>("peso"));
        dataNascColumn.setCellValueFactory(new PropertyValueFactory<Aluno, Date>("dataNasc"));
        imcColumn.setCellValueFactory(new PropertyValueFactory<Aluno, Float>("imc"));


        listAluno = alunoDao.getDataUsers();
        table.setItems(listAluno);
    }
}
