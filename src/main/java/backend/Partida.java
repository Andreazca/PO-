package backend;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Partida implements Serializable {
    private String id; // Identificador único da partida
    private Equipa equipaA; // Primeira equipa
    private Equipa equipaB; // Segunda equipa
    private int pontosEquipaA; // Pontos atribuídos à equipa A
    private int pontosEquipaB; // Pontos atribuídos à equipa B
    private int resultadoA; // Resultado da equipa A (gols ou pontos)
    private int resultadoB; // Resultado da equipa B (gols ou pontos)
    private LocalDateTime data; // Data e hora da partida

    // Construtor da classe Partida
    public Partida(String id, Equipa equipaA, Equipa equipaB, LocalDateTime data) {
        this.id = id;
        this.equipaA = equipaA;
        this.equipaB = equipaB;
        this.pontosEquipaA = 0; // Inicializa pontos das equipas como 0
        this.pontosEquipaB = 0;
        this.resultadoA = 0; // Inicializa resultados como 0
        this.resultadoB = 0;
        this.data = data; // Define a data da partida
    }

    // Métodos getter e setter para acessar e modificar os atributos
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Equipa getEquipaA() {
        return equipaA;
    }

    public void setEquipaA(Equipa equipaA) {
        this.equipaA = equipaA;
    }

    public Equipa getEquipaB() {
        return equipaB;
    }

    public void setEquipaB(Equipa equipaB) {
        this.equipaB = equipaB;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getPontosEquipaA() {
        return pontosEquipaA;
    }

    public void setPontosEquipaA(int pontosEquipaA) {
        this.pontosEquipaA = pontosEquipaA;
    }

    public int getPontosEquipaB() {
        return pontosEquipaB;
    }

    public void setPontosEquipaB(int pontosEquipaB) {
        this.pontosEquipaB = pontosEquipaB;
    }

    public int getResultadoA() {
        return resultadoA;
    }

    public void setResultadoA(int resultadoA) {
        this.resultadoA = resultadoA;
    }

    public int getResultadoB() {
        return resultadoB;
    }

    public void setResultadoB(int resultadoB) {
        this.resultadoB = resultadoB;
    }

    // Método para calcular os pontos com base no resultado da partida
    public void PontuarEquipas() {
        if (resultadoA == resultadoB) { // Empate
            pontosEquipaA = 1;
            pontosEquipaB = 1;
        } else if (resultadoA > resultadoB) { // Vitória da equipa A
            pontosEquipaA = 3;
            pontosEquipaB = 0;
        } else if (resultadoA < resultadoB) { // Vitória da equipa B
            pontosEquipaA = 0;
            pontosEquipaB = 3;
        }
    }

    // Método que retorna uma representação em string da partida
    @Override
    public String toString() {
        return "\nPartida\n" + equipaA + " " + resultadoA + " - " + resultadoB + " " + equipaB + "\n"
                + data.getDayOfMonth() + "/" + data.getMonthValue() + "/" + data.getYear()
                + " " + data.getHour() + ":" + data.getMinute();
    }
}
