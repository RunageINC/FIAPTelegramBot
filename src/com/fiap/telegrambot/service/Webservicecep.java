package com.fiap.telegrambot.service;

import javax.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lugamvazori
 */
@XmlRootElement
@Getter
@Setter
public class Webservicecep {

    private String resultado;
    private String resultado_txt;
    private String uf;
    private String cidade;
    private String bairro;
    private String tipo_logradouro;
    private String logradouro;

}
