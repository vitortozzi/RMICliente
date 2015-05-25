/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 *
 * @author a1260421
 */
public class Cliente {
    
    public Cliente() {}                                                                                                                                                                                                                                                                                                                                    
    
    public static void main(String[] args) throws RemoteException {
        try {
            Registry referenciaServicoNomes = LocateRegistry.getRegistry(9898);
            InterfaceServ refServenteServidor = (InterfaceServ) referenciaServicoNomes.lookup("ServenteServ");
            
            // Aqui ta rolando uma treta.
//            Na ViewListener, precisamos enviar uma referencia do cliente que está nessa classe, pra quando chamar
//            algum método do servidor, ele usar essa referencia pra se identificar (ex: regfistrar interesse)
//            
//            O problema é que quando a referencia serventeCli precisa de uma referencia à view (ViewListener) pois são os métodos nessa classe que
//            o servidor vai chamar (ex alterar preçoDiaria) então para alterar dados da view, precisa de uma referencia
//                    
//            Resumindo: 
//            ViewListener depende da referincia do cliente
//            Referencia do cliente depende da view
//                    
//            Até tentei criar a viewListener nula, entao teoricamente se eu instanciasse esse objeto depois, ele seria atualizado pra qualquer
//            que eu tenha mandado ele. Pois não foi o que acontecer, o método atualizarLista do ImplementCli quando
//            tenta acessar a view (referencia pra ViewListener) essa referencia está nula. Aí não atualiza porra nenhuma
//            na view do cliente.
                           
            
            ViewListener viewListener = null;
            ImplementCli serventeCli = new ImplementCli(refServenteServidor, viewListener);          
            viewListener = new ViewListener(refServenteServidor, serventeCli);
            
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
        
    }
    
}