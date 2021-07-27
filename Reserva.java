import java.time.LocalDateTime;

public class Reserva {
    private Sala sala;
    private LocalDateTime inicio;
    private LocalDateTime fim;

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
        return ("Nome da sala: " + sala.getNome() + " Horario reservado: " + inicio + " " + fim);
    }
}
