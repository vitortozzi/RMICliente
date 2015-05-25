
package rmi;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/* 
    Classe inicial do Cliente
    Tem as seguintes funções:
        - instanciar o(s) cliente(s) do sistema
        - capturar a referência do servidor registrada no Serviço de Nomes
        - passar a referência da interface do servidor para o(s) cliente(s)
*/
public class Cliente {
    
    /* construtor vazio */
    public Cliente() {}                                                                                                                                                                                                                                                                                                                                    
    
    /* método main, esse classa deve ser executada APÓS o servidor */
    public static void main(String[] args) throws RemoteException {
        try {
            /* capturar o serviço de nomes, iniciado pelo servidor na porta 9898 */
            Registry referenciaServicoNomes = LocateRegistry.getRegistry(9898);
            
            /* procurar no serviço de nomes a referência do servidor, com o nome ServentServ */
            InterfaceServ refServenteServidor = (InterfaceServ) referenciaServicoNomes.lookup("ServenteServ");

            /* instanciar o(s) cliente(s) do sistema, passando a referência do servidor e iniciando os dados do(s) cliente(s),
                com id e nome */
            ImplementCli serventeCli1 = new ImplementCli(refServenteServidor, 111, "Fulano");
            ImplementCli serventeCli2 = new ImplementCli(refServenteServidor, 222, "Sicrano");
            ImplementCli serventeCli3 = new ImplementCli(refServenteServidor, 333, "Beltrano");
            
        } catch (Exception e) {
            System.out.println("erro: " + e.getMessage());
        }
        
    }
    
}