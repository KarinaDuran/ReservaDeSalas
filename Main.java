import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {

        // Exemplos de uso dos metodos de GerenciadorDeSalas
        GerenciadorDeSalas gerenciador = new GerenciadorDeSalas();
        gerenciador.adicionaSalaChamada("Sala 1", 50, "Sala de conversas");
        gerenciador.adicionaSalaChamada("Sala 2", 78, "Sala de jogos");
        gerenciador.adicionaSalaChamada("Sala 3", 60, "Sala de cinema");
        gerenciador.adicionaSalaChamada("Sala 4", 42, "Sala de reuniao");

        gerenciador.removeSalaChamada("Sala 3");

        List<Sala> salas = gerenciador.listaDeSalas();

        LocalDateTime x = LocalDateTime.of(2021, 9, 14, 13, 40);
        LocalDateTime y = LocalDateTime.of(2021, 9, 16, 13, 40);
        LocalDateTime z = LocalDateTime.of(2021, 8, 21, 13, 40);
        LocalDateTime d = LocalDateTime.of(2021, 8, 26, 13, 40);

        Reserva reserva1 = gerenciador.reservaSalaChamada("Sala 1", x, y);
        Reserva reserva2 = gerenciador.reservaSalaChamada("Sala 2", x, y);
        Reserva reserva3 = gerenciador.reservaSalaChamada("Sala 2", z, d);

        gerenciador.cancelaReserva(reserva1);

        Collection<Reserva> reservas = gerenciador.reservasParaSala("Sala 2");

        gerenciador.imprimeReservasDaSala("Sala 2");

        // Exemplos dos métodos de MarcadorDeReuniao

        MarcadorDeReuniao mReuniao = new MarcadorDeReuniao();

        LocalDate inicio = LocalDate.of(2021, 8, 20);
        LocalDate fim = LocalDate.of(2021, 9, 20);

        Collection<String> participantes = new ArrayList<String>();
        participantes.add("Felipe");
        participantes.add("Cristiane");
        participantes.add("Zoe");
        participantes.add("Karina");
        participantes.add("Marcos");
        participantes.add("Marcia");
        participantes.add("Antonio");
        participantes.add("Junior");

        mReuniao.marcarReuniaoEntre(inicio, fim, participantes);

        mReuniao.indicaDisponibilidadeDe("Felipe", x, y);
        mReuniao.indicaDisponibilidadeDe("Cristiane", x, y);
        mReuniao.indicaDisponibilidadeDe("Zoe", x, y);
        mReuniao.indicaDisponibilidadeDe("Marcos", x, y);
        mReuniao.indicaDisponibilidadeDe("Karina", x, y);
        mReuniao.indicaDisponibilidadeDe("Marcia", x, y);
        mReuniao.indicaDisponibilidadeDe("Antonio", x, y);
        mReuniao.indicaDisponibilidadeDe("Junior", x, y);

        mReuniao.mostraSobreposicao();
    }

}