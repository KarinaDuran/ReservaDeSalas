import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Map;

import javax.swing.text.DateFormatter;



public class Console {
    Map<LocalDateTime, LocalDateTime> sobreposicoes;
    Collection<Participante> participantes;
    LocalDate inicio;
    LocalDate fim;
    DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    DateTimeFormatter formatoDia = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    Console(Collection<Participante> participantes, Map<LocalDateTime, LocalDateTime> sobreposicoes, LocalDate inicio, LocalDate fim) {
        this.participantes = participantes;
        this.sobreposicoes = sobreposicoes;
        this.inicio = inicio;
        this.fim = fim;
    }

    public void imprime(){
        System.out.println("*******************************************************************************" + "\n");
        System.out.println("O organizador dessa reuniao estipulou um prazo de " + inicio.format(formatoDia) + " a " + fim.format(formatoDia));
        System.out.println("\n" +"*******************************************************************************" + "\n");
        System.out.println(" - Lista de participantes da reuniao e suas disponibilidades:");
        for (Participante participante : participantes) {
            System.out.println(participante);
        }
        System.out.println("*******************************************************************************" + "\n");
        if (sobreposicoes.isEmpty()) {
            System.out.println(
                    "Dado a disponibilidade dos participantes, nao existe um horario em que todos os participantes possam comparecer!" + "\n");
                    System.out.println("*******************************************************************************");
                }else{
        System.out.println(
                "Dado a disponibilidade dos participantes, o(s) melhor(es) horario(s) para realizar a reuniao:");
                for (Map.Entry<LocalDateTime, LocalDateTime> sobreposicao : sobreposicoes.entrySet()){
                    System.out.println("De: " + sobreposicao.getKey().format(formatoPadrao) + " a " + sobreposicao.getValue().format(formatoPadrao));
                }
                System.out.println("\n"+"*******************************************************************************");
            }

    }
}