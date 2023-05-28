package br.ufrb.edu.gcet236.sigrh.services;

import java.util.ArrayList;
import java.util.List;

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

    public List<Enfermeiro> getColaboradores() {
        return this.enfermeiroRepository.findAll();
    }

    public void cadastrarColaboradores(Enfermeiro enfermeiro) {
        this.enfermeiroRepository.save(enfermeiro);

    }

    public void editarColaboradores(Enfermeiro enfermeiro) {
        Enfermeiro e = this.enfermeiroRepository.findByCpf(enfermeiro.getCpf()).get(0);
        e.setNome(enfermeiro.getNome());
        e.setRg(enfermeiro.getRg());
        e.setTelefone(enfermeiro.getTelefone());
        e.setLotacao(enfermeiro.getLotacao());
        
        this.enfermeiroRepository.save(e);
    }

    public void removerColaboradores(Enfermeiro enfermeiro) {
        Enfermeiro e = this.enfermeiroRepository.findByCpf(enfermeiro.getCpf()).get(0);
        
        this.enfermeiroRepository.delete(e);
    }

    public ArrayList<Enfermeiro> buscarPorNome(String nome) {
        return this.enfermeiroRepository.findByNomeContaining(nome);
    }

    
    public ArrayList<Enfermeiro> buscarPorCPF(String cpf) {
        return this.enfermeiroRepository.findByCpf(cpf);
    }

    public ArrayList<Enfermeiro> buscarPorRG(String rg) {
        return this.enfermeiroRepository.findByRg(rg);
    }

    public ArrayList<Enfermeiro> buscarPorTelefone(String telefone){
        return this.enfermeiroRepository.findByTelefone(telefone);
    }


    public ArrayList<Enfermeiro> buscarPorLotacao(String lotacao){
        return this.enfermeiroRepository.findByLotacaoContaining(lotacao);
    }
}
