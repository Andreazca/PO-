package backend;

import java.io.Serializable;
/**
 * Classe que representa uma equipa de FPS, que herda de Equipa.
 */
public class EquipaFPS extends Equipa implements Serializable {
    /**
     * Construtor para inicializar a equipa de FPS com id, nome e treinador.
     * Utiliza o construtor da classe base Equipa.
     */
    public EquipaFPS(String id, String nome,Treinador treinador) {
        super(id, nome, treinador);
    }



}
