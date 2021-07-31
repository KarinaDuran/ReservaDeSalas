import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


class SalaNaoEncontradaException extends Exception {
    String sala;

    SalaNaoEncontradaException(String sala) {
        this.sala = sala;
    }

    public String getMessage() {
        return "Sala " + sala + " nao encontrada!";
    }
}

class SalaJaReservadaException extends Exception {
    DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    Sala sala;
    LocalDateTime inicio;
    LocalDateTime fim;

    SalaJaReservadaException(Sala sala, LocalDateTime inicio, LocalDateTime fim) {
        this.sala = sala;
        this.inicio = inicio;
        this.fim = fim;
    }

    public String getMessage() {
        return "Sala " + sala.nome() + " ja reservada entre " + inicio.format(formatoPadrao) + " e " + fim.format(formatoPadrao);
    }
}

class dataInvalidaException extends Exception {
    public String getMessage() {
        return "Data invalida!";
    }
}

class salaDuplicadaException extends Exception {
    String nome;

    salaDuplicadaException(String nome) {
        this.nome = nome;
    }

    public String getMessage() {
        return "Ja existe uma sala com o nome " + nome;
    }
}
