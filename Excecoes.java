
class SalaNaoEncontradaException extends Exception {
    String sala;

    SalaNaoEncontradaException(String sala) {
        this.sala = sala;
    }

    public String getMessage() {
        return "Sala " + sala + " não encontrada!";
    }
}

class SalaJaReservadaException extends Exception {
    Sala sala;

    SalaJaReservadaException(Sala sala) {
        this.sala = sala;
    }

    public String getMessage() {
        return "Sala " + sala.nome() + " já reservada neste horario!";
    }
}

class dataInvalidaException extends Exception {
    public String getMessage() {
        return "Data inválida!";
    }
}

class salaDuplicadaException extends Exception {
    String nome;

    salaDuplicadaException(String nome) {
        this.nome = nome;
    }

    public String getMessage() {
        return "Já existe uma sala com o nome " + nome;
    }
}
