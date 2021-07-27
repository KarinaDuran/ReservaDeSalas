import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

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
        return "Sala " + sala.getNome() + " já reservada neste horario!";
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

public class GerenciadorDeSalas {
    private List<Sala> todaSalas = new ArrayList<Sala>();
    private Collection<Reserva> todasReservas = new ArrayList<Reserva>();

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        // Adicionar exceção de sala com o mesmo nome
        try {
            for (int i = 0; i < todaSalas.size(); i++) {
                if (todaSalas.get(i).getNome() == nome) {
                    throw new salaDuplicadaException(nome);
                }
            }
        } catch (salaDuplicadaException e) {
            System.out.println(e.getMessage());
            return;
        }

        Sala novaSala = new Sala(nome, descricao, capacidadeMaxima);

        adicionaSala(novaSala);
    }

    public void removeSalaChamada(String nomeDaSala) throws SalaNaoEncontradaException {
        try {
            boolean salaEncontrada = false;
            for (int i = 0; i < todaSalas.size(); i++) {
                if (todaSalas.get(i).getNome() == nomeDaSala) {
                    salaEncontrada = true;
                    todaSalas.remove(i);
                }
            }
            if (!salaEncontrada) {
                throw new SalaNaoEncontradaException(nomeDaSala);
            }

        } catch (SalaNaoEncontradaException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Sala> listaDeSalas() {
        return todaSalas;
    }

    public void adicionaSala(Sala novaSala) {
        todaSalas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal)
            throws SalaNaoEncontradaException, SalaJaReservadaException {
        Reserva novareReserva = null;
        Sala aux = null;
        try {
            if (dataInicial.isAfter(dataFinal))
                throw new dataInvalidaException();
        } catch (dataInvalidaException e) {
            System.out.println(e.getMessage());
            return null;
        }

        try {
            for (int i = 0; i < todaSalas.size(); i++) {
                if (todaSalas.get(i).getNome() == nomeDaSala) {
                    aux = todaSalas.get(i);
                }
            }
            if (aux == null) {
                throw new SalaNaoEncontradaException(nomeDaSala);
            }
        } catch (SalaNaoEncontradaException e) {
            e.getMessage();
            return null;
        }

        try {
            Iterator<Reserva> iterator = todasReservas.iterator();
            while (iterator.hasNext()) {
                Reserva x = iterator.next();
                if (x.getSala().getNome() == nomeDaSala) {
                    if (x.getFim().isAfter(dataInicial) && x.getInicio().isBefore(dataFinal)) {
                        throw new SalaJaReservadaException(x.getSala());

                    }
                }
            }
            novareReserva = new Reserva(aux, dataInicial, dataFinal);
            todasReservas.add(novareReserva);

            return novareReserva;
        } catch (SalaJaReservadaException e) {
            e.getMessage();
            return null;
        }
    }

    public void cancelaReserva(Reserva cancelada) {
        if (todasReservas.contains(cancelada)) {
            todasReservas.remove(cancelada);
        } else {
            System.out.println("Reserva não encontrada");
        }
    }

    public Collection<Reserva> reservasParaSala(String nomeSala) {
        Collection<Reserva> reservasDaSala = new ArrayList<>();
        Iterator<Reserva> iterator = todasReservas.iterator();
        Reserva aux;
        boolean salaExiste = false;
        try {
            for (int i = 0; i < todaSalas.size(); i++) {
                if (todaSalas.get(i).getNome() == nomeSala) {
                    salaExiste = true;
                }
            }
            if (!salaExiste) {
                throw new SalaNaoEncontradaException(nomeSala);
            }
        } catch (SalaNaoEncontradaException e) {
            System.out.println(e.getMessage());
            return null;
        }

        while (iterator.hasNext()) {
            aux = iterator.next();
            if (aux.getSala().getNome() == nomeSala) {
                reservasDaSala.add(aux);
            }
        }
        return reservasDaSala;
    }

    public void imprimeReservasDaSala(String nomeSala) {
        Collection<Reserva> reservasDaSala = reservasParaSala(nomeSala);
        if (reservasDaSala != null) {
            System.out.println(reservasDaSala);
        }
    }
}