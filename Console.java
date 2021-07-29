import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;

public class Console {
    Map<LocalDateTime, LocalDateTime> sobreposicoes;
    Collection<Participante> participantes;
    LocalDate inicio;
    LocalDate fim;
    DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    Console(Collection<Participante> participantes, Map<LocalDateTime, LocalDateTime> sobreposicoes, LocalDate inicio, LocalDate fim) {
        this.participantes = participantes;
        this.sobreposicoes = sobreposicoes;
        this.inicio = inicio;
        this.fim = fim;
    }

    public void imprime(){
        System.out.println("O organizador dessa reunião estipulou um prazo de " + inicio + " a " + fim);
        System.out.println("Lista de participantes da reuniao e suas disponibilidades:");
        for (Participante participante : participantes) {
            System.out.println(participante);
            
        }

        if (sobreposicoes.isEmpty()) {
            System.out.println(
                    "Dado a disponibilidade dos participantes, não existe um horário em que todos os participantes possam comparecer!");
        }
        System.out.println(
                "Dado a disponibilidade dos participantes, o(s) melhor(es) horário(s) para realizar a reunião:");
                for (Map.Entry<LocalDateTime, LocalDateTime> sobreposicao : sobreposicoes.entrySet()){
                    System.out.println("De: " + sobreposicao.getKey().format(formatoPadrao) + " a " + sobreposicao.getValue().format(formatoPadrao));
                }

    }
}