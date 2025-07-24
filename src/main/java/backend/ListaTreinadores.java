package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ListaTreinadores implements Serializable {
    List<Treinador> lt;

    // Construtor que inicializa a lista de treinadores
    public ListaTreinadores() {
        lt = new ArrayList<>();
    }

    // Retorna a lista de treinadores
    public List<Treinador> getLt() {
        return lt;
    }

    // Adiciona um treinador à lista
    public void adicionartreinador(Treinador t) {
        lt.add(t);
    }

    // Remove um treinador da lista
    public void removerTreinador(Treinador t) {
        lt.remove(t);
    }

    // Verifica se já existe um treinador com o email fornecido
    public boolean verificaremail(String email) {
        for (Treinador treinador : lt) {
            if (treinador.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    // Cria um novo treinador e o adiciona à lista
    public void criarTreinador(String nome, String email, String password) {
        String id = UUID.randomUUID().toString();  // Gera um ID único
        Treinador treina = new Treinador(id, nome, email, password);
        adicionartreinador(treina);
    }

    // Valida o login de um treinador verificando o email e a senha
    public boolean validarlogin(String email, String password) {
        for (Treinador treinador : lt) {
            if (treinador.getEmail().equals(email) && treinador.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    // Encontra o ID do treinador a partir do email
    public String encontrarid(String email) {
        for (Treinador treinador : lt) {
            if (treinador.getEmail().equals(email)) {
                return treinador.getIdTreinador();
            }
        }
        return null;
    }
    // Encontra o treinador pelo id
    public Treinador encontrarTreinadorid(String id) {
        for (Treinador treinador : lt) {
            if (treinador.getIdTreinador().equals(id)) {
                return treinador;
            }
        }
        return null;
    }


    // Encontra o treinador pelo email
    public Treinador encontrarTreinador(String email) {
        for (Treinador treinador : lt) {
            if (treinador.getEmail().equals(email)) {
                return treinador;
            }
        }
        return null;
    }

    // Verifica se o treinador possui uma equipa associada
    public boolean verificarEquipa(String id) {
        for (Treinador treinador : lt) {
            if (treinador.getIdTreinador().equals(id)) {
                if (treinador.getEquipagerida() == null) {
                    return false;
                }
            }
        }
        return true;
    }

    // Encontra a equipa associada ao treinador pelo ID
    public String encontrarEquipa(String id) {
        for (Treinador treinador : lt) {
            if (treinador.getIdTreinador().equals(id)) {
                Equipa e = treinador.getEquipagerida();
                return e != null ? e.getId() : null;
            }
        }
        return null;
    }

    // Encontra o nome da equipa associada ao treinador pelo ID
    public String encontrarEquipaNome(String id) {
        for (Treinador treinador : lt) {
            if (treinador.getIdTreinador().equals(id)) {
                Equipa e = treinador.getEquipagerida();
                return e != null ? e.getNome() : null;
            }
        }
        return null;
    }

    // Lista todos os treinadores sem equipa associada
    public List<Treinador> listarTreinadores() {
        List<Treinador> treinadores = new ArrayList<>();
        if (lt.isEmpty()) {
            return null;  // Se não houver treinadores, retorna null
        }
        for (Treinador treinador : lt) {
            if (treinador.getEquipagerida() == null) {
                treinadores.add(treinador);  // Adiciona treinadores sem equipa
            }
        }
        return treinadores.isEmpty() ? null : treinadores;  // Retorna null se não houver treinadores sem equipa
    }
}





