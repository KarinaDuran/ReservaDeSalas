public class Sala {
    private String nome, local, observacoes;
    private int capacidade;

    public Sala(String nome, String observacoes, int capacidade) {
        this.nome = nome;
        this.local = observacoes;
        this.observacoes = observacoes;
        this.capacidade = capacidade;
    }

    public String getNome(){
        return nome;
    }
    public String getLocal(){
        return local;
    }
    public String getObservacoes(){
        return observacoes;
    }
    public int getCapacidade(){
        return capacidade;
    }

}
