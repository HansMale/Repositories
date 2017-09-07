package cn.smbms.service.provider;

import java.util.List;
import java.util.Map;

import cn.smbms.pojo.Provider;

public interface ProviderService {
	 /**
     * 供应商列表
     * @param map
     * @return
     */
    public List<Provider> getProviderList(Map<String, Object> map);

    /**
     * 供应商表记录数
     * @param proName
     * @param proCode
     * @return
     */
    public Integer getproviderCount(String proName,String proCode);

    /**
     * 通过proId获取Provider
     * @param id
     * @return
     */
    public Provider getProviderById(Integer id);
    /**
     * 通过proId删除Provider
     * @param id
     * @return
     */
    public boolean deleteProviderById(Integer id);
    /**
     * 添加新的供应商
     * @param provider
     * @return
     */
    public boolean add(Provider provider);
    /**
     * 修改供应商信息
     * @param provider
     * @return
     */
    public boolean modify(Provider provider);
    
    public List<Provider> getAllProviderList();
}
