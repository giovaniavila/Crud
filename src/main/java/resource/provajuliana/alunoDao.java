package resource.provajuliana;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;

import javax.swing.*;
import java.sql.*;

import static resource.provajuliana.ConnectionFactory.getConnection;

public class alunoDao {

    static Connection conn;
    ConnectionFactory conexao;


    public alunoDao(){
        conexao= new ConnectionFactory();
        conn = getConnection();
    }

    public void adicionarAluno(Aluno aluno){
        String sql = "INSERT INTO juliana.alunos(nome, cpf, altura, peso, dataNasc,imc) VALUES(?,?,?,?,?,?)";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, (aluno.getNome()));
            stmt.setInt(2, (aluno.getCpf()));
            stmt.setFloat(3, (aluno.getAltura()));
            stmt.setFloat(4, (aluno.getPeso()));
            stmt.setDate(5, (aluno.getDataNasc()));
            stmt.setFloat(6, (aluno.getImc()));

            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao adicionar aluno " + e.getMessage());
        }
    }

    public static void deletarAluno(Aluno aluno){
        String sql = "delete from alunos where nome = ? and cpf = ? and altura = ? and peso = ? and dataNasc = ? and imc = ?";
        try{
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1, (aluno.getNome()));
            stmt.setInt(2, (aluno.getCpf()));
            stmt.setFloat(3, (aluno.getAltura()));
            stmt.setFloat(4, (aluno.getPeso()));
            stmt.setDate(5, (aluno.getDataNasc()));
            stmt.setFloat(6, (aluno.getImc()));

            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar aluno " + e.getMessage());
        }
    }


    public void atualizarAluno(Aluno aluno){
        String sql = "UPDATE `juliana`.`alunos` set`nome` = ?, set`cpf` = ?, set `altura` = ?, set`peso` = ?, set`dataNasc` = ?, set`imc` = ? where(`id_aluno` = ?);";
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, (aluno.getNome()));
            stmt.setInt(2, (aluno.getCpf()));
            stmt.setFloat(3, (aluno.getAltura()));
            stmt.setFloat(4, (aluno.getPeso()));
            stmt.setDate(5, (aluno.getDataNasc()));
            stmt.setFloat(6, (aluno.getImc()));

            stmt.execute();
            stmt.close();
        } catch(SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar aluno " + e.getMessage());
        }
    }

    public static ObservableList<Aluno> getDataUsers(){
        Connection conn = getConnection();
        ObservableList<Aluno> list  = FXCollections.observableArrayList();

        try{
            PreparedStatement ps = conn.prepareStatement("select * from alunos");
            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                list.add(new Aluno(rs.getString("nome"),rs.getInt("cpf"), rs.getFloat("altura"), rs.getFloat("peso"),rs.getDate("dataNasc"), rs.getFloat("cpf")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return list;


    }
}
