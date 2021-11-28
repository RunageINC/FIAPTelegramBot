package com.fiap.telegrambot.service;

import lombok.Getter;
import lombok.Setter;

/**
*
* @author lugamvazori
*/

@Getter @Setter
public class WebServiceCep {

	private String resultado;
	private String resultado_txt;
	private String uf;
	private String cidade;
	private String bairro;
	private String tipo_logradouro;
	private String logradouro;

}
