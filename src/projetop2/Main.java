package projetop2;

import easyaccept.EasyAccept;

public class Main {
	
	public static void main(String[] args) {
		ControllerUsuario controller = new ControllerUsuario();
		args = new String[] {"projetop2.ControllerUsuario", "accept_testes/use_case_1.txt"};
		EasyAccept.main(args);

	}

}
