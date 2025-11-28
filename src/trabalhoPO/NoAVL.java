package trabalhoPO;

import java.util.ArrayList;

public class NoAVL {

    private ArrayList<Reserva> listaItens;
    private int altura;
    private NoAVL esq;
    private NoAVL dir;

    public NoAVL(Reserva item) {
        this.listaItens = new ArrayList<>();
        this.listaItens.add(item);
        this.altura = 1; 
        this.esq = null;
        this.dir = null;
    }

    public Reserva getChave() {
        return listaItens.get(0);
    }

    public ArrayList<Reserva> getListaItens() {
        return listaItens;
    }

    public void adicionarItem(Reserva item) {
        this.listaItens.add(item);
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public NoAVL getEsq() {
        return esq;
    }

    public void setEsq(NoAVL esq) {
        this.esq = esq;
    }

    public NoAVL getDir() {
        return dir;
    }

    public void setDir(NoAVL dir) {
        this.dir = dir;
    }

    int getFatorBalanceamento() {
        return 0;
    }

	public void setFatorBalanceamento(int i) {
		
	}

		
	}

