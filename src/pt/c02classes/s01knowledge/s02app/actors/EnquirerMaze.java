package pt.c02classes.s01knowledge.s02app.actors;

import java.util.HashSet;
import java.util.Hashtable;

import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerMaze implements IEnquirer {

	IResponder responder;
	Hashtable<Integer,HashSet<Integer>> visitado = new Hashtable<Integer,HashSet<Integer>>();
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean acha_saida(String direcao, int x, int y){
		HashSet<Integer> visitados_linha;
		if(!visitado.containsKey(y)){
			visitados_linha = new HashSet<Integer>();
			visitado.put(y, visitados_linha);
		}else{
			visitados_linha = visitado.get(y);
			if(visitados_linha.contains(x)) return false;
		}
		visitados_linha.add(x);
		String resposta = responder.ask(direcao); 
		switch(resposta){
			case "parede": return false;
			case "entrada": return false;
			case "passagem": if(!responder.move(direcao)) return false; break;
			case "saida": responder.move(direcao); return true;
		}
		
		if(direcao != "sul") 
			if(acha_saida("norte",x,y + 1)) 
				return true;
		if(direcao != "norte") 
			if(acha_saida("sul",x,y - 1)) 
				return true;
		if(direcao != "oeste") 
			if(acha_saida("leste",x + 1,y)) 
				return true;
		if(direcao != "leste") 
			if(acha_saida("oeste",x - 1,y)) 
				return true;
		return false;
	}
	
	public boolean discover() {
		
		if(!acha_saida("norte",0,1))
			if(!acha_saida("sul",0,-1))
				if(!acha_saida("leste",1,0))
					acha_saida("oeste",-1,0);
		if (responder.finalAnswer("cheguei"))
			System.out.println("Você encontrou a saida!");
		else
			System.out.println("Fuém fuém fuém!");
		return true;
	}
	
}
