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
    String animais[] = {"aranha","camarao","humano","pikachu","tiranossauro"};

    public Enquirer()
	{
        animais = {"aranha","camarao","humano","pikachu","tiranossauro"};
	}


	@Override
	public void connect(IResponder responder)
	{
		java.util.Set<String> perguntados = new java.util.HashSet<String>();
		IBaseConhecimento bc = new BaseConhecimento();
		IDeclaracao decl;
		boolean primeira;
		boolean acertei = false;

		for(int i = 0; i < animais.length && !acertei; i++){

			obj = bc.recuperaObjeto(animais[i]);
			decl = obj.primeira();
			primeira = true;

			do{
				if(primeira) primeira = false;
				else decl = obj.proxima();
				while(decl != null && perguntados.contains(decl.getPropriedade())) decl = obj.proxima();
				if(decl != null) perguntados.add(decl.getPropriedade());
			}while (decl != null && responder.ask(decl.getPropriedade()).equalsIgnoreCase(decl.getValor()));

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
