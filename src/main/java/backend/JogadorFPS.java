package backend;

import java.io.Serializable;

/**
 * Classe que representa um jogador de FPS (First-Person Shooter), com informações adicionais sobre precisão e headshots.
 * Herda da classe Jogador.
 */
public class JogadorFPS extends Jogador implements Serializable {
    private int headshots; // Número de headshots (acertos na cabeça)
    private int tiros; // Número de tiros feitos
    private int tirosAcertados; // Número de tiros acertados


    /**
     * Construtor para inicializar o jogador de FPS com id, nome, nickname, senha, precisão e headshots.
     * Utiliza o construtor da classe base Jogador.
     */
    public JogadorFPS(String idJogador, String nome_completo, String nickname, String password) {
        super(idJogador, nome_completo, nickname, password); // Chama o construtor da classe base Jogador
        this.headshots = 0;
        this.tiros = 0;
        this.tirosAcertados = 0;
    }

    // Métodos getters e setters para acessar e modificar os atributos de precisão e headshots

    public double getPrecisao() {
        double precisao=((double) tirosAcertados /tiros)*100;
        return precisao; // Retorna a precisão do jogador
    }

    public void setHeadshots(int headshot) {
        this.headshots = headshots + headshot; // Incrementa o número de headshots
    }

    public double getHeadshots() {
        return headshots; // Retorna o número de headshots do jogador
    }

    public int getTiros() {
        return tiros;
    }

    public void setTiros(int tiros1) {
        this.tiros = tiros+tiros1;}
    public int getTirosAcertados(){
        return tirosAcertados;
    }
    public void setTirosAcertados(int tirosAcertados1){
        this.tirosAcertados=tirosAcertados1+tirosAcertados;
    }

    /**
     * Retorna uma string com os detalhes básicos do jogador, herdados da classe base Jogador.
     */
    @Override
    public String toString() {
        return super.toString(); // Chama o método toString da classe base Jogador
    }

    /**
     * Retorna uma string com detalhes completos sobre o jogador, incluindo precisão e headshots.
     */
    @Override
    public String toString2() {
        return super.toString() + "\n Precisão: " + getPrecisao() + "\n Headshots: " + headshots;
    }
}


