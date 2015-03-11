package pt.c02classes.s01knowledge.s02app.actors;

import pt.c02classes.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IDeclaracao;
import pt.c02classes.s01knowledge.s01base.inter.IEnquirer;
import pt.c02classes.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c02classes.s01knowledge.s01base.inter.IResponder;

public class EnquirerAnimals implements IEnquirer {

	IResponder responder;
	
	public void connect(IResponder responder) {
		this.responder = responder;
	}
	
	public boolean discover() {
        IBaseConhecimento bc = new BaseConhecimento();
        IObjetoConhecimento obj;
        String animais[] = new String[5];
        java.util.Hashtable<String,Boolean> perguntados = new java.util.Hashtable<String,Boolean>();
		IDeclaracao decl;
		boolean acertei = false;
        
		bc.setScenario("animals");
    	animais = bc.listaNomes();
		
    	for(int i = 0; i < animais.length && !acertei; i++){
    		obj = bc.recuperaObjeto(animais[i]);
			decl = obj.primeira();
    		boolean primeira = true;
    		Boolean podeSer = true;
    		
    		do{
				if(primeira) primeira = false;
				else decl = obj.proxima();
				while(decl != null && perguntados.containsKey(decl.getPropriedade())){
					podeSer = perguntados.get(decl.getPropriedade());
					decl = obj.proxima();
				}
				if(decl != null){
					podeSer = responder.ask(decl.getPropriedade()).equalsIgnoreCase(decl.getValor());
					perguntados.put(decl.getPropriedade(),podeSer);
				}
			}while (decl != null && podeSer);
		
    		if (decl == null)
    			acertei = responder.finalAnswer(animais[i]);
    	}


		if (acertei)
			System.out.println("Oba! Acertei!");
		if(!acertei){
			responder.finalAnswer("nao conheco");
			System.out.println("fuem! fuem! fuem!");
		}
		
		return acertei;
	}

}
