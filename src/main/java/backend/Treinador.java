package backend;

import java.io.Serializable;

public class Treinador implements Serializable {
    private String idTreinador; // ID único do treinador
    private String nome; // Nome do treinador
    private String email; // Email do treinador
    private Equipa equipagerida; // Equipa que o treinador está a gerir
    private String password; // Senha do treinador

    // Construtor que inicializa os atributos do treinador
    public Treinador(String idTreinador, String nome, String email, String password) {
        this.idTreinador = idTreinador; // Atribui o ID do treinador
        this.nome = nome; // Atribui o nome do treinador
        this.email = email; // Atribui o email do treinador
        this.password = password; // Atribui a senha do treinador
        this.equipagerida = null; // Inicializa a equipa gerida como null (ainda não atribuída)
    }

    // Métodos getters e setters para acessar e modificar os atributos do treinador

    public String getIdTreinador() {
        return idTreinador; // Retorna o ID do treinador
    }

    public void setIdTreinador(String idTreinador) {
        this.idTreinador = idTreinador; // Define o ID do treinador
    }

    public String getNome() {
        return nome; // Retorna o nome do treinador
    }

    public void setNome(String nome) {
        this.nome = nome; // Define o nome do treinador
    }

    public String getEmail() {
        return email; // Retorna o email do treinador
    }

    public void setEmail(String email) {
        this.email = email; // Define o email do treinador
    }

    public String getPassword() {
        return password; // Retorna a senha do treinador
    }

    public void setPassword(String password) {
        this.password = password; // Define a senha do treinador
    }

    public Equipa getEquipagerida() {
        return equipagerida; // Retorna a equipa que o treinador está a gerir
    }

    public void setEquipagerida(Equipa equipagerida) {
        this.equipagerida = equipagerida; // Define a equipa que o treinador está a gerir
    }

    // Método toString para representar o treinador como uma string
    public String toString(){
        return "Treinador \n nome=" + nome + "\n email=" + email ; // Exibe o nome e o email do treinador
    }
}
