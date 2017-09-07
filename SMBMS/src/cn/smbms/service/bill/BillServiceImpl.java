package cn.smbms.service.bill;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.bill.BillMapper;
import cn.smbms.pojo.Bill;
@Service
public class BillServiceImpl implements BillService {
	@Resource
	private BillMapper billMapper;
	@Override
	public List<Bill> getBillList(Map<String, Object> map) {
		return billMapper.getBillList(map);
	}

	@Override
	public Integer getBillCount(String productName, Integer providerId,
			Integer isPayment) {
		return billMapper.getBillCount(productName, providerId, isPayment);
	}

	@Override
	public Bill getBillById(Integer id) {
		return billMapper.getBillById(id);
	}

	@Override
	public boolean deleteBillById(Integer delId) {
		boolean flag = false;
		if(billMapper.deleteBillById(delId)>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean add(Bill bill) {
		boolean flag = false;
		if(billMapper.add(bill)>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modify(Bill bill) {
		boolean flag = false;
		if(billMapper.modify(bill)>0){
			flag = true;
		}
		return flag;
	}

}
