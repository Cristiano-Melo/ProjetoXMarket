package Models;

import java.time.LocalDate;
import java.util.Date;

public class Pedido {
	String cod_pedido;
	LocalDate data_pedido;
	String clientes_cod_cliente;
	String condicao_pagamento_pedido;
	String tipo_pedido;
	
	
	public String getCod_pedido() {
		return cod_pedido;
	}
	public void setCod_pedido(String cod_pedido) {
		this.cod_pedido = cod_pedido;
	}
	public LocalDate getData_pedido() {
		return data_pedido;
	}
	public void setData_pedido(LocalDate data_pedido) {
		this.data_pedido = data_pedido;
	}
	public String getClientes_cod_cliente() {
		return clientes_cod_cliente;
	}
	public void setClientes_cod_cliente(String clientes_cod_cliente) {
		this.clientes_cod_cliente = clientes_cod_cliente;
	}
	public String getCondicao_pagamento_pedido() {
		return condicao_pagamento_pedido;
	}
	public void setCondicao_pagamento_pedido(String condicao_pagamento_pedido) {
		this.condicao_pagamento_pedido = condicao_pagamento_pedido;
	}
	public String getTipo_pedido() {
		return tipo_pedido;
	}
	public void setTipo_pedido(String tipo_pedido) {
		this.tipo_pedido = tipo_pedido;
	}
	public void setData_pedidoExatoMomento(LocalDate now) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
	
	
	