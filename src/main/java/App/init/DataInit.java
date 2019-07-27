package openlegacy.testservice.App.init;

import openlegacy.testservice.App.dao.ItemDAO;
import openlegacy.testservice.App.entity.Item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInit implements ApplicationRunner {
	private ItemDAO itemDAO;
	
	@Autowired
	public DataInit(ItemDAO itemDAO) {
		this.itemDAO = itemDAO;
	}
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		long count = itemDAO.count();
		
		if(count == 0) {
			Item i1 = new Item();
			i1.setNumber(1L);
			i1.setName("Ron");
			i1.setAmount(7);
			i1.setCode(12345L);
			
			Item i2 = new Item();
			i2.setNumber(2L);
			i2.setName("Ori");
			i2.setAmount(23);
			i2.setCode(98765L);
			
			itemDAO.save(i1);
			itemDAO.save(i2);
		}
	}
}