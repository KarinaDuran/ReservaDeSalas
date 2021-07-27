import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args)throws Exception {
        GerenciadorDeSalas gsala = new GerenciadorDeSalas();
        
            gsala.adicionaSalaChamada("um", 10, "Sala de terapia");
            gsala.reservaSalaChamada("um", LocalDateTime.parse("2019-01-01T09:00"), LocalDateTime.parse("2019-01-01T10:00"));
            gsala.reservaSalaChamada("um", LocalDateTime.parse("2019-01-01T08:01"), LocalDateTime.parse("2019-01-01T09:00"));
            
            gsala.adicionaSalaChamada("dois", 20, "Sala de jogos");
            gsala.reservaSalaChamada("dois", LocalDateTime.parse("2019-01-01T09:00"), LocalDateTime.parse("2019-01-01T10:00"));
            // gsala.reservaSalaChamada("tres", LocalDateTime.parse("2019-01-01T09:00"), LocalDateTime.parse("2019-01-01T10:00"));

            // gsala.adicionaSalaChamada("tres", 20, "Sala de jogos");
            
            //!! Códigos comentados para testes de exceções, tirar comentários e testar !!
            
            //Tentativa de criar sala com o mesmo nome - Resulta numa exception de SalaExistente
            //Sala sala3 = gsala.adicionaSalaChamada("dois", 20, "Sala de jogos");
            
            //Tentativa de sobrepor um horário de uma reserva já feia - Resulta numa exception de InterseccaoReserva
            //gsala.reservaSalaChamada("dois", LocalDateTime.parse("2019-01-01T09:30"), LocalDateTime.parse("2019-01-01T10:30"));
            
            gsala.imprimeReservasDaSala("um");
            gsala.imprimeReservasDaSala("dois");
            // gsala.imprimeReservasDaSala("tres");
      
        
    }
    
}