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


import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import static java.lang.Float.parseFloat;
import static java.time.LocalDate.parse;
import static resource.provajuliana.alunoDao.atualizarAluno;


public class alunoController implements Initializable {

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
        Aluno aluno = table.getSelectionModel().getSelectedItem();

        if(aluno != null){
            String nome =txtNome.getText();
            int cpf = Integer.parseInt(txtCpf.getText());
            float altura = Float.parseFloat(txtAltura.getText());
            float peso = parseFloat(txtPeso.getText());
            Date dataNasc = Date.valueOf(txtDataNasc.getValue());
            float imc = peso / (altura * altura);

            Aluno alunoAtualizado = new Aluno(nome,cpf,altura,peso,dataNasc, imc);

            if (atualizarAluno(alunoAtualizado)){
                int indice = listAluno.indexOf(aluno);
                listAluno.set(indice, alunoAtualizado);
            }
        }

    }

    @FXML
    void excluir(ActionEvent event) {
       Aluno aluno = table.getSelectionModel().getSelectedItem();

      if(aluno != null){
          listAluno.remove(aluno);
          alunoDao.deletarAluno(aluno);
      } else{
          System.out.println("erro");
      }
    }


    @FXML
    void salvar(ActionEvent event) {
        String nome =txtNome.getText();
        int cpf = Integer.parseInt(txtCpf.getText());
        float altura = Float.parseFloat(txtAltura.getText());
        float peso = parseFloat(txtPeso.getText());
        Date dataNasc = Date.valueOf(txtDataNasc.getValue());
        float imc = peso / (altura * altura);

        //Criando novo aluno através de instância
        Aluno aluno = new Aluno(nome,cpf,altura,peso,dataNasc,imc);
        alunoDao dao = new alunoDao();

        //acessando o método adicionar aluno
        dao.adicionarAluno(aluno);

        gerarRelatorioCliente(aluno);

        //limpando os campos de texto
        txtNome.setText("");
        txtCpf.setText("");
        txtAltura.setText("");
        txtPeso.setText("");
    }


    public void gerarRelatorioCliente(Aluno aluno){
        String nomeArquivo = aluno.getNome() + "_" + aluno.getCpf() + ".txt";

        try(FileWriter writer = new FileWriter(nomeArquivo)){
            LocalDate dataAtual = LocalDate.now();
            DateTimeFormatter formatter =  DateTimeFormatter.ofPattern("dd/MM/yyyy");

            writer.write("Relatório do Cliente\n");
            writer.write("====================\n");
            writer.write("Nome: " + aluno.getNome() + "\n");
            writer.write("CPF: " + aluno.getCpf() + "\n");
            writer.write("Data de Nascimento: " + aluno.getDataNasc().toString() + "\n");
            writer.write("Data do Relatório: " + dataAtual.format(formatter) + "\n");
            writer.write("IMC: " + aluno.getImc() + "\n");

            if (aluno.getImc() < 16.9) {
                writer.write("Status: Muito abaixo do peso\n");
            } else if (aluno.getImc() < 18.4) {
                writer.write("Status: Abaixo do peso\n");
            } else if (aluno.getImc() < 24.9) {
                writer.write("Status: Peso na média\n");
            } else if (aluno.getImc() < 29.9) {
                writer.write("Status: Acima do peso\n");
            } else if (aluno.getImc() < 34.9) {
                writer.write("Status: Obesidade grau I\n");
            } else if (aluno.getImc() < 40) {
                writer.write("Status: Obesidade grau II\n");
            } else {
                writer.write("Status: Obesidade grau III\n");
            }

            writer.write("\n");

            System.out.println("Arquivo " + nomeArquivo + " gerado com sucesso.");
        } catch (IOException e) {
            System.out.println("Erro ao gerar o arquivo: " + e.getMessage());
        }

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
