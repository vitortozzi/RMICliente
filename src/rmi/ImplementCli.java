/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import entidades.Carro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author a1260421
 */
public class ImplementCli extends UnicastRemoteObject implements InterfaceCli {

    private InterfaceServ refServenteServ;
    private ViewListener view;
    
    public ImplementCli(InterfaceServ refServenteServ) throws RemoteException {
        this.refServenteServ = refServenteServ;
        this.view = new ViewListener(refServenteServ, this);

        refServenteServ.chamar("cliente mandando oi", this);
    }
    
    @Override
    public void echo(String msg) throws RemoteException {
        System.out.println(msg);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void receberNotificacao(Carro c) throws RemoteException {
        view.atualizaTabela(c);
    }
    
    
    
}
