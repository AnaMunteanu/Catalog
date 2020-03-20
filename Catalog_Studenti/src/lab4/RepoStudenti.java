package lab4;
import javax.print.DocFlavor;
import javax.swing.*;
import javax.swing.text.LabelView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

public class RepoStudenti {
	private Student[] vector;
	private static int MAX = 100;
	/*
	 * constr fara parametri
	 */
	public RepoStudenti() {
		vector = new Student[0];
	}
	/*
	 * constructor cu param
	 */
	public RepoStudenti( Student[] s) {
		vector = s;
	}
	/*
	 * constr de copiere
	 */
	public RepoStudenti( RepoStudenti r) {
		vector = r.vector;
	}
	/*
	 * get
	 */
	public Student[] getStudenti() {
		return vector;
	}
	public Student getStudent(int i) {
		return vector[i];
	}
	/*
	 * set
	 */
	public void setStudenti(Student[] s) {
		vector = s;
	}
	/*
	 * functie de adaugare a unui student 
	 */
	public void addStudent(Student s) {
		
		int lung = vector.length, i;
		Student[] aux = new Student[lung++];
		aux = vector;
		vector = new Student[lung];
		for(i=0; i<lung-1; i++)
			vector[i] = aux[i];
		vector[lung-1] = s;	
	}
	/*
	 * functie ce modifica studentul de pe poz i cu studentul s
	 */
	public void update(Student s, int i) {
		if(i>=0 && i<vector.length)
			vector[i] = s;
	}
	/*
	 * sterge studentul de pe pozitia pos
	 */
	public void delete( int pos) {
		Student[] aux = new Student[vector.length-1];
		int i, j=0;
		for(i=0; i<vector.length; i++)
			if(i != pos) 
				aux[j++] = vector[i];
		j--;
		vector = new Student[aux.length];
		for(i=0; i<aux.length; i++)
			vector[i] = aux[i];
				
	}
	/*
	 * ordoneaza studentii dupa medie
	 */
	public RepoStudenti ordonareMedie() {
		int i, j;
		for(i = 0; i<vector.length-1; i++)
			for(j = i+1; j<vector.length; j++)
				if( vector[i].getMedie()< vector[j].getMedie()) {
					Student aux = new Student();
					aux = vector[i];
					vector[i] = vector[j];
					vector[j] = aux;
				}
		return this;
	}
	/*
	 * vector de studenti neintegralisti
	 */
	public RepoStudenti studentiNeintegralisti() {
		RepoStudenti rez = new RepoStudenti();
		int i;
		for(i = 0; i<vector.length; i++)
			if(!( vector[i].esteIntegralist()))
				rez.addStudent(vector[i]);
		return rez;
	}
	/*
	 * returneaza un repoStudenti cu primii 3 elevi cu cele mai mari note la disciplina cu numele "nume"
	 */
	public RepoStudenti premiantiMaterie( String materie) {
		RepoStudenti aux = new RepoStudenti();
		RepoStudenti rez = new RepoStudenti();
		int i,j;
		for(i=0; i<vector.length; i++)
			if(vector[i].existaMaterie(materie)) {
				aux.addStudent(vector[i]);
				//System.out.println(vector[i]);
			}
		//System.out.println("DIM: "+aux.vector.length);
		for(i=0; i<aux.vector.length-1;i++)
			for(j=i+1; j<aux.vector.length; j++)
				if(aux.vector[i].getNotaLaMateria(materie)<aux.vector[j].getNotaLaMateria(materie)) {
					Student studAux = new Student();
					studAux = aux.vector[i];
					aux.vector[i] = aux.vector[j];
					aux.vector[j] = studAux;
				}
		if(aux.vector.length>=1)
			rez.addStudent(aux.vector[0]);
		if(aux.vector.length>=2)
			rez.addStudent(aux.vector[1]);
		if(aux.vector.length>=3)
			rez.addStudent(aux.vector[2]);
		return rez;
		
	}
	/*
	 * returneaza un repoStudenti cu integralisti, in ord des a mediei generale
	 */
	public RepoStudenti integralistiDupaMedie() {
		RepoStudenti rez = new RepoStudenti();
		int i;
		for(i=0; i<vector.length; i++)
			if(vector[i].esteIntegralist()==true)
				rez.addStudent(vector[i]);
		rez.ordonareMedie();
		return rez;
			
	}
	/*
	 * ordoneaza dupa varsta (crescator)
	 */
	public void ordonareVarsta() {
		int i,j;
		for(i=0; i<vector.length-1; i++)
			for(j=i+1; j<vector.length; j++)
				if(vector[i].getData().before(vector[j].getData())) {
					Student aux = vector[i];
					vector[i] = vector[j];
					vector[j] = aux;
				}
	}
	public String toString() {
		String str = new String();
		int i;
		//System.out.println(vector.length);
		for( i=0; i<vector.length; i++)
			str = str + vector[i].toString() +'\n';
		return str;
	}
	public String afisareNumeSituatie() {
		String str = new String();
		int i;
		for( i=0; i<vector.length; i++)
			str = str + vector[i].afisareNumeSituatie() +'\n';
		return str;
		
	}
	public String afisareNumeMedie() {
		String str = new String();
		int i;
		for( i=0; i<vector.length; i++)
			str = str + vector[i].afisareNumeMedie() +'\n';
		return str;
	}
	public String afisareNumeMaterie(String materie) {
		String str = new String();
		int i;
		for( i=0; i<vector.length; i++)
			str = str + vector[i].afisareNumeMaterie(materie) +'\n';
		return str;
	}
	/*
	 * returneaza pozitia studentului cu numele "nume" din repo sau -1 daca nu exista
	 */
	public int getStudentCuNumele(String nume) {
		int i;
		//System.out.println("Dim: " + vector.length);
		for(i=0; i<vector.length; i++)
			if(vector[i].getNume().equals(nume))
				return i;
		return -1;
	}
	/*
	 * calcul medie pentru toti studentii
	 */
	public void calculMedie() {
		for( int i=0; i<vector.length;i++)
			vector[i].calculMedie();
	}
	/*
	 * afiseaza studentii neintegralisti
	 */
	public RepoStudenti afisareNeintegralisti(){
		RepoStudenti rez = new RepoStudenti();
		for(int i = 0; i<vector.length; i++)
			if(vector[i].esteIntegralist() == false) {
				rez.addStudent(vector[i]);	
			}		
		return rez;
	}
	/*
	 * returneaza nr de studenti din vector
	 */
	public int getNrStudenti() {
		return vector.length;
	}
	public JTable creareTabel(Vector<Vector<String>> Data,Vector<String> Column){
	        final JTable Tabel=new JTable(Data,Column);
	        Tabel.setBounds(0,50,700,100*vector.length);
	        Tabel.setFillsViewportHeight(true);
	        return Tabel;
	    }
	public Vector<String> creareColoana(String data[]){
	        Vector<String> column=new Vector<>();
	        for(int i=0;i<data.length;i++)
	            column.add(data[i]);
	        return column;
	   }
	public Vector<Vector<String>> creareData(Vector<Vector<String>> V,Vector<String> col){
	        Vector<Vector<String>> data=new Vector<>();
	        data.add(col);
	        for(int i=0;i<V.size();i++)
	            data.add(V.elementAt(i));
	        return data;
	   }
	public void afisTotGUI(){
        JFrame baza = new JFrame();
        Vector<Vector<String>> Data1=new Vector<>();
        Vector<Vector<String>> Data;
        Vector<String> column = creareColoana(new String[]{"Nume","Adresa","Data","Materie","Nota"});
        for(int i=0;i<vector.length;i++){
           
            for(int j = 0; j<vector[i].getNrMaterii(); j++) {
            	Vector<String> Data3=new Vector<>();
                Data3.add(vector[i].getNume());
                Data3.add(vector[i].getAdresa());
                Data3.add(vector[i].getData().toGMTString());
            	Data3.add(vector[i].getSituatieCurs(j).getMaterie());
            	Data3.add(Integer.toString(vector[i].getSituatieCurs(j).getNota()));
            	Data1.add(Data3);
            }
        }
        Data=creareData(Data1,column);
        JTable Tabel=creareTabel(Data,column);
        baza.add(Tabel);
        Button Inapoi = new Button("Inapoi");
        Inapoi.setBounds(130,500,200,40);
        Inapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                baza.dispose();
            }
        });
        
        baza.add(Inapoi);
        baza.setSize(1000,1000);
        baza.setLayout(null);
        baza.setVisible(true);
    }
	public void adaugareGUI(){
        JFrame Submeniu1=new JFrame();
        Label Add=new Label("Introduceti datele noului student");
        Add.setBounds(100,110,300,20);
        TextField T1=new TextField();
        Label nume=new Label("Nume:");
        nume.setBounds(100,130,60,20);
        T1.setBounds(150,130,200,20);
        TextField T2=new TextField();
        Label adresa=new Label("Adresa:");
        adresa.setBounds(100,150,60,20);
        T2.setBounds(150,150,200,20);
        TextField T3=new TextField();
        Label Ziua=new Label("Ziua:");
        Ziua.setBounds(100,170,60,20);
        T3.setBounds(150,170,200,20);
        TextField T4=new TextField();
        Label Luna=new Label("Luna:");
        Luna.setBounds(100,190,60,20);
        T4.setBounds(150,190,200,20);
        TextField T5=new TextField();
        T5.setBounds(150,210,200,20);
        Label An=new Label("An:");
        An.setBounds(100,210,60,20);
        TextField T6=new TextField();
        T6.setBounds(150,230,200,20);
        Label Materie=new Label("Materie");
        Materie.setBounds(100,230,60,20);
        TextField T7=new TextField();
        Label Nota=new Label("Nota:");
        Nota.setBounds(100,250,60,20);
        T7.setBounds(150,250,200,20);

        JButton Submit=new JButton("Adauga");
        Submit.setBounds(150,300,200,50);
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int ziua,luna,an, nota;
                String adresa,nume,materie;
                nume = T1.getText();
                adresa = T2.getText();
                
                ziua = Integer.parseInt(T3.getText()) + 1;
                luna = Integer.parseInt(T4.getText()) - 1;
                an = Integer.parseInt(T5.getText());
                
                materie=T6.getText();
                nota = Integer.parseInt(T7.getText());

                Date data = new Date(an,luna,ziua);
               
                SituatieTotala sit = new SituatieTotala();
                SituatieCurs curs = new SituatieCurs(nota, materie);
                sit.add(curs);
                Student student=new Student(nume, adresa, data, sit);
                addStudent(student);
                Label Succes=new Label("Student adaugat!");
                Succes.setBounds(150,400,200,20);
                Submeniu1.add(Succes);
            }
        });
        JButton Inapoi = new JButton("Inapoi");
        Inapoi.setBounds(150,350,200,50);
        Inapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Submeniu1.dispose();
            }
        });

        Submeniu1.add(Add);
        Submeniu1.add(T1);
        Submeniu1.add(nume);
        Submeniu1.add(T2);
        Submeniu1.add(adresa);
        Submeniu1.add(T3);
        Submeniu1.add(Ziua);
        Submeniu1.add(T4);
        Submeniu1.add(Luna);
        Submeniu1.add(T5);
        Submeniu1.add(An);
        Submeniu1.add(T6);
        Submeniu1.add(Materie);
        Submeniu1.add(T7);
        Submeniu1.add(Nota);
        Submeniu1.add(Submit);
        Submeniu1.add(Inapoi);

        Submeniu1.setSize(1000,1000);
        Submeniu1.setLayout(null);
        Submeniu1.setVisible(true);
    }
	public void afisNumeMedieGUI(){
        JFrame baza = new JFrame();
        Vector<Vector<String>> Data1=new Vector<>();
        Vector<Vector<String>> Data;
        Vector<String> column = creareColoana(new String[]{"Nume","Adresa","Data","Medie"});
        for(int i=0;i<vector.length;i++){
            Vector<String> Data3=new Vector<>();
            Data3.add(vector[i].getNume());
            Data3.add(vector[i].getAdresa());
            Data3.add(vector[i].getData().toGMTString());
            Data3.add(Float.toString(vector[i].getMedie()));
            Data1.add(Data3);
            
        }
        Data=creareData(Data1,column);
        JTable Tabel=creareTabel(Data,column);
        baza.add(Tabel);
        Button Inapoi = new Button("Inapoi");
        Inapoi.setBounds(130,500,200,40);
        Inapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                baza.dispose();
            }
        });
        
        baza.add(Inapoi);
        baza.setSize(1000,1000);
        baza.setLayout(null);
        baza.setVisible(true);
    }
	public void afisNumeDataGUI() {
		JFrame baza = new JFrame();
        Vector<Vector<String>> Data1=new Vector<>();
        Vector<Vector<String>> Data;
        Vector<String> column = creareColoana(new String[]{"Nume","Data"});
        for(int i=0;i<vector.length;i++){
            Vector<String> Data3=new Vector<>();
            Data3.add(vector[i].getNume());
            Data3.add(vector[i].getData().toGMTString());
            Data1.add(Data3);
            
        }
        Data=creareData(Data1,column);
        JTable Tabel=creareTabel(Data,column);
        baza.add(Tabel);
        Button Inapoi = new Button("Inapoi");
        Inapoi.setBounds(130,500,200,40);
        Inapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                baza.dispose();
            }
        });
        
        baza.add(Inapoi);
        baza.setSize(1000,1000);
        baza.setLayout(null);
        baza.setVisible(true);
	}
	public void afisRestantieriGUI() {
		JFrame baza = new JFrame();
        Vector<Vector<String>> Data1=new Vector<>();
        Vector<Vector<String>> Data;
        Vector<String> column = creareColoana(new String[]{"Nume","Materia", "Nota"});
		for( int i = 0; i<vector.length; i++) {
			for(int j = 0; j<vector[i].getNrMaterii(); j++) {
				if(vector[i].getSituatieCurs(j).notaSub5() == true) {
					 Vector<String> Data3=new Vector<>();
			         Data3.add(vector[i].getNume());
			         Data3.add(vector[i].getSituatieCurs(j).getMaterie());
			         Data3.add(Integer.toString(vector[i].getSituatieCurs(j).getNota()));
			         Data1.add(Data3);
				}
			}
		}
		Data=creareData(Data1,column);
        JTable Tabel=creareTabel(Data,column);
        baza.add(Tabel);
        Button Inapoi = new Button("Inapoi");
        Inapoi.setBounds(130,500,200,40);
        Inapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                baza.dispose();
            }
        });
        
        baza.add(Inapoi);
        baza.setSize(1000,1000);
        baza.setLayout(null);
        baza.setVisible(true);
	}
	public void deleteGUI() {
		JFrame Submeniu = new JFrame();
		TextField T1=new TextField();
        Label nume=new Label("Pozitia:");
        nume.setBounds(100,130,60,20);
        T1.setBounds(150,130,200,20);
        JButton Submit=new JButton("Sterge");
        Submit.setBounds(150,180,200,50);
        Submit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
                int pos;
                pos = Integer.parseInt(T1.getText());
                delete(pos);
                Label Succes=new Label("Student sters!");
                Succes.setBounds(150,280,200,20);
                Submeniu.add(Succes);
        	}
        });
        JButton Inapoi = new JButton("Inapoi");
        Inapoi.setBounds(150,230,200,50);
        Inapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Submeniu.dispose();
            }
        });
        
        Submeniu.add(nume);
        Submeniu.add(T1);
        Submeniu.add(Submit);
        Submeniu.add(Inapoi);

        Submeniu.setSize(1000,1000);
        Submeniu.setLayout(null);
        Submeniu.setVisible(true);
	}
	public void updateGUI() {
		JFrame Submeniu1=new JFrame();
		TextField T=new TextField();
        Label pozitie = new Label("Pozitia:");
        pozitie.setBounds(100,90,60,20);
        T.setBounds(150,90,200,20);
        Label Add=new Label("Introduceti datele noului student");
        Add.setBounds(100,110,300,20);
        TextField T1=new TextField();
        Label nume=new Label("Nume:");
        nume.setBounds(100,130,60,20);
        T1.setBounds(150,130,200,20);
        TextField T2=new TextField();
        Label adresa=new Label("Adresa:");
        adresa.setBounds(100,150,60,20);
        T2.setBounds(150,150,200,20);
        TextField T3=new TextField();
        Label Ziua=new Label("Ziua:");
        Ziua.setBounds(100,170,60,20);
        T3.setBounds(150,170,200,20);
        TextField T4=new TextField();
        Label Luna=new Label("Luna:");
        Luna.setBounds(100,190,60,20);
        T4.setBounds(150,190,200,20);
        TextField T5=new TextField();
        T5.setBounds(150,210,200,20);
        Label An=new Label("An:");
        An.setBounds(100,210,60,20);
        TextField T6=new TextField();
        T6.setBounds(150,230,200,20);
        Label Materie=new Label("Materie");
        Materie.setBounds(100,230,60,20);
        TextField T7=new TextField();
        Label Nota=new Label("Nota:");
        Nota.setBounds(100,250,60,20);
        T7.setBounds(150,250,200,20);

        JButton Submit=new JButton("Update");
        Submit.setBounds(150,300,200,50);
        Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	int pos;
                pos = Integer.parseInt(T.getText());
                int ziua,luna,an, nota;
                String adresa,nume,materie;
                nume = T1.getText();
                adresa = T2.getText();
                
                ziua = Integer.parseInt(T3.getText()) + 1;
                luna = Integer.parseInt(T4.getText()) - 1;
                an = Integer.parseInt(T5.getText());
                
                materie=T6.getText();
                nota = Integer.parseInt(T7.getText());

                Date data = new Date(an,luna,ziua);
               
                SituatieTotala sit = new SituatieTotala();
                SituatieCurs curs = new SituatieCurs(nota, materie);
                sit.add(curs);
                Student student=new Student(nume, adresa, data, sit);
                update(student,pos);
                Label Succes=new Label("Update realizat!");
                Succes.setBounds(150,400,200,20);
                Submeniu1.add(Succes);
            }
        });
        JButton Inapoi = new JButton("Inapoi");
        Inapoi.setBounds(150,350,200,50);
        Inapoi.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Submeniu1.dispose();
            }
        });
        Submeniu1.add(pozitie);
        Submeniu1.add(T);
        Submeniu1.add(Add);
        Submeniu1.add(T1);
        Submeniu1.add(nume);
        Submeniu1.add(T2);
        Submeniu1.add(adresa);
        Submeniu1.add(T3);
        Submeniu1.add(Ziua);
        Submeniu1.add(T4);
        Submeniu1.add(Luna);
        Submeniu1.add(T5);
        Submeniu1.add(An);
        Submeniu1.add(T6);
        Submeniu1.add(Materie);
        Submeniu1.add(T7);
        Submeniu1.add(Nota);
        Submeniu1.add(Submit);
        Submeniu1.add(Inapoi);

        Submeniu1.setSize(1000,1000);
        Submeniu1.setLayout(null);
        Submeniu1.setVisible(true);
	}
}
