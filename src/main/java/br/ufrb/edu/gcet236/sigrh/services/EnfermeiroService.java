package br.ufrb.edu.gcet236.sigrh.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import br.ufrb.edu.gcet236.sigrh.DatabaseConnection;

import br.ufrb.edu.gcet236.sigrh.entities.Enfermeiro;
import br.ufrb.edu.gcet236.sigrh.entities.Pessoa;
import br.ufrb.edu.gcet236.sigrh.repositories.EnfermeiroRepository;

import java.sql.*;

@Service
public class EnfermeiroService {
    // ...
    @Autowired
    EnfermeiroRepository enfermeiroRepository;
    public ArrayList<Enfermeiro> getColaboradores() {
        ArrayList<Enfermeiro> enfermeiros = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM enfermeiros")) {

            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String nome = resultSet.getString("nome");
                String cpf = resultSet.getString("cpf");
                String rg = resultSet.getString("rg");
                String telefone = resultSet.getString("telefone");
                String lotacao = resultSet.getString("lotacao");

                Enfermeiro enfermeiro = new Enfermeiro(nome, cpf, rg, telefone, lotacao);
                enfermeiro.setCpf(id);

                enfermeiros.add(enfermeiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return enfermeiros;
    }
        public void cadastrarColaboradores(Enfermeiro enfermeiro) {
            this.enfermeiroRepository.add(enfermeiro);
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "INSERT INTO enfermeiros (nome, cpf, rg, telefone, lotacao) VALUES (?, ?, ?, ?, ?)")) {
    
                statement.setString(1, enfermeiro.getNome());
                statement.setLong(2, enfermeiro.getCpf());
                statement.setString(3, enfermeiro.getRg());
                statement.setString(4, enfermeiro.getTelefone());
                statement.setString(5, enfermeiro.getLotacao());
    
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        public void editarColaboradores(Enfermeiro enfermeiro) {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "UPDATE enfermeiros SET nome = ?, cpf = ?, rg = ?, telefone = ?, lotacao = ? WHERE id = ?")) {
    
                statement.setString(1, enfermeiro.getNome());
                statement.setString(2, enfermeiro.getCpf());
                statement.setString(3, enfermeiro.getRg());
                statement.setString(4, enfermeiro.getTelefone());
                statement.setString(5, enfermeiro.getLotacao());
                statement.setString(6, enfermeiro.getCpf());
    
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        public void removerColaboradores(Enfermeiro enfermeiro) {
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "DELETE FROM enfermeiros WHERE id = ?")) {
    
                statement.setInt(1, enfermeiro.getCpf());
    
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    
        public ArrayList<Pessoa> buscarPorNome(String nome) {
            ArrayList<Enfermeiro> resultados = new ArrayList<>();
    
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT * FROM enfermeiros WHERE nome LIKE ?")) {
    
                statement.setString(1, "%" + nome + "%");
    
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String enfermeiroNome = resultSet.getString("nome");
                        String enfermeiroCPF = resultSet.getString("cpf");
                        String enfermeiroRg = resultSet.getString("rg");
                        String enfermeiroTelefone = resultSet.getString("telefone");
                        String enfermeiroLotacao = resultSet.getString("lotacao");
    
                        Enfermeiro enfermeiro = new Enfermeiro(enfermeiroNome, enfermeiroCPF, enfermeiroRg, enfermeiroTelefone, enfermeiroLotacao);
                        enfermeiro.setCpf(id);
    
                        resultados.add(enfermeiro);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public ArrayList<Pessoa> buscarPorCPF(String cpf) {
            ArrayList<Enfermeiro> resultados = new ArrayList<>();
    
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT * FROM enfermeiros WHERE cpf LIKE ?")) {
    
                statement.setString(1, "%" + cpf + "%");
    
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String enfermeiroNome = resultSet.getString("nome");
                        String enfermeiroCPF = resultSet.getString("cpf");
                        String enfermeiroRg = resultSet.getString("rg");
                        String enfermeiroTelefone = resultSet.getString("telefone");
                        String enfermeiroLotacao = resultSet.getString("lotacao");
    
                        Enfermeiro enfermeiro = new Enfermeiro(enfermeiroNome, enfermeiroCPF, enfermeiroRg, enfermeiroTelefone, enfermeiroLotacao);
                        enfermeiro.setCpf(id);
    
                        resultados.add(enfermeiro);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public ArrayList<Pessoa> buscarPorRG(String rg) {
            ArrayList<Enfermeiro> resultados = new ArrayList<>();
    
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT * FROM enfermeiros WHERE rg LIKE ?")) {
    
                statement.setString(1, "%" + rg + "%");
    
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String enfermeiroNome = resultSet.getString("nome");
                        String enfermeiroCPF = resultSet.getString("cpf");
                        String enfermeiroRg = resultSet.getString("rg");
                        String enfermeiroTelefone = resultSet.getString("telefone");
                        String enfermeiroLotacao = resultSet.getString("lotacao");

                        Enfermeiro enfermeiro = new Enfermeiro(enfermeiroNome, enfermeiroCPF, enfermeiroRg, enfermeiroTelefone, enfermeiroLotacao);
                        enfermeiro.setCpf(id);
    
                        resultados.add(enfermeiro);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }

        public ArrayList<Pessoa> buscarPorTelefone(String telefone){
            ArrayList<Enfermeiro> resultados = new ArrayList<>();
    
            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(
                         "SELECT * FROM enfermeiros WHERE telefone LIKE ?")) {
    
                statement.setString(1, "%" + telefone + "%");
    
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String enfermeiroNome = resultSet.getString("nome");
                        String enfermeiroCPF = resultSet.getString("cpf");
                        String enfermeiroRg = resultSet.getString("rg");
                        String enfermeiroTelefone = resultSet.getString("telefone");
                        String enfermeiroLotacao = resultSet.getString("lotacao");
    
                        Enfermeiro enfermeiro = new Enfermeiro(enfermeiroNome, enfermeiroCPF, enfermeiroRg, enfermeiroTelefone, enfermeiroLotacao);
                        enfermeiro.setCpf(id);
    
                        resultados.add(enfermeiro);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        }
            public ArrayList<Pessoa> buscarPorLotacao(String lotacao){
                ArrayList<Enfermeiro> resultados = new ArrayList<>();
        
                try (Connection connection = DatabaseConnection.getConnection();
                     PreparedStatement statement = connection.prepareStatement(
                             "SELECT * FROM enfermeiros WHERE telefone LIKE ?")) {
        
                    statement.setString(1, "%" + lotacao + "%");
        
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            int id = resultSet.getInt("id");
                            String enfermeiroNome = resultSet.getString("nome");
                            String enfermeiroCPF = resultSet.getString("cpf");
                            String enfermeiroRg = resultSet.getString("rg");
                            String enfermeiroTelefone = resultSet.getString("telefone");
                            String enfermeiroLotacao = resultSet.getString("lotacao");
        
                            Enfermeiro enfermeiro = new Enfermeiro(enfermeiroNome, enfermeiroCPF, enfermeiroRg, enfermeiroTelefone, enfermeiroLotacao);
                            enfermeiro.setCpf(id);
        
                            resultados.add(enfermeiro);
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;

            }
    }
