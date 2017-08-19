package es.uned.master.software.tfm.adtm.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.uned.master.software.tfm.adtm.amqp.receiver.ReceiverConsumer;
import es.uned.master.software.tfm.adtm.entity.Transaction;
import es.uned.master.software.tfm.adtm.service.TransactionDataService;

/**
 * Clase principal para la gestion de transacciones distribuidas asincronas
 * 
 * @author Francisco Cilleruelo
 */
@Component
public class DistributedTransactionManager {
	
	@Autowired
	private TransactionDataService transactionDataService;
	
	/**
	 * Metodo invocado por el emisor de una transaccion para comenzarla
	 * 
	 * @param transaction Transaccion a enviar
	 */
	public void sendTransaction(Transaction<?> transaction){
		transactionDataService.startTransaction(transaction);
	}
	
	/**
	 * Metodo invocado por el receptor de una transaccion para recibirla
	 * 
	 * @param responseQueueName Nombre de la cola donde se espera recibir la transaccion enviada por el emisor
	 * @param receiverConsumer Componente encargado de recibir y procesar la transaccion recibida
	 */
	public void recieveTransaction(String responseQueueName, ReceiverConsumer<?> receiverConsumer){
		transactionDataService.receiveTransaction(responseQueueName, receiverConsumer);
	}

}
