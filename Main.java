import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {

    public static void main(String[] args)throws Exception {
        // GerenciadorDeSalas gsala = new GerenciadorDeSalas();
        
        //     gsala.adicionaSalaChamada("um", 10, "Sala de terapia");
        //     // gsala.reservaSalaChamada("um", LocalDateTime.parse("2019-01-01T09:00"), LocalDateTime.parse("2019-01-01T10:00"));
        //     gsala.reservaSalaChamada("um", LocalDateTime.parse("2019-01-01T10:00"), LocalDateTime.parse("2019-01-01T09:00"));
        //     gsala.reservaSalaChamada("um", LocalDateTime.parse("2019-01-01T08:01"), LocalDateTime.parse("2019-01-01T09:00"));
            
        //     gsala.adicionaSalaChamada("dois", 20, "Sala de jogos");
        //     gsala.reservaSalaChamada("dois", LocalDateTime.parse("2019-01-01T09:00"), LocalDateTime.parse("2019-01-01T10:00"));
        //     // gsala.reservaSalaChamada("tres", LocalDateTime.parse("2019-01-01T09:00"), LocalDateTime.parse("2019-01-01T10:00"));

        //     // gsala.adicionaSalaChamada("tres", 20, "Sala de jogos");
            
        //     //!! Códigos comentados para testes de exceções, tirar comentários e testar !!
            
        //     //Tentativa de criar sala com o mesmo nome - Resulta numa exception de SalaExistente
        //     //Sala sala3 = gsala.adicionaSalaChamada("dois", 20, "Sala de jogos");
            
        //     //Tentativa de sobrepor um horário de uma reserva já feia - Resulta numa exception de InterseccaoReserva
        //     //gsala.reservaSalaChamada("dois", LocalDateTime.parse("2019-01-01T09:30"), LocalDateTime.parse("2019-01-01T10:30"));
            
        //     gsala.imprimeReservasDaSala("um");
        //     gsala.imprimeReservasDaSala("dois");
        //     gsala.imprimeReservasDaSala("tres");
    
            


            Collection <String> p = new ArrayList<>();
            p.add("a");
            p.add("b");
            p.add("c");
            p.add("d");
            p.add("e");
            p.add("f");
            p.add("g");
            p.add("h");

            MarcadorDeReuniao m = new MarcadorDeReuniao();
            LocalDate x = LocalDate.of(2022, 7, 22);
            LocalDate y = LocalDate.of(2022, 8, 22);
            m.marcarReuniaoEntre(x, y, p);
            System.out.println(m.diaInicial);
            System.out.println(m.diaFinal);

            LocalDateTime inicio = LocalDateTime.of(2022, 7, 23, 10, 15);
            LocalDateTime fim = LocalDateTime.of(2022, 7, 27, 10, 15);
            LocalDateTime inicio1 = LocalDateTime.of(2022, 8, 5, 10, 15);
            LocalDateTime fim1 = LocalDateTime.of(2022, 8, 12, 10, 15);
            m.indicaDisponibilidadeDe("a", inicio, fim);
            m.indicaDisponibilidadeDe("a", inicio1, fim1);

            m.indicaDisponibilidadeDe("b", inicio, fim);
            m.indicaDisponibilidadeDe("b", inicio1, fim1);

            m.indicaDisponibilidadeDe("c", inicio, fim);
            m.indicaDisponibilidadeDe("c", inicio1, fim1);

            m.indicaDisponibilidadeDe("d", inicio, fim);
            m.indicaDisponibilidadeDe("d", inicio1, fim1);

            m.indicaDisponibilidadeDe("e", inicio, fim);
            m.indicaDisponibilidadeDe("e", inicio1, fim1);

            m.indicaDisponibilidadeDe("f", inicio, fim);
            m.indicaDisponibilidadeDe("f", inicio1, fim1);

            m.indicaDisponibilidadeDe("g", inicio, fim);
            m.indicaDisponibilidadeDe("g", inicio1, fim1);

            m.indicaDisponibilidadeDe("h", inicio, fim);
            m.indicaDisponibilidadeDe("h", inicio1, fim1);

            m.mostraSobreposicao();


            
        
    }
    
}