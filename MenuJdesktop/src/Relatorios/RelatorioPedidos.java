package Relatorios;

import java.io.InputStream;
import java.util.List;

import Models.Pedido;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

public class RelatorioPedidos {
	
	//Criando os métodos para vincular o nosso relatório as variáveis	
		public void gerarRelatorio(List<Pedido> lista) throws JRException{
			
			
			//Fazendo a leitura das variáveis do relatório
			InputStream arq = RelatorioCliente.class.getResourceAsStream("/RelatoriosJasper/MyReports/RelatorioPedidos.jrxml");
			
			//Compilando o nosso relatório com as variáveis 
			JasperReport report = JasperCompileManager.compileReport(arq);
			
			//Criando a escrita do nosso arquivo no relatório
			JasperPrint print = JasperFillManager.fillReport(report, null, new JRBeanCollectionDataSource(lista));
			
			//Exibindo o nosso arquivo
			JasperViewer.viewReport(print,false);		
		}
}
