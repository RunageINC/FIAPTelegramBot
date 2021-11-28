/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.telegrambot.cep;


import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author lugamvazori
 */
public class CepUtils {

    private static final String BREAK_ROW = "\r\n";

    /**
     * WebService de busca CEP.
     *
     * @param cep
     * @return
     * @throws JAXBException
     * @throws MalformedURLException
     */
    public static Webservicecep getEndereco(String cep) throws JAXBException, MalformedURLException {
        JAXBContext jc = JAXBContext.newInstance(Webservicecep.class);
        Unmarshaller u = jc.createUnmarshaller();
        URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
        Webservicecep wCep = (Webservicecep) u.unmarshal(url);
        return wCep;
    }

    /**
     * Preencher um StringBuilder com retorno do webservice do CEP informado.
     *
     * @param result
     * @return
     */
    public static StringBuilder fillCEP(Webservicecep result) {
        StringBuilder sb = new StringBuilder();
        if (result != null) {
            sb.append("Estado: ").append(result.getUf());
            sb.append(BREAK_ROW);
            sb.append("Cidade: ").append(result.getCidade());
            sb.append(BREAK_ROW);
            sb.append("Bairro: ").append(result.getBairro());
            sb.append(BREAK_ROW);
            sb.append("Logradouro: ").append(result.getTipo_logradouro()).append(" ").append(result.getLogradouro());
        }
        return sb;
    }

}
