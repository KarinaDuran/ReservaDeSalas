public class Excecoes {
    public static class SalaNaoEncontradaException extends Exception {
        String sala;
    
        SalaNaoEncontradaException(String sala) {
            this.sala = sala;
        }
    
        public String getMessage() {
            return "Sala " + sala + " não encontrada!";
        }
    }
    
    public static class SalaJaReservadaException extends Exception {
        Sala sala;
    
        SalaJaReservadaException(Sala sala) {
            this.sala = sala;
        }
    
        public String getMessage() {
            return "Sala " + sala.getNome() + " já reservada neste horario!";
        }
    }
    public static class dataInvalidaException extends Exception{
        public String getMessage() {
            return "Data inválida!";
        }
    }
}
