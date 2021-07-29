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
            if (inicio.isAfter(fim) || inicio.isBefore(horaInicial) || fim.isAfter(horaFinal) || inicio.equals(fim))
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
        List<LocalDateTime> remocao = new ArrayList<>();
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
                    if (((i.getKey().isAfter(j.getKey()) || i.getKey().isEqual(j.getKey()))
                            && i.getKey().isBefore(j.getValue()))
                            || (i.getValue().isAfter(j.getKey())
                                    && (i.getValue().isBefore(j.getValue()) || i.getValue().isEqual(j.getValue())))) {
                        temSobreposicao = true;
                        if (i.getKey().isAfter(j.getKey())) {
                            possibilidades.put(i.getKey(), j.getValue());
                            possibilidades.remove(j.getKey());
                        }
                        if (i.getValue().isBefore(j.getValue())) {
                            possibilidades.replace(j.getKey(), i.getValue());
                        }
                    }

                }
                if (!temSobreposicao) {
                    remocao.add(j.getKey());
                }

            }

        }
        for (LocalDateTime i : remocao) {
            possibilidades.remove(i);
        }
        Console a = new Console(Participantes, possibilidades, diaInicial, diaFinal);
        a.imprime();
    }

}
