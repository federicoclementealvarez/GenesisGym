package logic;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;

import dataAccess.DataFee;
import dataAccess.DataPlan;
import entities.Fee;
import entities.People;
import entities.Plan;

public class ManageFeeController {

	private DataFee df;
	private DataPlan dp;

	public ManageFeeController() {
		df = new DataFee();
		dp = new DataPlan();
	}

	public ArrayList<Fee> getUnpaidFees(People p) {
		return df.getUnpaidByPeople(p);
	}

	public void payFee(People p, LocalDate dueDate) {
		df.updatePayDate(p.getDni(), dueDate);
	}

	public void checkAndCreateCurrentMonthFee(People p) {
		LocalDate lastDayOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
		Fee existingFee = df.getOne(p.getDni(), lastDayOfMonth);

		if (existingFee == null) {
			Plan plan = dp.getOne(p.getPlan().getId());
			Fee newFee = new Fee();
			newFee.setClient(p);
			newFee.setDueDate(lastDayOfMonth);
			newFee.setAmount(plan.getRate());
			newFee.setPayDate(null);
			df.insertOne(newFee);
		}
	}
}
