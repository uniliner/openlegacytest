package openlegacy.testservice.App.dao;

import java.util.List;

import openlegacy.testservice.App.entity.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDAO extends CrudRepository<Item, Long> {
	public List<Item> findByNumber(Long number);
}