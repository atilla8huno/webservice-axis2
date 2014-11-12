package br.com.oobj.axis2;

import br.com.oobj.axis2.stub.NotaFiscalWSStub;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class NotaFiscalClient {

	private static final String ENDPOINT = "http://localhost:8080/axis2/services/NotaFiscalWS";
	
	public static void main(String[] args) {
		try {
			NotaFiscalWSStub stub = new NotaFiscalWSStub(ENDPOINT);
			NotaFiscalWSStub.ConsomeXML consomeXMLRequest = new NotaFiscalWSStub.ConsomeXML();
//			NotaFiscalWSStub.ImprimirNotaDaEmpresa imprimirRequest = new NotaFiscalWSStub.ImprimirNotaDaEmpresa();
			
			NotaFiscal nfe = NotaFiscal.create("Oobj Axis2");
			consomeXMLRequest.setNfeEmXML(toXML(nfe));
//			imprimirRequest.setEmpresa("Oobj Axis2 Maluco");
			
			NotaFiscalWSStub.ConsomeXMLResponse consomeXMLResp = stub.consomeXML(consomeXMLRequest);
//			NotaFiscalWSStub.ImprimirNotaDaEmpresaResponse imprimirResp = stub.imprimirNotaDaEmpresa(imprimirRequest);
			
			System.out.println(fromXML(consomeXMLResp.get_return()));
//			System.out.println(fromXML(imprimirResp.get_return()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Obtém o objeto NotaFiscal através de um XML em String
	 * 
	 * @param xml
	 * @return NotaFiscal
	 */
	private static NotaFiscal fromXML(String xml) {
		return (NotaFiscal) getXStream().fromXML(xml);
	}
	
	/**
	 * Converte o objeto NotaFiscal em um XML para devolver no WS
	 * 
	 * @param nfe
	 * @return String (xml)
	 */
	private static String toXML(NotaFiscal nfe) {
		return getXStream().toXML(nfe);
	}
	
	/**
	 * Cria uma instância do XStream e a retorna 
	 * 
	 * @return XStream
	 */
	private static XStream getXStream() {
		return new XStream(new DomDriver());
	}
}
