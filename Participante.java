import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class Participante {
    String nome;

    DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    Map<LocalDateTime, LocalDateTime> disponibilidade = new HashMap<>();

    Participante(String nome) {
        this.nome = nome;
    }

    public void adicionarHorario(LocalDateTime inicio, LocalDateTime fim) {
        if (disponibilidade.containsKey(inicio)) {
            if (disponibilidade.get(inicio).isBefore(fim)) {
                disponibilidade.replace(inicio, fim);
            }
        } else {
            disponibilidade.put(inicio, fim);
        }
    }

    @Override
    public String toString() {
        String aux = "Nome do participante: " + nome + "\n" + "Horarios disponíveis: ";
        for (LocalDateTime inicio : disponibilidade.keySet()) {
            LocalDateTime fim = disponibilidade.get(inicio);
            aux += "\n" + "De: " + inicio.format(formatoPadrao) + " a " + fim.format(formatoPadrao) ;
        }
        aux +="\n";
        return aux;
    }
}