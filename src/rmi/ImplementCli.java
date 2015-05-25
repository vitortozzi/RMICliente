
package rmi;

import entidades.Carro;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/* Implementação da Interface do Cliente */
public class ImplementCli extends UnicastRemoteObject implements InterfaceCli {

    /* dados do Cliente */
    private int idCliente;
    private String nomeCliente;

    /* referência da interface do Servidor */
    private InterfaceServ refServenteServ;

    /* referência do controller das Views */
    private ViewListener view;

    /* é no construtor que será iniciado o controller das Views */
    public ImplementCli(InterfaceServ refServenteServ, int idCli, String nomeCli) throws RemoteException {
        /* iniciando valores passados da classe Cliente */
        this.idCliente = idCli;
        this.nomeCliente = nomeCli;

        /* guardando referência do Servidor */
        this.refServenteServ = refServenteServ;

        /* instanciando um objeto da classe ViewListener
            cada Cliente possui um ViewListener, afinal cada Cliente possui uma interface gráfica própria */
        this.view = new ViewListener(refServenteServ, this);

        /* teste inicial */
        refServenteServ.chamar("cliente mandando oi", this);
    }

    @Override
    /* método para receber notificações sobre a baixa do preço da diária de um Carro que o cliente
        havia registrado interesse no passado */
    public void receberNotificacao(Carro c) throws RemoteException {
        view.atualizaTabela(c);
    }
    
    @Override
    public void echo(String msg) throws RemoteException {
        System.out.println(msg);
    }

    @Override
    public int getIdCliente() {
        return idCliente;
    }

    @Override
    public String getNomeCliente() {
        return nomeCliente;
    }
    
    
    
}
