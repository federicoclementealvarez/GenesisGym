package entities;
import java.time.LocalDate;

public class Fee {

	private People client;
	private LocalDate dueDate;
	private Double amount;
	private LocalDate payDate=null;
	
	
	public People getClient() {
		return client;
	}
	public void setClient(People client) {
		this.client = client;
	}
	public LocalDate getDueDate() {
		return dueDate;
	}
	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public LocalDate getPayDate() {
		return payDate;
	}
	public void setPayDate(LocalDate payDate) {
		this.payDate = payDate;
	}
	
	
	
}
