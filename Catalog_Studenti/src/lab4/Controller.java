package lab4;

public class Controller {
	private RepoStudenti studenti;
	
	public Controller() {
		studenti = new RepoStudenti();
	}
	public Controller(RepoStudenti s) {
		studenti = s;
	}
	/*
	 * getteri
	 */
	public RepoStudenti getStudenti() {
		return studenti;
	}
	public Student getStudent(int i) {
		return studenti.getStudent(i);
	}
	/*
	 * adaugare student in repo
	 */
	public void addStudent(Student s) {
		studenti.addStudent(s);
	}
	/*
	 * update student
	 */
	public void update(Student s, int i) {
		studenti.update(s, i);
	}
	/*
	 * sterge studentul de pe pozitia i
	 */
	public void delete(int i) {
		studenti.delete(i);
	}
	/*
	 * ordoneaza studentii dupa medie
	 */
	public RepoStudenti ordonareMedie() {
		return studenti.ordonareMedie();
	}
	/*
	 * afisare studenti neintegralisti
	 */
	public RepoStudenti studentiNeintegralisti() {
		return studenti.studentiNeintegralisti();
	}
	/*
	 * returneaza primii 3 studenti dupa nota la materia data ca param
	 */
	public RepoStudenti premiantiMaterie(String materie) {
		return studenti.premiantiMaterie(materie);
	}
	/*
	 * returneaza un repoStudenti cu integralisti, in ord des a mediei generale
	 */
	public RepoStudenti integralistiDupaMedie() {
		return studenti.integralistiDupaMedie();
	}
	/*
	 * ordoneaza dupa varsta (crescator)
	 */
	public void ordonareVarsta() {
		studenti.ordonareVarsta();
	}
	public String toString() {
		return studenti.toString();
	}
	public String afisareNumeSituatie() {
		return studenti.afisareNumeSituatie();
	}
	public String afisareNumeMedie() {
		return studenti.afisareNumeMedie();
	}
	public String afisareNumeMaterie(String materie) {
		return studenti.afisareNumeMaterie(materie);
	}
	/*
	 * adauga situatiaCurs s la studentul cu numele nume
	 */
	public void addSituatieCursLaStudentulCuNumele(String nume, SituatieCurs s) {
		int pos = studenti.getStudentCuNumele(nume);
		//System.out.println(pos);
		if(pos>=0 && pos<=studenti.getStudenti().length)
			studenti.getStudent(pos).addSituatieCurs(s);
	}
	/*
	 * calcul medie pt toti studentii
	 */
	public void calculMedie() {
		studenti.calculMedie();
	}
	/*
	 * returneaza numarul de studenti
	 */
	public int getNrStudenti() {
		return studenti.getNrStudenti();
	}
	public void adaugareGUI() {
		studenti.adaugareGUI();
	}
	public void afisTotGUI() {
		studenti.afisTotGUI();
	}
	public void afisNumeMedieGUI() {
		studenti.afisNumeMedieGUI();
	}
	public void afisNumeDataGUI() {
		studenti.afisNumeDataGUI();
	}
	public void deleteGUI() {
		studenti.deleteGUI();
	}
	public void updateGUI() {
		studenti.updateGUI();
	}
}
