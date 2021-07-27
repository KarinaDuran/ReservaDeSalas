import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class GerenciadorDeSalas {
    private List<Sala> todaSalas = new ArrayList<Sala>();
    private Collection<Reserva> todasReservas = new ArrayList<Reserva>(); 

    public void adicionaSalaChamada(String nome, int capacidadeMaxima, String descricao) {
        //Adicionar exceção de sala com o mesmo nome
        Sala novaSala = new Sala (nome, descricao, capacidadeMaxima);
        adicionaSala(novaSala);
    }

    public void removeSalaChamada(String nomeDaSala) {
        //Adicionar exceção de sala nao existente
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
                Reserva novareReserva = null;
         try{   
             if(dataInicial.isAfter(dataFinal)){
                throw new Exception();
            }
             Sala aux = null;
            for (int i =0; i<todaSalas.size(); i++){
                if(todaSalas.get(i).getNome() == nomeDaSala){
                      aux = todaSalas.get(i);
                }
            }
            if(aux ==null){
                throw new Exception();
            }
            Iterator <Reserva> iterator = todasReservas.iterator();
            while(iterator.hasNext()){
                Reserva x = iterator.next();
                if(x.getSala().getNome() == nomeDaSala){
                    if(x.getFim().isAfter(dataInicial) && x.getInicio().isBefore(dataFinal)){
                        throw new Exception();

                    }
                }
            }

           novareReserva = new Reserva(aux, dataInicial, dataFinal);
            todasReservas.add(novareReserva);
            
        }catch(Exception e){
            System.out.println("Sala não encontrada!");
            throw new Exception();
        }

         return novareReserva;  

    }

    public void cancelaReserva(Reserva cancelada) {
        if(todasReservas.contains(cancelada)){
            todasReservas.remove(cancelada);
        }else{
            System.out.println("Reserva não encontrada");
        }
    }

    public Collection<Reserva> reservasParaSala(String nomeSala) {
        Collection<Reserva> reservasDaSala = new ArrayList<>();
        Iterator <Reserva> iterator = todasReservas.iterator();
        Reserva aux;
        while(iterator.hasNext()){
            aux = iterator.next(); 
            if(aux.getSala().getNome() == nomeSala){
                reservasDaSala.add(aux);
            }
        }
        return reservasDaSala;
    }

    public void imprimeReservasDaSala(String nomeSala) {
        Iterator <Reserva> iterator = todasReservas.iterator();
        Reserva aux=null;
        boolean salaExiste = false;

        for (int i =0; i<todaSalas.size(); i++){
            if(todaSalas.get(i).getNome() == nomeSala){
                 salaExiste = true;
            }
        }
        if(salaExiste){

        System.out.println("Nome da sala: " + nomeSala);
        boolean salaComReservas = false;
        while(iterator.hasNext()){
            aux = iterator.next(); 
            if(aux.getSala().getNome() == nomeSala){
                System.out.println(aux.toString());
                salaComReservas = true;
            }
    
    }
    if (!salaComReservas){
        System.out.println("Sala sem reservas!");
    }
}else{
    System.out.println("Sala não encontrada!");
}
    }
}