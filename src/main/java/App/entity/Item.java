package openlegacy.testservice.App.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ITEM")
public class Item {
	
	@Id
	@GeneratedValue
	@Column(name="number", nullable=false)
	private Long number;
	
	@Column(name="name", nullable=false)
    private String name;
	
	@Column(name="amount", nullable=false)
    private int amount;

	@Column(name="code", nullable=false)
    private Long code;

    public Long getNumber() {
        return number;
    }
	public void setNumber(Long number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
}