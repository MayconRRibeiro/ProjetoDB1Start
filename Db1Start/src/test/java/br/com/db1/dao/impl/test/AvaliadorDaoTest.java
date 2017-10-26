package br.com.db1.dao.impl.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.db1.dao.impl.AvaliadorDao;
import br.com.db1.model.Avaliador;

public class AvaliadorDaoTest {
	
	Avaliador avaliador = new Avaliador();
	AvaliadorDao avaliadorDao = new AvaliadorDao();	
	
	public void saveTest() {
		avaliador.setId(5L);
		avaliador.setNome("Fusca");
		avaliador.setEmail("Fusca@hotmail.com");
		Assert.assertEquals(avaliadorDao.save(avaliador), true);		
	}
	public void deleteTest() {
		avaliador.setId(5L);
		avaliador.setNome("Fusca");
		avaliador.setEmail("Fusca@hotmail.com");
		Assert.assertEquals(avaliadorDao.delete(1L), false);
	}
}
