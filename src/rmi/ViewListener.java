package rmi;

import entidades.Carro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import views.HomeCliente;
import views.JanelaConsulta;
import views.JanelaLocacao;

public class ViewListener implements ActionListener {

    private InterfaceCli refCliente;
    private InterfaceServ refServidor;

    //private JanelaPrincipal principal;
    private HomeCliente home;
    private JanelaConsulta consulta;
    private JanelaLocacao janelaLocacao;
    private ArrayList<Carro> carros;
    private Carro selectedCar = null;

    public ViewListener(InterfaceServ refServidor, InterfaceCli refCliente) throws RemoteException {
        this.refCliente = refCliente;
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
        janelaLocacao = new JanelaLocacao();

    }

    public void mapearAcoesBttnsJanelaConsultar() {
        consulta.getBtnLocar().addActionListener(this);
        consulta.getBtnRegistrarInteresse().addActionListener(this);
        consulta.getBtnVoltar().addActionListener(this);
    }

    public void mapearAcoesBttnsJanelaLocar() {
        janelaLocacao.getBtnFinalizar().addActionListener(this);
        janelaLocacao.getBtnCancelar().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this.home.getjButton1()) {
            int rowIndex = home.getjTable1().getSelectedRow();
            if (rowIndex != -1) {
                String nomeEscolhido = (String) home.getjTable1().getModel().getValueAt(rowIndex, 0);

                for (Carro c : carros) {
                    if (c.getModelo().equals(nomeEscolhido)) {
                        try {
                            selectedCar = refServidor.getCarro(c.getId());
                        } catch (RemoteException ex) {
                            Logger.getLogger(ViewListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
                if (selectedCar != null) {
                    consulta = new JanelaConsulta();
                    consulta.getlModelo().setText(selectedCar.getModelo());
                    consulta.gettMarca().setText(selectedCar.getMarca());
                    consulta.gettValor().setText(String.valueOf(selectedCar.getPrecoDiaria()));
                    consulta.gettPlaca().setText(selectedCar.getPlaca());
                    consulta.setVisible(true);
                    if (!selectedCar.getDisponivel()) {
                        consulta.gettStatus().setText("Alugado");
                        consulta.getBtnLocar().setEnabled(false);
                    } else {
                        consulta.gettStatus().setText("Disponível");
                        consulta.getBtnLocar().setEnabled(true);
                    }
                    mapearAcoesBttnsJanelaConsultar();
                }
            }
        } else if (e.getSource() == consulta.getBtnVoltar()) {
//            consulta.setVisible(false);
            consulta.dispose();
        } else if (e.getSource().equals(consulta.getBtnLocar())) {
            if (selectedCar != null) {
                janelaLocacao = new JanelaLocacao();
                janelaLocacao.gettModelo().setText(selectedCar.getModelo());
                janelaLocacao.gettMarca().setText(selectedCar.getMarca());
                janelaLocacao.gettValor().setText(String.valueOf(selectedCar.getPrecoDiaria()));
                janelaLocacao.gettPlaca().setText(selectedCar.getPlaca());
                janelaLocacao.setVisible(true);
                mapearAcoesBttnsJanelaLocar();
            }
        } else if (e.getSource() == consulta.getBtnRegistrarInteresse()) {
            boolean result = false;
            try {
                result = refServidor.registrarIntCarro(selectedCar.getId(), refCliente);
            } catch (RemoteException ex) {
                Logger.getLogger(ViewListener.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (result) {
                JOptionPane.showMessageDialog(consulta, "Interesse registrado com sucesso");
            } else {
                JOptionPane.showMessageDialog(consulta, "Não foi possível registrar interesse no veículo");
            }
        } else if (e.getSource() == janelaLocacao.getBtnCancelar()) {
//            janelaLocacao.setVisible(false);
            janelaLocacao.dispose();
        } else if (e.getSource() == janelaLocacao.getBtnFinalizar()) {
            String sRetirada = janelaLocacao.gettDataRetirada1().getText() + "/" + janelaLocacao.gettDataRetirada2().getText() + "/" + janelaLocacao.gettDataRetirada3().getText();
            String sDevolucao = janelaLocacao.gettDataDevolucao1().getText() + "/" + janelaLocacao.gettDataDevolucao2().getText() + "/" + janelaLocacao.gettDataDevolucao3().getText();

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date retirada = null;
            Date devolucao = null;
            try {
                retirada = formatter.parse(sRetirada);
                devolucao = formatter.parse(sDevolucao);
            } catch (ParseException ex) {
                Logger.getLogger(ViewListener.class.getName()).log(Level.SEVERE, null, ex);
            }

            int diffInDays = (int) ((devolucao.getTime() - retirada.getTime()) / (1000 * 60 * 60 * 24));

            JOptionPane.showMessageDialog(janelaLocacao, "Dias para locação: " + diffInDays);

        }

    }

    private void populaTabela() throws RemoteException {
        DefaultTableModel model = (DefaultTableModel) home.getjTable1().getModel();
        carros = refServidor.getAllCarros();
        for (Carro c : carros) {
            model.addRow(new Object[]{c.getModelo(), c.getPrecoDiaria()});
        }
    }

    public void atualizaTabela(Carro c) throws RemoteException {
        for (int i = 0; i < this.home.getjTable1().getRowCount(); i++) {
            if(this.home.getjTable1().getValueAt(i, 0).equals(c.getModelo())){
                this.home.getjTable1().setValueAt(c.getPrecoDiaria(), i, 1);
            }
        }
    }
}
