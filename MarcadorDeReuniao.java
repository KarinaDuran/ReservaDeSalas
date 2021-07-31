import java.time.LocalDate;
import java.time.LocalDateTime;
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

    public void marcarReuniaoEntre(LocalDate dataInicial, LocalDate dataFinal,
            Collection<String> listaDeParticipantes) {
        try {
            if (dataInicial.compareTo(dataFinal) >= 0) {
                throw new dataInvalidaException();
            }
        } catch (dataInvalidaException e) {
            System.out.println(e.getMessage());
            return;
        }

        Iterator<String> iterator = listaDeParticipantes.iterator();
        while (iterator.hasNext()) {
            Participante aux = new Participante(iterator.next());
            if (!Participantes.toString().contains(aux.toString()))
                Participantes.add(aux);
            else
                System.out.println("A lista de participantes ja contem um membro com o nome " + aux.nome
                        + ", somente a primeira insercao foi considerada." + '\n');
        }
        diaInicial = dataInicial;
        diaFinal = dataFinal;

    }

    public void indicaDisponibilidadeDe(String participante, LocalDateTime inicio, LocalDateTime fim) {
        Iterator<Participante> iterator = Participantes.iterator();
        // Verifica se o horario esta entre os dias estipulados e se eh valido
        try {
            LocalDateTime horaInicial = diaInicial.atStartOfDay();
            LocalDateTime horaFinal = diaFinal.atTime(23, 59);
            if (inicio.isAfter(fim) || inicio.isEqual(fim) || inicio.isBefore(horaInicial) || fim.isAfter(horaFinal))
                throw new dataInvalidaException();
        } catch (dataInvalidaException e) {
            System.out.println(e.getMessage());
            return;
        }

        boolean participanteExiste = false;
        while (iterator.hasNext()) {
            Participante x = iterator.next();
            if (x.nome == participante) {
                participanteExiste = true;
                x.adicionarHorario(inicio, fim);
                break;
            }
        }

        if (!participanteExiste) {
            System.out.println("Participante nao encontrado"+"\n");
        }

    }

    // Metodo para mostrar sobreposicao
    public void mostraSobreposicao() {
        // Iterador dos participantes
        Iterator<Participante> iterator = Participantes.iterator();
        // Lista auxiliar para remover os horarios que nao sao acessiveis por todos os
        // membros
        List<LocalDateTime> remocao = new ArrayList<>();

        Map<LocalDateTime, LocalDateTime> sobreposicoes = new HashMap<>();
        Boolean temSobreposicao;
        Participante x;
        // Adiciona as disponibilidades do primeiro participante
        if (iterator.hasNext()) {
            x = iterator.next();
            if (x.disponibilidade == null)
                return;
            for (LocalDateTime chave : x.disponibilidade.keySet()) {
                sobreposicoes.put(chave, x.disponibilidade.get(chave));
            }
        }
        // Percorre os proximos participantes para ajustar os horarios do primeiro de
        // modo que todos possam comparecer
        while (iterator.hasNext()) {
            x = iterator.next();
            if (x.disponibilidade == null)
                return;
            for (Map.Entry<LocalDateTime, LocalDateTime> j : sobreposicoes.entrySet()) {
                temSobreposicao = false;
                for (Map.Entry<LocalDateTime, LocalDateTime> i : x.disponibilidade.entrySet()) {
                    if ((i.getKey().compareTo(j.getKey()) >= 0 && i.getKey().compareTo(j.getValue()) < 0)
                            || ((i.getValue().compareTo(j.getValue()) <= 0)
                                    && i.getValue().compareTo(j.getKey()) > 0)) {

                        temSobreposicao = true;

                        if (i.getKey().isAfter(j.getKey())) {
                            sobreposicoes.put(i.getKey(), j.getValue());
                            sobreposicoes.remove(j.getKey());
                        }

                        if (i.getValue().isBefore(j.getValue())) {
                            sobreposicoes.replace(j.getKey(), i.getValue());
                        }
                    }

                }
                if (!temSobreposicao) {
                    remocao.add(j.getKey());
                }

            }

        }
        for (LocalDateTime i : remocao) {
            sobreposicoes.remove(i);
        }
        Console a = new Console(Participantes, sobreposicoes, diaInicial, diaFinal);
        a.imprime();
    }

}
