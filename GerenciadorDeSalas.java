import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GerenciadorDeSalas {
    // List com todas as salas
    private List<Sala> todaSalas = new ArrayList<Sala>();
    // Collection com todas as reservas
    private Collection<Reserva> todasReservas = new ArrayList<Reserva>();

    // Metodo de adicionar uma sala
    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        // Verifica se a sala nao foi duplicada
        try {
            for (int i = 0; i < todaSalas.size(); i++) {
                if (todaSalas.get(i).nome() == nome) {
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

    // Metodo para remover uma sala
    public void removeSalaChamada(String nomeDaSala) throws SalaNaoEncontradaException {
        try {
            boolean salaEncontrada = false;
            for (int i = 0; i < todaSalas.size(); i++) {
                if (todaSalas.get(i).nome() == nomeDaSala) {
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

    // Metodo de acesso a lista de todas as salas
    public List<Sala> listaDeSalas() {
        return todaSalas;
    }

    public void adicionaSala(Sala novaSala) {
        todaSalas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal)
            throws SalaNaoEncontradaException, SalaJaReservadaException, dataInvalidaException {
        // Verificacao de data invalida
        if (dataInicial.isAfter(dataFinal)) {
            throw new dataInvalidaException();
        }
        Sala aux = null;
        Reserva novareReserva;
        // Busca da sala
        for (int i = 0; i < todaSalas.size(); i++) {
            if (todaSalas.get(i).nome() == nomeDaSala) {
                aux = todaSalas.get(i);
            }
        }
        // Se nao encontrada, lanca excecao
        if (aux == null) {
            throw new SalaNaoEncontradaException(nomeDaSala);
        }
        // Verifica se existe alguma reserva no horario
        Iterator<Reserva> iterator = todasReservas.iterator();
        while (iterator.hasNext()) {
            Reserva x = iterator.next();
            if (x.getSala().nome() == nomeDaSala) {
                // Imprimir melhor os horarios
                if ((x.getInicio().compareTo(dataInicial) <= 0 && x.getFim().compareTo(dataInicial) >= 0)
                        || (x.getFim().compareTo(dataFinal) >= 0 && x.getInicio().compareTo(dataFinal) <= 0)) {
                    throw new SalaJaReservadaException(x.getSala(), x.getInicio(), x.getFim());

                }

            }
        }

        novareReserva = new Reserva(aux, dataInicial, dataFinal);
        todasReservas.add(novareReserva);

        return novareReserva;
    }

    // Metodo para cancelar reserva
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
        // Se a sala nao existir retorna null e trata excecao
        try {
            for (int i = 0; i < todaSalas.size(); i++) {
                if (todaSalas.get(i).nome() == nomeSala) {
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

        // Adiciona na collection as reservas da sala
        while (iterator.hasNext()) {
            aux = iterator.next();
            if (aux.getSala().nome() == nomeSala) {
                reservasDaSala.add(aux);
            }
        }
        if (reservasDaSala.isEmpty())
            System.out.println("A sala " + nomeSala + " nao contem reservas!");

        return reservasDaSala;
    }

    // Metodo para imprimir as reservas de uma determinada sala
    public void imprimeReservasDaSala(String nomeSala) {
        Collection<Reserva> reservasDaSala = reservasParaSala(nomeSala);
        if (reservasDaSala != null && !reservasDaSala.isEmpty()) {
            for (Reserva reserva : reservasDaSala) {
                System.out.println(reserva);
            }
        }
    }
}