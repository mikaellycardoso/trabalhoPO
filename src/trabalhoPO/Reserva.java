package trabalhoPO;

public class Reserva implements Comparable<Reserva>{
    private String reserva, nome, voo, assento, data;

    public Reserva(String reserva, String nome, String voo, String assento, String data) {
        this.reserva = reserva;
        this.nome = nome;
        this.voo = voo;
        this.assento = assento;
        this.data = data;

    }

    public String getReserva() {
        return reserva;
    }

    public void setReserva(String reserva) {
        this.reserva = reserva;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVoo() {
        return voo;
    }

    public void setVoo(String voo) {
        this.voo = voo;
    }

    public String getAssento() {
        return assento;
    }

    public void setAssento(String assento) {
        this.assento = assento;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    public int compareTo(Reserva outraReserva) {

        int comparacaoNome = this.nome.compareToIgnoreCase(outraReserva.getNome());

        if (comparacaoNome != 0) {
            return comparacaoNome;
        }
        return this.reserva.compareToIgnoreCase(outraReserva.getReserva());
    }

	@Override
	public String toString() {
		return reserva + ";" + nome + ";" + voo + ";" + data + ";"
				+ assento;
	}
	public String toStringFormatoPesquisa() {
        return "NOME " + nome + ":\n" +
               "Reserva: " + reserva + "\n" +
               "Voo: " + voo + "\n" +
               "Data: " + data + "\n" +
               "Assento: " + assento + "\n";
	}
    
}