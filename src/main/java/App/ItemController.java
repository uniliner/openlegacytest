package openlegacy.testservice.App;

import openlegacy.testservice.App.dao.ItemDAO;
import openlegacy.testservice.App.entity.Item;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping(path = "items", produces = MediaType.APPLICATION_JSON_VALUE)
public class ItemController {
	@Autowired
	private ItemDAO itemDAO;
	
	@GetMapping("/list")
	public Iterable<Item> getAll() {
        return itemDAO.findAll();
    }
	
	private Item getItemInternal(long number) {
		List<Item> found = itemDAO.findByNumber(number);
		if(found.size() == 1) {
			return found.get(0);
		}
		return null;
	}
	
	@GetMapping("/{number}")
	public ResponseEntity getItem(@PathVariable long number) {
        Item item = getItemInternal(number);
		if(item == null)
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		return new ResponseEntity(item, HttpStatus.OK);
    }
	
	@PatchMapping("/withdraw")
	public ResponseEntity withdraw(@RequestBody Item req) {
		Item found = getItemInternal(req.getNumber());
		if(found == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		if(found.getAmount() < req.getAmount()) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		}
		found.setAmount(found.getAmount() - req.getAmount());
		itemDAO.save(found);
		return new ResponseEntity(found, HttpStatus.OK);
	}
	
	@PatchMapping("/deposit")
	public ResponseEntity deposit(@RequestBody Item req) {
		Item found = getItemInternal(req.getNumber());
		if(found == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		found.setAmount(found.getAmount() + req.getAmount());
		itemDAO.save(found);
		return new ResponseEntity(found, HttpStatus.OK);
	}
	
	@PutMapping("")
	public ResponseEntity add(@RequestBody Item req) throws Exception {
		Item found = getItemInternal(req.getNumber());
		if(found == null) {
			itemDAO.save(req);
			return new ResponseEntity(req, HttpStatus.OK);
		}
		return new ResponseEntity(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{number}")
	public ResponseEntity delete(@PathVariable long number) throws Exception {
		Item found = getItemInternal(number);
		if(found == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		Item record = found;
		itemDAO.delete(found);
		return new ResponseEntity(record, HttpStatus.OK);
	}
}
