package lab4;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.*;
import java.util.Formatter;
;
public class UI {
	
	private Controller ctrl;
	
	public UI() {
		
		ctrl = new Controller();
	}
	
	public static int CitInt(String sir) {   	//citeste un nr de tip int
		
		System.out.println(sir);
		Scanner scn = new Scanner(System.in);
		return scn.nextInt();
	}
	
	public static int AfisMeniu() {
		
		System.out.println("1. Popularea bazei de date cu studenti prin citirea din fisierele Studenti si Note");
		System.out.println("2. Adaugare student");
		System.out.println("3. Stergere student");
		System.out.println("4. Update student");
		System.out.println("5. Ordonare descrescatoare dupa medie");
		System.out.println("6. Ordonare descrescatoare a studentilor integralisti dupa medie");
		System.out.println("7. Ordonare dupa varsta (crescator)");
		System.out.println("8. Studenti nepromovati si materiile la care nu au promovat");
		System.out.println("0. Terminare program");
		int optiune=CitInt("Optiunea ta: ");
		return optiune;
		
		}
	
	/*
	 * citeste un string de la tastatura pana la Enter
	 */
	public static String citString(String sir) throws IOException {
		
	    char c;
	    String  s = new String();
	    System.out.println(sir);
	    do {
	        c= (char) System.in.read();
	        s = s + c;
	    }
	    while (c != '\n');

	    return s.substring(0, s.length()-2);
	  }
	
	public void citStudenti() {
		RepoStudenti studenti = new RepoStudenti();
		String[] sir = new String[0];
		try {
			BufferedReader file = new BufferedReader ( new FileReader ("D:\\METODE AVANSATE\\lab4\\STUDENTI"));
			String str = new String();
			while ( (str = file.readLine())!=null) {
			
				Student s = new Student();
				ctrl = new Controller(studenti);
				sir = str.split(",");
				s.setNume(sir[0]);
				s.setAdresa(sir[1]);
				int an, luna, zi;
				try {
					 an = Integer.parseInt(sir[2]);
					 luna = Integer.parseInt(sir[3])-1;
					 zi = Integer.parseInt(sir[4]);
					 Date d = new Date(an, luna, zi);
					 s.setData(d);
				}
				catch( NumberFormatException nfex) {
					System.out.println("Number format exception: "+ nfex.getMessage());
				}
					
					
					ctrl.addStudent(s);
					//System.out.println(s);
				}  //end while
				
		}
		catch(IOException e) {
			
			System.out.println("Eroare la citire...");
			e.printStackTrace();
		}
	}
	
	public String[] citMaterii() {
		String[] sir = new String[0];
		String[] materii = new String[0];
		try {
			BufferedReader file = new BufferedReader ( new FileReader ("D:\\METODE AVANSATE\\lab4\\MATERII"));
			String str = new String();
			while ( (str = file.readLine())!=null) {
				sir = str.split(",");
				SituatieCurs s = new SituatieCurs( Integer.parseInt(sir[2]), sir[1]);
				boolean existaMaterie= false;
				for(int i=0; i<materii.length;i++)
					if(sir[1].equals(materii[i]))
						existaMaterie = true;
				if(existaMaterie==false) {
					String[] aux = materii;
					int lung=materii.length;
					materii = new String[lung+1];
					for(int i=0; i< aux.length;i++)
						materii[i] = aux[i];
					materii[aux.length] = sir[1];	
					}
				
				//System.out.println(s);
				//System.out.println(sir[0]);
				ctrl.addSituatieCursLaStudentulCuNumele(sir[0], s);
			}
			
		}
		catch(IOException e) {
			System.out.println("Eroare la citire...");
			e.printStackTrace();
		}
		return materii;
		
	}
	public void scriereStudenti() {
		try {
		 BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\METODE AVANSATE\\lab4\\STUDENTI"));
		    for(int i=0; i<ctrl.getNrStudenti(); i++) {
		    	int luna = ctrl.getStudent(i).getData().getMonth()+1;
		    	String str= ctrl.getStudent(i).getNume()+","+ctrl.getStudent(i).getAdresa()+","
		    +ctrl.getStudent(i).getData().getYear()+","+luna+","+ctrl.getStudent(i).getData().getDay()+'\n';
		    	writer.write(str);
		    }
		     
		    writer.close();
		}
		catch(IOException e) {
			System.out.println("Eroare la scrierea in fisier "+e.getMessage());
		}
	}
	public void scriereMaterii() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("D:\\METODE AVANSATE\\lab4\\MATERII"));
			for(int i=0; i<ctrl.getNrStudenti(); i++) {
				for(int j=0; j<ctrl.getStudent(i).getNrMaterii();j++) {
					String str = ctrl.getStudent(i).getNume()+","+ctrl.getStudent(i).getSituatieCurs(j).getMaterie()+","+ctrl.getStudent(i).getSituatieCurs(j).getNota()+'\n';
					writer.write(str);
				}
			}
			writer.close();
			
		}
		catch(IOException e) {
			System.out.println("Eroare la scrierea in fisier "+e.getMessage());
		}
	}
	public void addStudent() {
		Student student = new Student();
		String nume = new String();
		String adresa = new String();
		String an = new String();
		String luna = new String();
		String zi = new String();
		Date d = new Date();
		try {
		nume = citString("Dati numele:");
		adresa = citString("Dati adresa:");
		an = citString("Dati anul nasterii: ");
		luna = citString("Dati luna nasterii: ");
		zi = citString("Dati ziua nasterii: ");
		d = new Date(Integer.parseInt(an), Integer.parseInt(luna)-1, Integer.parseInt(zi));
		}
		catch(IOException e) {
			System.out.println("Eroare la citire: "+ e.getMessage());
		}
		student.setNume(nume);
		student.setAdresa(adresa);
		student.setData(d);
		int nr;
		nr = CitInt("Dati nr de materii:");
		SituatieTotala sit = new SituatieTotala();
		for(int i=0; i<nr; i++) {
			
			String materie = new String();
			String nota = new String();
			try {
			materie = citString("Dati materia:");
			nota = citString("Dati nota: ");
			}
			catch(IOException e) {
				System.out.println("Eroare la citire " + e.getMessage());
			}
			SituatieCurs s = new SituatieCurs(Integer.parseInt(nota), materie);
			sit.add(s);
		}
		student.setSituatie(sit);
		student.calculMedie();
		ctrl.addStudent(student);
	}
	public void deleteStudent() {
		int pos = CitInt("Dati pozitia studentului pe care doriti sa-l stergeti:");
		ctrl.delete(pos);

		
	}
	public void updateStudent() {
		int pos = CitInt("Dati pozitia studentului pe care doriti sa-l modificati:");
		System.out.println("Dati datele despre noul student.");
		Student student = new Student();
		String nume = new String();
		String adresa = new String();
		String an = new String();
		String luna = new String();
		String zi = new String();
		Date d = new Date();
		try {
		nume = citString("Dati numele:");
		adresa = citString("Dati adresa:");
		an = citString("Dati anul nasterii: ");
		luna = citString("Dati luna nasterii: ");
		zi = citString("Dati ziua nasterii: ");
		d = new Date(Integer.parseInt(an), Integer.parseInt(luna)-1, Integer.parseInt(zi));
		}
		catch(IOException e) {
			System.out.println("Eroare la citire: "+ e.getMessage());
		}
		student.setNume(nume);
		student.setAdresa(adresa);
		student.setData(d);
		int nr;
		nr = CitInt("Dati nr de materii:");
		SituatieTotala sit = new SituatieTotala();
		for(int i=0; i<nr; i++) {
			
			String materie = new String();
			String nota = new String();
			try {
			materie = citString("Dati materia:");
			nota = citString("Dati nota: ");
			}
			catch(IOException e) {
				System.out.println("Eroare la citire " + e.getMessage());
			}
			SituatieCurs s = new SituatieCurs(Integer.parseInt(nota), materie);
			sit.add(s);
		}
		student.setSituatie(sit);
		student.calculMedie();
		ctrl.update(student, pos);
	}
	public void run() {
		String[] listaMaterii = new String[0];
		int opt=AfisMeniu();
		while ( opt != 0 ) {
			if(opt==1) {
				citStudenti();
				listaMaterii = citMaterii();
				ctrl.calculMedie();
				//System.out.println(ctrl.toString());
				AfisTot();
			}
			else if(opt==2) {
				addStudent();
				scriereStudenti();
				scriereMaterii();
				//System.out.println(ctrl.toString());
				AfisTot();
			}
			else if(opt==3) {
				deleteStudent();
				scriereStudenti();
				scriereMaterii();
				//System.out.println(ctrl.toString());
				AfisTot();
			}
			else if(opt==4) {
				updateStudent();
				scriereStudenti();
				scriereMaterii();
				//System.out.println(ctrl.toString());
				AfisTot();
				
			}
			else if(opt==5) {
				ctrl.ordonareMedie();
				//System.out.println(ctrl.afisareNumeMedie());
				AfisNumeMedie(ctrl);
				
			}
			else if(opt==6) {
				RepoStudenti rez = new RepoStudenti();
				rez = ctrl.integralistiDupaMedie();
				Controller ctr2 = new Controller(rez);
				AfisNumeMedie(ctr2);
				
				
			}
			else if(opt==7) {
				ctrl.ordonareVarsta();
				//System.out.println(ctrl.toString());
				AfisNumeData();
				
			}
			else if(opt==8) {
				RepoStudenti rez = new RepoStudenti();
				rez = ctrl.studentiNeintegralisti();
				//System.out.println(rez.toString());
				//rez.afisareNeintegralisti();
				AfisRestantieri(rez);
			}
		
			
			opt = AfisMeniu();
		}
		System.out.println("Program terminat.");
}
	public void AfisTot() {
		FormatariTabel.capTabel1();
		for(int i=0; i<ctrl.getNrStudenti(); i++)
			FormatariTabel.Tabel1(ctrl,i);
		FormatariTabel.Linii(58,'-');
	}
	public void AfisNumeMedie(Controller ctrl) {
		FormatariTabel.capTabel2();
		for(int i=0; i<ctrl.getNrStudenti(); i++)
			FormatariTabel.Tabel2(ctrl,i);
		FormatariTabel.Linii(20,'-');
	}
	public void AfisNumeData() {
		FormatariTabel.capTabel3();
		for(int i=0; i<ctrl.getNrStudenti(); i++)
			FormatariTabel.Tabel3(ctrl,i);
		FormatariTabel.Linii(22,'-');
	}
	public void AfisRestantieri(RepoStudenti repo) {
		FormatariTabel.capTabel4();
		for(int i=0; i<repo.getNrStudenti();i++)
			FormatariTabel.Tabel4(repo, i);
		FormatariTabel.Linii(32, '-');
	}
}

class FormatariTabel{
	
	public static void Linii (int nrLinii,char c){
		for(int i=0;i<nrLinii;i++){
			System.out.print(c);
		}
		System.out.println();
	}
	public static void capTabel1(){
		String sir="|   Nume   |   Data   |     Adresa     |Materie    | Nota |";
		//String linii="==================================================";
		Linii(58,'=');
		System.out.println(sir);
		Linii(58,'=');
		//System.out.println(linii);
	    //System.out.println(sir);
		//System.out.println(linii);
	}
	public static void capTabel2() {
		String sir = "|   Nume   | Medie |";
		Linii(20, '=');
		System.out.println(sir);
		Linii(20,'=');
	}
	public static void capTabel3() {
		String sir = "|   Nume   |   Data   |";
		Linii(22, '=');
		System.out.println(sir);
		Linii(22,'=');
	}
	public static void capTabel4() {
		String sir = "|   Nume   |  Materie  | Nota |";
		Linii(32, '=');
		System.out.println(sir);
		Linii(32, '=');
	}
	public static void Tabel4(RepoStudenti repo, int i) {
		for(int j = 0; j< repo.getStudent(i).getSituatie().getNrMaterii(); j++) {
			
		if(repo.getStudent(i).getSituatieCurs(j).notaSub5()==true) {
			Formatter f = new Formatter();
			String[] s = new String[3];
			s[0] = repo.getStudent(i).getNume();
			s[1]=repo.getStudent(i).getSituatieCurs(j).getMaterie();
			s[2]=Integer.toString(repo.getStudent(i).getSituatieCurs(j).getNota());
			f.format("|%-10s|%-11s|%-6s|\n", s);
			System.out.print(f);
			}
		}
	}
	public static void Tabel3(Controller ctrl, int i) {
		Formatter f = new Formatter();
		String[] s = new String[2];
		s[0] = ctrl.getStudent(i).getNume();
		int luna = ctrl.getStudent(i).getData().getMonth()+1;
		s[1]=Integer.toString(ctrl.getStudent(i).getData().getYear())+"."+Integer.toString(luna)+"."+Integer.toString(ctrl.getStudent(i).getData().getDay());
		f.format("|%-10s|%-10s|\n", s);
		System.out.print(f);
	}
	public static void Tabel2(Controller ctrl, int i) {
		Formatter f = new Formatter();
		String[] s = new String[2];
		s[0]=ctrl.getStudent(i).getNume();
		s[1]= Float.toString(ctrl.getStudent(i).getMedie());
		f.format("|%-10s|%-7s|\n", s);
		System.out.print(f);
	}
	public static void Tabel1(Controller ctrl, int i) {
		
			int j = 0;
			while(j<ctrl.getStudent(i).getNrMaterii()) {
				
				Formatter f = new Formatter();
				String[] s = new String[5];
				
				s[0]=ctrl.getStudent(i).getNume();
				int luna = ctrl.getStudent(i).getData().getMonth()+1;
				//System.out.println(Integer.toString(ctrl.getStudent(i).getData().getDay()));
				s[1]=Integer.toString(ctrl.getStudent(i).getData().getYear())+"."+Integer.toString(luna)+"."+Integer.toString(ctrl.getStudent(i).getData().getDay());
				s[2]=ctrl.getStudent(i).getAdresa();
				s[3]=ctrl.getStudent(i).getSituatieCurs(j).getMaterie();
				s[4]=Integer.toString(ctrl.getStudent(i).getSituatieCurs(j).getNota());
				
				f.format("|%-10s|%-10s|%-16s|%11s|%-6s|\n",s);
				System.out.print(f);
				j++;
			}
	}
	/*public void AfisTot(){
		Formatter F = new Formatter();
		capTabel1();
		int i=0;
		while(i<ctrl.getNrStudenti()){
			
			String []s=new String[5];
			s[0]=ctrl.getStudent(i).getNume();
			s[1]=ctrl.getStudent(i).getData().toString();
			s[2]=ctrl.getStudent(i).getAdresa();
			for(int j=0; j<ctrl.getStudent(i).getNrMaterii();j++) {
				s[3]=ctrl.getStudent(i).getSituatieCurs(j).getMaterie();
				s[4]=Integer.toString(ctrl.getStudent(i).getSituatieCurs(j).getNota());
				//F.format("|%-10f|%-8f|%-10f|%13f|%6f|\n",s[0],s[1],s[2],s[3],s[4]);
				//System.out.println(F);
				System.out.format("|%-10f|%-8f|%-10f|%-13f|%-6f|\n",s[0],s[1],s[2],s[3],s[4]);
			}
			i++;
		}	
	}*/
}
