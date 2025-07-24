package backend;

import java.io.Serializable;

/**
 * Classe que representa um administrador do sistema.
 */
public class Administrador implements Serializable {
    private String nome; // Nome do administrador
    private String email; // Email do administrador
    private String password; // Senha do administrador
    private String idAdministrador; // ID único do administrador

    /**
     * Construtor para inicializar um administrador.
     */
    public Administrador(String idAdministrador, String nome, String email, String password) {
        this.idAdministrador = idAdministrador;
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    // Métodos getters e setters para acessar e modificar os atributos

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIdAdministrador() {
        return idAdministrador;
    }

    public void setIdAdministrador(String idAdministrador) {
        this.idAdministrador = idAdministrador;
    }

    /**
     * Retorna uma representação em string do administrador.
     */
    public String toString() {
        return "Administrador\nnome=" + nome + "\nemail=" + email;
    }
}

