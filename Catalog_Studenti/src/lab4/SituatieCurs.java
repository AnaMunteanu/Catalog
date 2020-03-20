package lab4;

public class SituatieCurs {
	private int nota;
	private String materie;
	
	/*
	 * constructor implicit
	 */
	public SituatieCurs() {
		
		nota = 0;
		materie = null;
	}
	/*
	 * constructor cu parametri
	 */
	public SituatieCurs(int n, String m) {
		
		nota = n;
		materie = m;
	}
	/*
	 * constructor de copiere
	 */
	public SituatieCurs(SituatieCurs s) {
		
		nota = s.nota;
		materie = s.materie;
	}
	/*
	 * accesor de tip get pentru nota
	 */
	public int getNota() {
		
		return nota;
	}
	/*
	 * accesor de tip get pentru materie
	 */
	public String getMaterie() {
		
		return materie;
	}
	/*
	 * accesor de tip set pentru nota
	 */
	public void setNota(int nota) {
		
		this.nota = nota;
	}
	/*
	 * accesor de tip set pentru materie
	 */
	public void setMaterie(String materie) {
		
		this.materie = materie;
	}
	/*
	 * verifica daca nota<5
	 */
	public boolean notaSub5() {
		return (nota<5)? true: false;
		
	}
	/*
	 * conversie la string pentru afisari
	 */
	public String toString() {
		return "nota "+nota+" la materia "+ materie;
	}
}
