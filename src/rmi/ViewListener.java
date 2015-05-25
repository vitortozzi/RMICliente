
package rmi;

import entidades.Carro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import views.HomeCliente;
import views.JanelaPrincipal;
import views.JanelaConsulta;

public class ViewListener implements ActionListener {

//    private InterfaceCli refCliente;
    private InterfaceServ refServidor;
    
    //private JanelaPrincipal principal;
    private HomeCliente home;
    private JanelaConsulta consulta;
    ArrayList<Carro> carros;
    
    public ViewListener(InterfaceServ refServidor) throws RemoteException {
//        this.refCliente = refCliente;
        this.refServidor = refServidor;
        
//        this.principal = new JanelaPrincipal();
        this.home = new HomeCliente();
//        this.principal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.home.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        this.principal.setVisible(true);
        this.home.setVisible(true);
        
//        this.principal.getjButton1().addActionListener(this);
        this.home.getjButton1().addActionListener(this);
//        this.principal.getjButton2().addActionListener(this);
        populaTabela();
        
        consulta = new JanelaConsulta();
        
    }

    public void mapearAcoesBttnsJanelaConsultar() {
        this.consulta.getBtnLocar().addActionListener(this);
        this.consulta.getBtnRegistrarInteresse().addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
//        if (e.getSource().equals(this.principal.getjButton1())) {
//            try {
//                int idCarro = Integer.parseInt(this.principal.getjTextField1().getText());
//                Carro tempCarro = refServidor.getCarro(idCarro);
//                
//                this.principal.getjTextField2().setText(tempCarro.getModelo());
//                this.principal.getjTextField3().setText(tempCarro.getMarca());
//                if (tempCarro.getDisponivel()) {
//                    this.principal.getjTextField4().setText("Disponivel");
//                } else {
//                    this.principal.getjTextField4().setText("Indisponivel");
//                }
//                this.principal.getjTextField5().setText(tempCarro.getPlaca());
//                this.principal.getjTextField6().setText(String.valueOf(tempCarro.getPrecoDiaria()));
//            } catch (RemoteException ex) {
//                Logger.getLogger(ViewListener.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
        if(e.getSource().equals(this.home.getjButton1())){
            int rowIndex = home.getjTable1().getSelectedRow();
            
            if(rowIndex != -1){
                String nomeEscolhido = (String) home.getjTable1().getModel().getValueAt(rowIndex, 0);
                Carro temp = null;
                for(Carro c: carros){
                    if(c.getModelo().equals(nomeEscolhido)){
                        temp = c;
                    }
                }
                if(temp!=null){
                    consulta.getlModelo().setText(temp.getModelo());
                    consulta.gettMarca().setText(temp.getMarca());
                    consulta.gettValor().setText(String.valueOf(temp.getPrecoDiaria()));
                    consulta.gettPlaca().setText(temp.getPlaca());
                    consulta.setVisible(true);
                    if(!temp.getDisponivel()){
                        consulta.gettStatus().setText("Alugado");
                        consulta.getBtnLocar().setEnabled(false);
                    }else{
                        consulta.gettStatus().setText("Disponível");
                    }
                }
            }
        }
        
    }

    private void populaTabela() throws RemoteException {
        DefaultTableModel model = (DefaultTableModel) home.getjTable1().getModel();
        carros = refServidor.requestCarros();
        for(Carro c: carros){
            model.addRow(new Object[]{c.getModelo(),c.getPrecoDiaria()});
        }
    }
    
}
