import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Reserva {
    private Sala sala;
    private LocalDateTime inicio;
    private LocalDateTime fim;
    DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public Reserva(Sala sala, LocalDateTime inicio, LocalDateTime fim){
        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
    }
    public Sala getSala() {
        return sala;
    }

    public LocalDateTime getInicio() {
        return inicio;
    }

    public LocalDateTime getFim() {
        return fim;
    }
    @Override
    public String toString(){
        return ("Sala: " + sala.getNome() + ", Horario reservado: inicio em " + inicio.format(formatoPadrao)+ " e término em " + fim.format(formatoPadrao));
    }
}
