package cn.smbms.dao.provider;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;

public interface ProviderMapper {
	public List<Provider> getProviderList(Map<String, Object> map);
	
	public Integer getProviderCount(@Param("proName")String proName,@Param("proCode")String proCode);
	
	public Provider getProviderById(@Param("id")Integer id);
	
	public int deleteProviderById(@Param("id")Integer delId);
	
	public int add(Provider provider);
	
	public int modify(Provider provider);
	
	public List<Provider> getAllProviderList();
}
