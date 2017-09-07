package cn.smbms.service.provider;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.provider.ProviderMapper;
import cn.smbms.pojo.Provider;
@Service
public class ProviderServiceImpl implements ProviderService {
    @Resource
    private ProviderMapper providerMapper;
	@Override
	public List<Provider> getProviderList(Map<String, Object> map) {
		return providerMapper.getProviderList(map);
	}

	@Override
	public Integer getproviderCount(String proName, String proCode) {
		return providerMapper.getProviderCount(proName, proCode);
	}

	@Override
	public Provider getProviderById(Integer id) {
		return providerMapper.getProviderById(id);
	}

	@Override
	public boolean deleteProviderById(Integer id) {
		boolean flag = false;
		if(providerMapper.deleteProviderById(id)>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean add(Provider provider) {
		boolean flag = false;
		if(providerMapper.add(provider)>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public boolean modify(Provider provider) {
		boolean flag = false;
		if(providerMapper.modify(provider)>0){
			flag = true;
		}
		return flag;
	}

	@Override
	public List<Provider> getAllProviderList() {
		return providerMapper.getAllProviderList();
	}

}
