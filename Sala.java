public class Sala {
    private String nome, local, observacoes;
    private int capacidade;

    public Sala(String nome, String observacoes, int capacidade) {
        this.nome = nome;
        this.local = observacoes;
        this.observacoes = observacoes;
        this.capacidade = capacidade;
    }

    public String nome(){
        return nome;
    }
    public String local(){
        return local;
    }
    public String observacoes(){
        return observacoes;
    }
    public int capacidade(){
        return capacidade;
    }

}
