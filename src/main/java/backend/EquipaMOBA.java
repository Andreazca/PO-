package backend;

import java.io.Serializable;
/**
 * Classe que representa uma equipa de MOBA, que herda de Equipa.
 */
public class EquipaMOBA extends Equipa implements Serializable {

    /**
     * Construtor para inicializar a equipa de MOBA com id, nome e treinador.
     * Utiliza o construtor da classe base Equipa.
     */
    public EquipaMOBA(String id, String nome,Treinador treinador) {
        super(id,nome,treinador);
    }

}
