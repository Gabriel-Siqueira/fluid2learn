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

public class Orchestrator
{
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
			base.setScenario("animals");
			String listaAnimais[] = base.listaNomes();
	        for (int animal = 0; animal < listaAnimais.length; animal++) {
				System.out.println("Enquirer com " + listaAnimais[animal] + "...");
				stat = new Statistics();
				resp = new ResponderAnimals(stat, listaAnimais[animal]);
				enq = new EnquirerAnimals();
				enq.connect(resp);
				enq.discover();
				System.out.println("----------------------------------------------------------------------------------------\n");
	        }
			break;
		case "M": 
//			base.setScenario("maze");
			System.out.println("Enquirer com Mordor...");
			stat = new Statistics();
			resp = new ResponderMaze(stat, "mordor");
			enq = new EnquirerMaze();
			enq.connect(resp);
			enq.discover();
			System.out.println("----------------------------------------------------------------------------------------\n");
		
			break;
		}
		entrada.close();
	}
}

















//package pt.c02classes.s01knowledge.s02app.app;
//
//import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
//import pt.c02classes.s01knowledge.s01base.impl.Statistics;
//import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
//import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
//import pt.c02classes.s01knowledge.s01base.inter.IResponder;
//import pt.c02classes.s01knowledge.s01base.inter.IStatistics;
//import pt.c02classes.s01knowledge.s02app.actors.EnquirerAnimals;
//import pt.c02classes.s01knowledge.s02app.actors.ResponderAnimals;
//
//public class Orchestrator
//{
//	public static void main(String[] args)
//	{
//		IEnquirer enq;
//		IResponder resp;
//		IStatistics stat;
//		
//		IBaseConhecimento base = new BaseConhecimento();
//
//		base.setScenario("animals");
//		String listaAnimais[] = base.listaNomes();
//        for (int animal = 0; animal < listaAnimais.length; animal++) {
//			System.out.println("Enquirer com " + listaAnimais[animal] + "...");
//			stat = new Statistics();
//			resp = new ResponderAnimals(stat, listaAnimais[animal]);
//			enq = new EnquirerAnimals();
//			enq.connect(resp);
//			enq.discover();
//			System.out.println("----------------------------------------------------------------------------------------\n");
//        }		
//	}
//}
