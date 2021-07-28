import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MarcadorDeReuniao {
    public static LocalDate diaInicial;
    public static LocalDate diaFinal;
    public Collection<Participante> Participantes = new ArrayList<Participante>();
    DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public class Participante {
        String nome;

        DateTimeFormatter formatoPadrao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        Map<LocalDateTime, LocalDateTime> disponibilidade = new HashMap<>();

        Participante(String nome) {
            this.nome = nome;
        }

        public void adicionarHorario(LocalDateTime inicio, LocalDateTime fim) {
            if (disponibilidade.containsKey(inicio)) {
                if (disponibilidade.get(inicio).isBefore(fim)) {
                    disponibilidade.replace(inicio, disponibilidade.get(inicio), fim);
                }
            } else {
                disponibilidade.put(inicio, fim);
            }
        }

        @Override
        public String toString() {
            String aux = "Nome do participante: " + nome + "\n" + "Horários disponíveis: ";
            for (LocalDateTime inicio : disponibilidade.keySet()) {
                LocalDateTime fim = disponibilidade.get(inicio);
                aux += "\n" + "De: " + inicio.format(formatoPadrao) + " a " + fim.format(formatoPadrao) ;
            }
            aux +="\n";
            return aux;
        }
    }

    public class Console {
        Map<LocalDateTime, LocalDateTime> sobreposicoes;

        Console(Collection<MarcadorDeReuniao.Participante> participantes, Map<LocalDateTime, LocalDateTime> sobreposicoes) {
            System.out.println("O organizador dessa reunião estipulou um prazo de " + diaInicial + " a " + diaFinal);
            System.out.println("Lista de participantes da reuniao e suas disponibilidades:");
            for (Participante participante : participantes) {
                System.out.println(participante);
                
            }

            if (sobreposicoes.isEmpty()) {
                System.out.println(
                        "Dado a disponibilidade dos participantes, não existe um horário em que todos os participantes possam comparecer!");
            }
            System.out.println(
                    "Dado a disponibilidade dos participantes, o(s) melhor(es) horário(s) para realizar a reunião:");
                    for (Map.Entry<LocalDateTime, LocalDateTime> sobreposicao : sobreposicoes.entrySet()){
                        System.out.println("De: " + sobreposicao.getKey().format(formatoPadrao) + " a " + sobreposicao.getValue().format(formatoPadrao));
                    }

        }
    }



    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal,
            Collection<String> listaDeParticipantes) {
        LocalDate hoje = LocalDate.now();
        try {
            if (dataInicial.isBefore(hoje)) {
                throw new dataInvalidaException();
            }
        } catch (dataInvalidaException e) {
            System.out.println(e.getMessage());
            return;
        }

        Iterator<String> iterator = listaDeParticipantes.iterator();
        while (iterator.hasNext()) {
            Participantes.add(new Participante(iterator.next()));
        }
        diaInicial = dataInicial;
        diaFinal = dataFinal;

    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        Iterator<Participante> iterator = Participantes.iterator();
        try {
            LocalDateTime horaInicial = diaInicial.atStartOfDay();
            LocalDateTime horaFinal = diaFinal.atTime(23, 59);
            if (inicio.isAfter(fim) || inicio.isAfter(horaFinal) || fim.isBefore(horaInicial))
                throw new dataInvalidaException();
        } catch (dataInvalidaException e) {
            System.out.println(e.getMessage());
            return;
        }
        while (iterator.hasNext()) {
            Participante x = iterator.next();
            if (x.nome == participante) {
                x.adicionarHorario(inicio, fim);
            }
        }

    }

   
    public void mostraSobreposicao() {
        Iterator<Participante> iterator = Participantes.iterator();
        List <LocalDateTime> remocao = new ArrayList<>();
        Map<LocalDateTime, LocalDateTime> possibilidades = new HashMap<>();
        Boolean temSobreposicao;
        Participante x;
        if (iterator.hasNext()) {
            x = iterator.next();
            if (x.disponibilidade == null)
                return;
            for (LocalDateTime chave : x.disponibilidade.keySet()) {
                possibilidades.put(chave, x.disponibilidade.get(chave));
            }
        }
        while (iterator.hasNext()) {
            x = iterator.next();
            if (x.disponibilidade == null)
                return; 
            for (Map.Entry<LocalDateTime, LocalDateTime> j : possibilidades.entrySet()) {
                temSobreposicao = false;
                for (Map.Entry<LocalDateTime, LocalDateTime> i : x.disponibilidade.entrySet()) {
                    if (( (i.getKey().isAfter(j.getKey()) || i.getKey().isEqual(j.getKey()))  && i.getKey().isBefore(j.getValue()))
                            || (i.getValue().isAfter(j.getKey()) && (i.getValue().isBefore(j.getValue()) || i.getValue().isEqual(j.getValue())))) {
                                temSobreposicao = true;
                        if (i.getKey().isAfter(j.getKey())) {
                            possibilidades.put(i.getKey(), j.getValue());
                            System.out.println("aqui");
                            possibilidades.remove(j.getKey());
                        }
                        if (i.getValue().isBefore(j.getValue())) {
                            possibilidades.replace(j.getKey(), i.getValue());
                        }
                    }

                }
                if(!temSobreposicao){
                   remocao.add(j.getKey());
                }

            }
            

        }
        for (LocalDateTime i : remocao) {
             possibilidades.remove(i);
        }
        Console a = new Console(Participantes, possibilidades);

    }

}
