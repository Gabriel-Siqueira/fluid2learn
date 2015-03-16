package pt.c02classes.s01knowledge.s02app.app;

import java.util.Scanner;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.impl.Statistics;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;
import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
import pt.c02classes.s01knowledge.s02app.actors.EnquirerMaze;
import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
import pt.c02classes.s01knowledge.s02app.actors.ResponderMaze;

public class OrquestratorInit {
	public static void main(String[] args)
	{
		IEnquirer enq;
		IResponder resp;
		IStatistics stat;
		Scanner entrada =  new Scanner(System.in);
		String prog = null;
		IBaseConhecimento base = new BaseConhecimento();
		System.out.println("Entre com 'A' para Amimals ou 'M' para Maze ");
		prog = entrada.nextLine();


		switch(prog.toUpperCase()) {
		case "A": 
			String animal;
			base.setScenario("animals");
			String listaAnimais[] = base.listaNomes();
			System.out.print("Escolha o animal entre: " + listaAnimais[0]);
			for(int i = 1; i < listaAnimais.length; i++){
				System.out.print(", " + listaAnimais[i]);
			}
			System.out.println(".");
	        animal = entrada.nextLine();
			System.out.println("Enquirer com " + animal + "...");
			stat = new Statistics();
			resp = new ResponderAnimals(stat, animal);
			enq = new EnquirerAnimals();
			enq.connect(resp);
			enq.discover();
			System.out.println("----------------------------------------------------------------------------------------\n");
			break;
		case "M":
			String labirinto;
			base.setScenario("maze");
			String listaLabirintos[] = base.listaNomes();
			System.out.print("Escolha o labirinto entre: " + listaLabirintos[0]);
			for(int i = 1; i < listaLabirintos.length; i++){
				System.out.print(", " + listaLabirintos[i]);
			}
			System.out.println(".");
	        labirinto = entrada.nextLine();
			System.out.println("Enquirer com " + labirinto + "...");
			stat = new Statistics();
			resp = new ResponderMaze(stat, labirinto);
			enq = new EnquirerMaze();
			enq.connect(resp);
			enq.discover();
			System.out.println("----------------------------------------------------------------------------------------\n");
		
			break;
		}
		entrada.close();
	}
}
