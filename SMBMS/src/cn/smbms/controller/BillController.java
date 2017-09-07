package cn.smbms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Bill;
import cn.smbms.pojo.Provider;
import cn.smbms.pojo.User;
import cn.smbms.service.bill.BillService;
import cn.smbms.service.provider.ProviderService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/sys/bill")
public class BillController {
	private Logger logger = Logger.getLogger(LoginController.class);
	@Resource
	private BillService billService;
	@Resource
	private ProviderService providerService;
    @RequestMapping(value="/syserror.html")
	public String sysError(){
		return "syserror";
	}
	@RequestMapping(value="/list.html")
	public String getBillList(Model model,@RequestParam(value="queryProductName",required=false) String queryProductName,
			@RequestParam(value="queryProviderId",required=false) Integer queryProviderId,
			@RequestParam(value="queryIsPayment",required=false) Integer queryIsPayment,
			@RequestParam(value="pageIndex",required=false) String pageIndex){
		logger.info("getBillList ---- > queryProductName: " + queryProductName);
		logger.info("getBillList ---- > queryProviderId: " + queryProviderId);
		logger.info("getBillList ---- > pageIndex: " + pageIndex);
		List<Bill> billList = null;
		List<Provider> providerList = null;
		//设置页面容量
    	int pageSize = Constants.pageSize;
    	//当前页码
    	int currentPageNo = 1;
	
		if(queryProductName == null){
			queryProductName = "";
		}
/*		if(queryProviderId == 0){
			queryProviderId = null;
		}
		
		if(queryIsPayment==0){
			queryIsPayment = null;
		}*/
    	if(pageIndex != null){
    		try{
    			currentPageNo = Integer.valueOf(pageIndex);
    		}catch(NumberFormatException e){
    			return "redirect:/sys/bill/syserror.html";
    		}
    	}	
    	//总数量（表）	
    	int totalCount = 0;
		try {
			totalCount = billService.getBillCount(queryProductName, queryProviderId, queryIsPayment);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	//总页数
    	PageSupport pages=new PageSupport();
    	pages.setCurrentPageNo(currentPageNo);
    	pages.setPageSize(pageSize);
    	pages.setTotalCount(totalCount);
    	int totalPageCount = pages.getTotalPageCount();
    	//控制首页和尾页
    	if(currentPageNo < 1){
    		currentPageNo = 1;
    	}else if(currentPageNo > totalPageCount){
    		currentPageNo = totalPageCount;
    	}
    	Map<String, Object> map = new HashMap<String, Object>();
        map.put("productName",queryProductName);
        map.put("providerId",queryProviderId);
        map.put("isPayment", queryIsPayment);
        Integer from = (currentPageNo-1)*pageSize;
        map.put("from",from);
        map.put("pageSize",pageSize);
    	try {
			billList = billService.getBillList(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	providerList = providerService.getAllProviderList();
    	model.addAttribute("billList", billList);
    	model.addAttribute("providerList",providerList);
    	model.addAttribute("queryProductName", queryProductName);
		model.addAttribute("queryProviderId", queryProviderId);
		model.addAttribute("queryIsPayment",queryIsPayment);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "billlist";
	}
    
    @RequestMapping(value="/view/{id}",method=RequestMethod.GET)
	public String view(@PathVariable String id,Model model){
		logger.debug("view id===================== "+id);
		Bill bill = new Bill();
		try {
			bill = billService.getBillById(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute(bill);
		return "billview";
    }
    
    @RequestMapping(value="/del.json",method=RequestMethod.POST)
    @ResponseBody
    public Object delBillById(@RequestParam("billid") String id){
    	logger.info("deleteBillById billid ===================== "+id);
    	HashMap<String, String> resultMap = new HashMap<String,String>();
    	if(!StringUtils.isNullOrEmpty(id)){
    		boolean flag = billService.deleteBillById(Integer.parseInt(id));
    		if(flag){
    			resultMap.put("delResult", "true");
    		}else{
    			resultMap.put("delResult", "false");
    		}
    	}else{
    		resultMap.put("delResult", "notexit");
    	}
    	return JSONArray.toJSONString(resultMap);
    }
	@RequestMapping(value="/add.html",method=RequestMethod.GET)
	public String add(@ModelAttribute("bill") Bill bill){
		return "billadd";
	}
	@RequestMapping(value="/providerlist.json",method=RequestMethod.GET,produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public List<Provider> getProviderList(){
		List<Provider> providerList = null;
		try {
			providerList = providerService.getAllProviderList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		logger.debug("providerList size: " + providerList.size());
		return providerList;
	}
	@RequestMapping(value="/addsave.html",method=RequestMethod.POST)
	public String addBillSave(Bill bill,HttpSession session){
		User user = (User) session.getAttribute(Constants.USER_SESSION);
		bill.setCreatedBy(user.getId());
		bill.setCreationDate(new Date());
		try{
			if(billService.add(bill)){
				return "redirect:/sys/bill/list.html";
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "billadd";
	}
	@RequestMapping(value="/modify/{id}",method=RequestMethod.GET)
	public String getBillById(@PathVariable String id,Model model){
		logger.debug("getBillById id===================== "+id);
		Bill bill = billService.getBillById(Integer.parseInt(id));
		model.addAttribute(bill);
		return "billmodify";
	}
	@RequestMapping(value="/modifysave.html",method=RequestMethod.POST)
	public String modifyBillSave(Bill bill,HttpSession session){
		logger.debug("modifyBillSave id===================== "+bill.getId());
		User user = (User) session.getAttribute(Constants.USER_SESSION);
		bill.setModifyBy(user.getId());
		bill.setModifyDate(new Date());
		try {
			if(billService.modify(bill)){
				return "redirect:/sys/bill/list.html";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "billmodify";
	}
}
