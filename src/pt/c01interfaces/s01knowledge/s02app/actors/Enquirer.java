package pt.c01interfaces.s01knowledge.s02app.actors;

import pt.c01interfaces.s01knowledge.s01base.impl.BaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IBaseConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IDeclaracao;
import pt.c01interfaces.s01knowledge.s01base.inter.IEnquirer;
import pt.c01interfaces.s01knowledge.s01base.inter.IObjetoConhecimento;
import pt.c01interfaces.s01knowledge.s01base.inter.IResponder;


public class Enquirer implements IEnquirer
{
    IObjetoConhecimento obj;
    IBaseConhecimento bc;
    String animais[];

    public Enquirer()
	{
    	bc = new BaseConhecimento();
    	animais = new String[5];
    	animais = bc.listaNomes();
	}


	@Override
	public void connect(IResponder responder)
	{
        java.util.Hashtable<String,Boolean> perguntados = new java.util.Hashtable<String,Boolean>();
		IDeclaracao decl;
		boolean primeira;
		boolean acertei = false;
		Boolean resposta = true;

		for(int i = 0; i < animais.length && !acertei; i++){

			obj = bc.recuperaObjeto(animais[i]);
			decl = obj.primeira();
			primeira = true;

			do{
				if(primeira) primeira = false;
				else decl = obj.proxima();
				while(decl != null && perguntados.containsKey(decl.getPropriedade())){
					resposta = perguntados.get(decl.getPropriedade());
					decl = obj.proxima();
					System.out.println("as");
				}
				if(decl != null){
					resposta = responder.ask(decl.getPropriedade()).equalsIgnoreCase(decl.getValor());
					perguntados.put(decl.getPropriedade(),resposta);
				}
			}while (decl != null && resposta);

			if (decl == null)
				acertei = responder.finalAnswer(animais[i]);


			if (acertei)
				System.out.println("Oba! Acertei!");
		}
		if(!acertei){
			responder.finalAnswer("nao conheco");
			System.out.println("fuem! fuem! fuem!");
		}
	}
}
