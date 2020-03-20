package lab4;

public class SituatieTotala {
	private SituatieCurs[] situatie;
	private static int MAX;
	/*
	 * constr fara parametri
	 */
	public SituatieTotala() {
		situatie = new SituatieCurs[0];
	}
	
	/*
	 * constr cu param
	 */
	public SituatieTotala( SituatieCurs[] sit) {
		situatie = sit;
	}
	
	/*
	 * get
	 */
	public SituatieCurs[] getSituatieTotala() {
		return situatie;
	}
	
	/*
	 * get nr materii din situatie
	 */
	public int getNrMaterii() {
		return situatie.length;
	}
	/*
	 * calcul medie
	 */
	public float calculMedie() {
		int suma = 0, i;
		for( i = 0; i<situatie.length;i++)
			suma = suma + situatie[i].getNota();
		float medie = (float)suma / situatie.length;
		return medie;
		
	}
	
	/*
	 * functie de adaugare a unei situatiiCurs la situatia totala
	 */
	public void add(SituatieCurs s) {
		
		int lung =situatie.length;
		SituatieCurs[] aux=new SituatieCurs[ lung];
		aux= situatie;
		lung++;
		int i;
		situatie =new SituatieCurs[lung];
		for(i=0; i<lung-1; i++)
			situatie[i]=aux[i];
		situatie[lung-1] = s; 
	}
	/*
	 * conversie la string pt afisari
	 */
	public String toString() {
		String str = new String();
		int i;
		for(i = 0; i < situatie.length; i++)
			str = str + situatie[i].toString() + '\n';
		//str = str + situatie[situatie.length-1].toString();
		return str;
	}
	/*
	 * verifica daca materia cu numele dat ca parametru exista in vector
	 */
	public boolean existaMaterie(String materie) {
		
		int i;
		for(i=0; i<situatie.length; i++)
			if(situatie[i].getMaterie().equals(materie))
				return true;
		return false;
	}
	/*
	 * returneaza nota de la materia materie sau -1 daca nu exista
	 */
	public int getNotaLaMateria( String materie) {
		int i;
			for(i=0; i<situatie.length;i++)
				if(situatie[i].getMaterie()==materie)
					return situatie[i].getNota();
			return -1;
	}
}
