package backend;

import java.io.Serializable;

/**
 * Classe que representa uma equipa de eFootball, que herda de Equipa.
 */
public class EquipaEfootball extends Equipa implements Serializable {

    /**
     * Construtor para inicializar a equipa de eFootball com id, nome e treinador.
     * Utiliza o construtor da classe base Equipa.
     */
    public EquipaEfootball(String id, String nome, Treinador treinador) {
        super(id, nome, treinador); // Chama o construtor da classe base Equipa
    }
}
