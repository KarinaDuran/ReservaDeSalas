import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GerenciadorDeSalas {
    private List<Sala> todaSalas = new ArrayList<Sala>();
    private Collection<Reserva> todasReservas = new ArrayList<Reserva>(); 

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        Sala novaSala = new Sala (nome, descricao, capacidadeMaxima);
        adicionaSala(novaSala);
    }

    public void removeSalaChamada(String nomeDaSala) {
        for (int i =0; i<todaSalas.size(); i++){
            if(todaSalas.get(i).getNome() == nomeDaSala){
                todaSalas.remove(i);
            }
        }
    }

    public List<Sala> listaDeSalas() {
        return todaSalas;
    }

    public void adicionaSala(Sala novaSala) {
        todaSalas.add(novaSala);
    }

    public Reserva reservaSalaChamada(String nomeDaSala, LocalDateTime dataInicial, LocalDateTime dataFinal)
            throws Exception {
            if(dataInicial.isAfter(dataFinal)){
                throw new Exception();
            }
            
            try{
            
            Sala aux = new Sala ("", "", -1);
            for (int i =0; i<todaSalas.size(); i++){
                if(todaSalas.get(i).getNome() == nomeDaSala){
                    aux = todaSalas.get(i);
                }
            }

            Reserva novareReserva = new Reserva(aux, dataInicial, dataFinal);
            todasReservas.add(novareReserva);
            return novareReserva;
        }catch(Exception e){
            throw new Exception();
        }

           

    }

    public void cancelaReserva(Reserva cancelada) {
        if(todasReservas.contains(cancelada)){
            todasReservas.remove(cancelada);
        }
    }

    public Collection<Reserva> reservasParaSala(String nomeSala) {
        Collection<Reserva> reservasDaSala = new ArrayList<>();
        Iterator <Reserva> iterator = todasReservas.iterator();
        Reserva aux;
        while(iterator.hasNext()){
            aux = iterator.next(); 
            if(aux.sala().getNome() == nomeSala){
                reservasDaSala.add(aux);
            }
        }
        return reservasDaSala;
    }

    public void imprimeReservasDaSala(String nomeSala) {
        Iterator <Reserva> iterator = todasReservas.iterator();
        Reserva aux;
        System.out.println(nomeSala);
        while(iterator.hasNext()){
            aux = iterator.next(); 
            if(aux.sala().getNome() == nomeSala){
                System.out.println(aux.toString());
            }
    
    }
    }
}