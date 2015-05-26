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

/* Classe que controla os eventos das views */
public class ViewListener implements ActionListener {

    //Referencias à clientes e servidor
    private InterfaceCli refCliente;
    private InterfaceServ refServidor;

//    Janelas
    private HomeCliente home;
    private JanelaConsulta consulta;
    private JanelaLocacao janelaLocacao;
    
//    Lista de carros
    private ArrayList<Carro> carros;
//    Carro escolhido nas operações
    private Carro selectedCar = null;

    public ViewListener(InterfaceServ refServidor, InterfaceCli refCliente) throws RemoteException {
        this.refCliente = refCliente;
        this.refServidor = refServidor;

        this.home = new HomeCliente();
        this.home.getjLabel1().setText("Locação de Carros - " + this.refCliente.getNomeCliente());
        this.home.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.home.setVisible(true);

        this.home.getjButton1().addActionListener(this);
        //Recebe lista de carros do servidor
        populaTabela();

        consulta = new JanelaConsulta();
        janelaLocacao = new JanelaLocacao();

    }

//    Mapeamento de Listeners
    public void mapearAcoesBttnsJanelaConsultar() {
        consulta.getBtnLocar().addActionListener(this);
        consulta.getBtnRegistrarInteresse().addActionListener(this);
        consulta.getBtnVoltar().addActionListener(this);
    }

    //    Mapeamento de Listeners
    public void mapearAcoesBttnsJanelaLocar() {
        janelaLocacao.getBtnFinalizar().addActionListener(this);
        janelaLocacao.getBtnCancelar().addActionListener(this);
    }

//    Eventos dos listeners
    @Override
    public void actionPerformed(ActionEvent e) {

//        Botão consultar
        if (e.getSource() == this.home.getjButton1()) {
            int rowIndex = home.getjTable1().getSelectedRow();
            if (rowIndex != -1) {
                String placaEscolhido = (String) home.getjTable1().getModel().getValueAt(rowIndex, 2);
//                Procura pela placa selecionada na lista de carros
                for (Carro c : carros) {
                    if (c.getPlaca().equals(placaEscolhido)) {
                        try {
                            // Busca o carro com as informações atualizadas do servidor
                            selectedCar = refServidor.getCarro(c.getId());
                        } catch (RemoteException ex) {
                            Logger.getLogger(ViewListener.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        break;
                    }
                }
                if (selectedCar != null) {
//                    Preenche os dados da janela de consulta
                    consulta = new JanelaConsulta();
                    consulta.getlModelo().setText(selectedCar.getModelo());
                    consulta.gettMarca().setText(selectedCar.getMarca());
                    consulta.gettValor().setText(String.valueOf(selectedCar.getPrecoDiaria()));
                    consulta.gettPlaca().setText(selectedCar.getPlaca());
                    consulta.setVisible(true);
//                    Disponibilidade do carro para ser alugado
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
        } 
//        Botão Voltar da janela de consulta
        else if (e.getSource() == consulta.getBtnVoltar()) {
            consulta.dispose();
//            Botão fazer locação da janela de consulta
        } else if (e.getSource().equals(consulta.getBtnLocar())) {
            if (selectedCar != null) {
//                Dados da janela de locação são preenchidos
                janelaLocacao = new JanelaLocacao();
                janelaLocacao.gettModelo().setText(selectedCar.getModelo());
                janelaLocacao.gettMarca().setText(selectedCar.getMarca());
                janelaLocacao.gettValor().setText(String.valueOf(selectedCar.getPrecoDiaria()));
                janelaLocacao.gettPlaca().setText(selectedCar.getPlaca());
                janelaLocacao.setVisible(true);
                mapearAcoesBttnsJanelaLocar();
            }
        } 
//        Botão registrar Interesse
        else if (e.getSource() == consulta.getBtnRegistrarInteresse()) {
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
        } 
//        Botão cancelar na janela de locação
        else if (e.getSource() == janelaLocacao.getBtnCancelar()) {
            janelaLocacao.dispose();
        } 
//        Botão finalizar locação na janela de locação
        else if (e.getSource() == janelaLocacao.getBtnFinalizar()) {
//            Recupera datas do campo
            String sRetirada = janelaLocacao.gettDataRetirada1().getText() + "/" + janelaLocacao.gettDataRetirada2().getText() + "/" + janelaLocacao.gettDataRetirada3().getText();
            String sDevolucao = janelaLocacao.gettDataDevolucao1().getText() + "/" + janelaLocacao.gettDataDevolucao2().getText() + "/" + janelaLocacao.gettDataDevolucao3().getText();
//          Formatação das datas;
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date retirada = null;
            Date devolucao = null;
            try {
                retirada = formatter.parse(sRetirada);
                devolucao = formatter.parse(sDevolucao);
            } catch (ParseException ex) {
                Logger.getLogger(ViewListener.class.getName()).log(Level.SEVERE, null, ex);
            }
//          Calculo de dias entre a retirada e devolução
            int diffInDays = (int) ((devolucao.getTime() - retirada.getTime()) / (1000 * 60 * 60 * 24));
            selectedCar.setLocalDevolucao(janelaLocacao.gettDevolucao().toString());
            selectedCar.setLocalRetirada(janelaLocacao.gettRetirada().toString());
            String msg = "Valor da diária do veículo: R$ " + selectedCar.getPrecoDiaria() + "\nValor total do aluguél: R$ " + diffInDays * selectedCar.getPrecoDiaria()
                    + "\nDias de locação: " + diffInDays + " dias"
                    + "\nForma de pagamento: ";
            if (janelaLocacao.getrVista().isSelected()) {
                msg = msg + "à Vista\nTotal: R$ " + diffInDays * selectedCar.getPrecoDiaria();
            } 
            // Calculo do parcelamento (Caso tenha selecionado cartão de crédito)
            else {
                int parcelas;
//                Quebra a string do combobox fornecendo a quantidade de parcelas
                parcelas = Integer.parseInt(janelaLocacao.getcParcelamento().getSelectedItem().toString().substring(0, 1));
                double valorParcelado = (diffInDays * selectedCar.getPrecoDiaria()) / (parcelas);
                msg = msg + "Cartão de Crédito\n" + janelaLocacao.getcParcelamento().getSelectedItem() + " de R$ " + valorParcelado;
            }
//            Diálogo para confirmação da operação
            int resultDialog = JOptionPane.showConfirmDialog(janelaLocacao, msg, "Locação", JOptionPane.YES_NO_OPTION);

//            Caso prossiga com a locação
            if (resultDialog == JOptionPane.YES_OPTION) {
                boolean result = false;
                try {
                    result = refServidor.locarCarro(selectedCar, refCliente);
                } catch (RemoteException ex) {
                    Logger.getLogger(ViewListener.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Verificação de sucesso na locação
                if (result) {
                    JOptionPane.showMessageDialog(janelaLocacao, "Locação realizada com sucesso!");
                    janelaLocacao.setVisible(false);
                    consulta.setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(janelaLocacao, "Não foi possível registrar a locação\nTalvez o carro selecionado já tenha sido alugado."
                            + " Tente consultar o veículo novamente!");
                    janelaLocacao.setVisible(false);
                    consulta.setVisible(false);
                }
            }

        }

    }

//    Preenche tabela com os carros do servidor
    private void populaTabela() throws RemoteException {
        DefaultTableModel model = (DefaultTableModel) this.home.getjTable1().getModel();
        this.carros = this.refServidor.getAllCarros();
        for (Carro c : this.carros) {
            model.addRow(new Object[]{c.getMarca(), c.getModelo(), c.getPlaca(), c.getPrecoDiaria()});
        }
    }
// Atualiza valor do preços de um carro
    public void atualizaTabela(Carro c, boolean flag) throws RemoteException {
        for (int i = 0; i < this.home.getjTable1().getRowCount(); i++) {
            if (this.home.getjTable1().getValueAt(i, 2).equals(c.getPlaca())) {
                this.home.getjTable1().setValueAt(c.getPrecoDiaria(), i, 3);
            }
        }
        if (flag) {
            JOptionPane.showMessageDialog(home, this.refCliente.getNomeCliente() + ", o veículo " + c.getModelo() + " que você registrou interesse "
                    + "teve uma redução no valor da diária!\nConfira a tabela de valores para mais detalhes.");
        }
    }
}
