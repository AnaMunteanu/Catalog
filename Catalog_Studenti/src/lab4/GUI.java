package lab4;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class GUI {
	
	private Controller ctrl;
	
	public GUI() {
		
		ctrl = new Controller();
	}
	public void run(){
		String[] listaMaterii = new String[0];
		citStudenti();
		listaMaterii = citMaterii();
		ctrl.calculMedie();
		
        JFrame Meniu1=new JFrame();
        
        JButton afiseaza5=new JButton("Afisare baza de date");
        afiseaza5.setBounds(300,90,300,50);
        afiseaza5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrl.afisTotGUI();
            }
        });
        
        JButton adauga =new JButton("Adauga un student");
        adauga.setBounds(300,440,300,50);
        adauga.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    ctrl.adaugareGUI();
                    scriereStudenti();
    				scriereMaterii();
            }
        });
        
        JButton update =new JButton("Update un student");
        update.setBounds(300,140,300,50);
        update.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrl.updateGUI();
                scriereStudenti();
				scriereMaterii();
            }
        });
        
        JButton sterge =new JButton("Sterge un student");
        sterge.setBounds(300,190,300,50);
        sterge.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ctrl.deleteGUI();
                scriereStudenti();
				scriereMaterii();
            }
        });
        
        JButton afiseaza1 =new JButton("Ordonare desc dupa medie");
        afiseaza1.setBounds(300,240,300,50);
        afiseaza1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               ctrl.ordonareMedie();
               ctrl.afisNumeMedieGUI();
            }
        });
        
        JButton afiseaza2 =new JButton("Ordonare desc a integralistilor dupa medie");
        afiseaza2.setBounds(300,290,300,50);
        afiseaza2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RepoStudenti rez = new RepoStudenti();
				rez = ctrl.integralistiDupaMedie();
				Controller ctr2 = new Controller(rez);
				ctr2.afisNumeMedieGUI();
            }
        });
        
        JButton afiseaza3 =new JButton("Ordonare cresc dupa varsta");
        afiseaza3.setBounds(300,340,300,50);
        afiseaza3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	ctrl.ordonareVarsta();
            	ctrl.afisNumeDataGUI();
            }
        });
        
        JButton afiseaza4 =new JButton("Restantieri si materiile nepromovate");
        afiseaza4.setBounds(300,390,300,50);
        afiseaza4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	RepoStudenti rez = new RepoStudenti();
				rez = ctrl.studentiNeintegralisti();
				rez.afisRestantieriGUI();
            }
        });
        
        
        JButton iesire=new JButton("Iesire");
        iesire.setBounds(300,490,300,50);
        iesire.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Meniu1.dispose();
            }
        });
       // Meniu1.add(Welcome);
        //Meniu1.add(Welcome2);
        Meniu1.add(adauga);
        Meniu1.add(update);
        Meniu1.add(sterge);
        Meniu1.add(afiseaza1);
        Meniu1.add(afiseaza2);
        Meniu1.add(afiseaza3);
        Meniu1.add(afiseaza4);
        Meniu1.add(afiseaza5);
        Meniu1.add(iesire);
        Meniu1.setSize(1000,1000);
        Meniu1.setLayout(null);
        Meniu1.setVisible(true);
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

}


