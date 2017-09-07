package cn.smbms.dao.bill;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Bill;

public interface BillMapper {
	public List<Bill> getBillList(Map<String, Object> map);
	
	public Integer getBillCount(@Param("productName")String productName,@Param("providerId")Integer providerId,@Param("isPayment")Integer isPayment);
	
	public Bill getBillById(@Param("id")Integer id);
	
	public int deleteBillById(@Param("id")Integer delId);
	
	public int add(Bill bill);
	
	public int modify(Bill bill);
	
}
