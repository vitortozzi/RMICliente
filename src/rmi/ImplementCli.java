/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import entidades.Carro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 *
 * @author a1260421
 */
public class ImplementCli extends UnicastRemoteObject implements InterfaceCli {

    InterfaceServ refServenteServ;
    ViewListener view;
    
    public ImplementCli(InterfaceServ refServenteServ, ViewListener view) throws RemoteException {
        this.refServenteServ = refServenteServ;
        this.view = view;
        refServenteServ.chamar("cliente mandando oi", this);
    }
    
    @Override
    public void echo(String msg) throws RemoteException {
        System.out.println(msg);
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizarLista(Carro c) throws RemoteException {
        // Vai dar pau aqui, view nula
        view.atualizaTabela(c);
    }
    
    
    
}
