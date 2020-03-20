package lab4;
import java.util.Date;


public class Student {
	private String nume;
	private String adresa;
	private Date dataNasterii;
	private SituatieTotala situatie;
	private float medie = 0;

	
	/*
	 * constructor fara parametri
	 */
	public Student() {
		nume = new String();
		adresa = new String();
		dataNasterii = new Date();
		situatie = new SituatieTotala();
		
	}
	
	/*
	 * constructor cu parametri
	 */
	public Student( String nume, String adresa, Date data, SituatieTotala sit) {
		this.nume = nume;
		this.adresa = adresa;
		dataNasterii = data;
		situatie = sit;
	}
	/*
	 * getteri
	 */
	public String getNume() {
		return nume;
	}
	public String getAdresa() {
		return adresa;
	}
	public Date getData() {
		return dataNasterii;
	}
	public SituatieTotala getSituatie() {
		return situatie;
	}
	public SituatieCurs getSituatieCurs( int i) {
		return situatie.getSituatieTotala()[i];
	}
	/*
	 * setteri
	 */
	public void setNume(String n) {
		nume = n;
	}
	public void setAdresa(String a) {
		adresa = a;
	}
	public void setData(Date d) {
		dataNasterii = d;
	}
	public void setSituatie(SituatieTotala s) {
		situatie = s;
	}
	/*
	 * modifica situatiaCurs de pe pozitia i cu situatiaCurs s
	 */
	public void updateSituatie( int i, SituatieCurs s) {
		situatie.getSituatieTotala()[i] = s;
	}
	/*
	 *adauga situatiaCurs s la situatie
	 */
	public void addSituatieCurs(SituatieCurs s) {
		situatie.add(s);
	}
	/*
	 * verifica daca studentul are vreo nota sub 5
	 */
	public boolean esteIntegralist() {
		int i;
		for(i=0; i<situatie.getNrMaterii(); i++) {
			if( situatie.getSituatieTotala()[i].notaSub5() == true)
				return false;
		}
		return true;
	}
	
	/*
	 * functia returneaza un vector cu toate restantele
	 */
	public SituatieTotala situatieRestante() {
		SituatieTotala sit = new SituatieTotala();
		int i;
		for(i=0; i<situatie.getNrMaterii(); i++)
			if(situatie.getSituatieTotala()[i].notaSub5() == true)
				sit.add(situatie.getSituatieTotala()[i]);
		return sit;
		
	}
	/*
	 * calcul medie
	 */
	public void calculMedie() {
		medie = situatie.calculMedie();
	}
	/*
	 * get medie
	 */
	public float getMedie() {
		return medie;
	}
	/*
	 * verifica daca studentul are materia data prin nume in situatie
	 */
	public boolean existaMaterie(String materie) {
		return situatie.existaMaterie(materie);
	}
	/*
	 * returneaza nota la materia materie, sau -1 daca nu exista
	 */
	public int getNotaLaMateria(String materie) {
		if(situatie.existaMaterie(materie)==true)
			return situatie.getNotaLaMateria(materie);
		else
			return -1;
	}
	/*
	 *conversie la string pt afisari 
	 */
	public String toString() {
		return nume + '\n' + adresa + '\n' + dataNasterii.toString()  + '\n' + situatie.toString() + '\n' + "media generala: " + Float.toString(medie)+'\n';
		
	}
	public String afisareNumeSituatie() {
		return nume + '\n' + situatie.toString() + '\n';
	}
	public String afisareNumeMedie() {
		return nume + '\n' + medie +'\n';
	}
	/*
	 * returneaza numarul de materii ale unui student
	 */
	public int getNrMaterii() {
		return situatie.getNrMaterii();
	}
	/*
	 * afiseaza numele studentului si nota la  materia "materie" daca acesta are nota sau un mesaj daca nu are
	 */
	public String afisareNumeMaterie(String materie) {
		for(int i=0; i<situatie.getNrMaterii(); i++)
			if(situatie.getSituatieTotala()[i].getMaterie().equals(materie))
				return nume + '\n' + situatie.getSituatieTotala()[i].toString();
		return "Studentul " + nume + " nu are nota la materia" + materie;
			
	}
}
