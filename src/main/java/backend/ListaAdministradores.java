package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe que representa uma lista de administradores. Ela permite adicionar, remover,
 * verificar a existência de administradores e validar o login com email e senha.
 */
public class ListaAdministradores implements Serializable {
    private List<Administrador> la; // Lista de administradores
    private int id; // ID da lista (não utilizado na classe)

    /**
     * Construtor que inicializa a lista de administradores como uma ArrayList.
     */
    public ListaAdministradores() {
        la = new ArrayList<Administrador>();
    }

    /**
     * Retorna a lista de administradores.
     */
    public List<Administrador> getAdministrador() {
        return la;
    }

    /**
     * Adiciona um administrador à lista.
     */
    public void adicionarAdministrador(Administrador a) {
        la.add(a);
    }

    /**
     * Remove um administrador da lista.
     */
    public void removerAdministrador(Administrador a) {
        la.remove(a);
    }

    /**
     * Verifica se já existe um administrador com o email fornecido.
     *
     * @param email O email do administrador a ser verificado
     * @return true se o email já estiver registrado, false caso contrário
     */
    public boolean verificaremail(String email) {
        for (Administrador administrador : la) {
            if (administrador.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Valida o login de um administrador com base no email e senha fornecidos.
     *
     * @param email O email do administrador
     * @param password A senha do administrador
     * @return true se as credenciais estiverem corretas, false caso contrário
     */
    public boolean validarlogin(String email, String password) {
        for (Administrador administrador : la) {
            if (administrador.getEmail().equals(email) && administrador.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Encontra o ID de um administrador com base no email fornecido.
     *
     * @param email O email do administrador
     * @return O ID do administrador se encontrado, ou null se não encontrado
     */
    public String encontrarid(String email) {
        for (Administrador administrador : la) {
            if (administrador.getEmail().equals(email)) {
                return administrador.getIdAdministrador();
            }
        }
        return null;
    }

    /**
     * Encontra um administrador com base no seu ID.
     *
     * @param id O ID do administrador
     * @return O objeto Administrador correspondente ao ID fornecido, ou null se não encontrado
     */
    public Administrador encontrarAdministrador(String id) {
        for (Administrador administrador : la) {
            if (administrador.getIdAdministrador().equals(id)) {
                return administrador;
            }
        }
        return null;
    }
}



