import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GerenciadorDeSalas {
    private List<Sala> todaSalas = new ArrayList<Sala>();
    private Collection<Reserva> todasReservas = new ArrayList<Reserva>();

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
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

    public List<Sala> listaDeSalas() {
        return todaSalas;
    }

    public void adicionaSala(Sala novaSala) {
        todaSalas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal)
            throws SalaNaoEncontradaException, SalaJaReservadaException, dataInvalidaException {
        Reserva novareReserva = null;
        Sala aux = null;
        if (dataInicial.isAfter(dataFinal)) {
            throw new dataInvalidaException();
        }

        for (int i = 0; i < todaSalas.size(); i++) {
            if (todaSalas.get(i).nome() == nomeDaSala) {
                aux = todaSalas.get(i);
            }
        }
        if (aux == null) {
            throw new SalaNaoEncontradaException(nomeDaSala);
        }

        Iterator<Reserva> iterator = todasReservas.iterator();
        while (iterator.hasNext()) {
            Reserva x = iterator.next();
            if (x.getSala().nome() == nomeDaSala) {
                int a = x.getInicio().compareTo(dataInicial);
                int b= x.getFim().compareTo(dataFinal);
                
                if( (a <= 0 )

                }
            }
    
        novareReserva = new Reserva(aux, dataInicial, dataFinal);
        todasReservas.add(novareReserva);

        return novareReserva;
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

        while (iterator.hasNext()) {
            aux = iterator.next();
            if (aux.getSala().nome() == nomeSala) {
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