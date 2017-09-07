package cn.smbms.service.bill;

import java.util.List;
import java.util.Map;


import cn.smbms.pojo.Bill;

public interface BillService {
	public List<Bill> getBillList(Map<String, Object> map);
	
	public Integer getBillCount(String productName,Integer providerId,Integer isPayment);
	
	public Bill getBillById(Integer id);
	
	public boolean deleteBillById(Integer delId);
	
	public boolean add(Bill bill);
	
	public boolean modify(Bill bill);
}
