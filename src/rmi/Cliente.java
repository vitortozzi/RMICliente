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
            ImplementCli serventeCli = new ImplementCli(refServenteServidor);
            ViewListener viewListener = new ViewListener(refServenteServidor);
            
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
        
    }
    
}