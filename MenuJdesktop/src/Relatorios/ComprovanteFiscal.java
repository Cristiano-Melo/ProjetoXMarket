package Relatorios;

import java.io.InputStream;
import java.util.List;

import Models.ListaPedido;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class ComprovanteFiscal {
	
	public void gerarRelatorio(List<ListaPedido> lista) throws JRException{
	
		InputStream arq = RelatorioCliente.class.getResourceAsStream("/RelatoriosJasper/MyReports/ComprovanteFiscal.jrxml");
		 
		JasperReport report = JasperCompileManager.compileReport(arq);
		
		JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
		
		JasperViewer.viewReport(print,false);		
				
	}
}
