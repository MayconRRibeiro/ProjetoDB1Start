package br.com.db1.dao.impl.test;

import org.junit.Assert;
import org.junit.Test;

import br.com.db1.dao.impl.AvaliadorDao;
import br.com.db1.model.Avaliador;

public class CandidatoDaoTest {
	
	Avaliador avaliador = new Avaliador();
	AvaliadorDao avaliadorDao = new AvaliadorDao();
	Long id = (long) 1;
	
@Test
	public void saveTest() {
		Assert.assertEquals(avaliadorDao.save(avaliador), false);		
	}
	public void deleteTest() {
		Assert.assertEquals(avaliadorDao.delete(id), true);
	}
}
