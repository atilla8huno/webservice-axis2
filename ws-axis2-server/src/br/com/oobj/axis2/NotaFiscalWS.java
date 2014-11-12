package br.com.oobj.axis2;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class NotaFiscalWS {

	public String consomeXML(String nfeEmXML) {
		NotaFiscal nfe = NotaFiscal.create("Oobj TI");//fromXML(nfeEmXML);
		nfe = tratarNFe(nfe);
		
//		return Response.status(200).entity(toXML(nfe)).build();
		return toXML(nfe);
	}
	
	public String imprimirNotaDaEmpresa(String empresa) {
    	System.out.println(empresa);
        return toXML(NotaFiscal.create(empresa));
    }

	/**
	 * Processa a nota fiscal e altera o status
	 * 
	 * @param nfe
	 * @return NotaFiscal
	 */
	private NotaFiscal tratarNFe(NotaFiscal nfe) {
		contarAte(150000);
		nfe.setStatus("AUTORIZADO");
		return nfe;
	}

	/**
	 * Conta de 0 até o valor recebido
	 * 
	 * @param valor
	 */
	private void contarAte(int valor) {
		System.out.println("Contando até "+valor+" para fingir um processamento");
		for (int i=0; i<valor; i++) {
		}
		System.out.println("Fim da contagem");
	}
	
	/**
	 * Obtém o objeto NotaFiscal através de um XML em String
	 * 
	 * @param xml
	 * @return NotaFiscal
	 */
	private NotaFiscal fromXML(String xml) {
		return (NotaFiscal) getXStream().fromXML(xml);
	}
	
	/**
	 * Converte o objeto NotaFiscal em um XML para devolver no WS
	 * 
	 * @param nfe
	 * @return String (xml)
	 */
	private String toXML(NotaFiscal nfe) {
		return getXStream().toXML(nfe);
	}
	
	/**
	 * Cria uma instância do XStream e a retorna 
	 * 
	 * @return XStream
	 */
	private XStream getXStream() {
		return new XStream(new DomDriver());
	}
}
