import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;


public class MarcadorDeReuniao {
    public static LocalDate diaInicial;
    public static LocalDate diaFinal;


    public class Participante{
        String nome;
        LocalDateTime inicio;
        LocalDateTime fim;
        Participante(String nome){
            this.nome = nome;
        }

        public void setInicio(LocalDateTime inicio){
            this.inicio = inicio;
        }
        public void setFim(LocalDateTime fim){
            this.fim = fim;
        }
        public LocalDateTime getInicio(){
            return inicio;
        }
        public LocalDateTime getFim(){
            return fim;
        }
    }


    private Collection <Participante> Participantes = new ArrayList<Participante>();
    
    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal,
            Collection<String> listaDeParticipantes) {
            Iterator <String> iterator = listaDeParticipantes.iterator();
        while(iterator.hasNext()){
            Participantes.add(new Participante(iterator.next()));
        }
            diaInicial = dataInicial;
            diaFinal = dataFinal;

    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        Iterator <Participante> iterator = Participantes.iterator();
        //Precisa verificar se o horario indicado é entre os dias 
        while(iterator.hasNext()){
            Participante x = iterator.next();
            if (x.nome== participante){
                x.inicio = inicio;
                x.fim = fim;
            }
        }

    }

    public void mostraSobreposicao() {
        Iterator <Participante> iterator = Participantes.iterator();
        while(iterator.hasNext()){
  
            }
           
        }
    

}