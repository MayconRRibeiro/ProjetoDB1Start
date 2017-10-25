package br.com.db1.controller.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.db1.controller.AutenticacaoBean;

public class AutenticacaoBeanTest {
	AutenticacaoBean autenticacao = new AutenticacaoBean();
	
	@Test
	public void testeAutenticacao() {
		autenticacao.setUsuario("admin");
		Assert.assertTrue(autenticacao.getUsuario() == AutenticacaoBean.getUsuarioCorreto());
		
		autenticacao.setSenha("admin");
		Assert.assertTrue(autenticacao.getSenha() == AutenticacaoBean.getSenhaCorreta());
		
		autenticacao.setUsuario("corinthians");
		Assert.assertFalse(autenticacao.getUsuario() == AutenticacaoBean.getUsuarioCorreto());
		
		autenticacao.setSenha("corinthians");
		Assert.assertFalse(autenticacao.getSenha() == AutenticacaoBean.getSenhaCorreta());
	}
	public void testeRegistrarSaida() {
		Assert.assertEquals(autenticacao.registraSaida(),"/redirect");
	}
}
