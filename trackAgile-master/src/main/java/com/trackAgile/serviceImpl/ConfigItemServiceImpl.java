package com.trackAgile.serviceImpl;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trackAgile.Entity.Configurations;
import com.trackAgile.repository.ConfigurationRepository;
import com.trackAgile.service.ConfigItemService;
@Service
public class ConfigItemServiceImpl implements ConfigItemService {

	
	
	@Autowired
    private ConfigurationRepository configItemRepository;

     @Override
    public String getConfigValue(String key, String defaultValue) {
        Optional<Configurations> configItem = configItemRepository.findByConfigItem(key);
        
        if (configItem.isPresent()) {
            return configItem.get().getValue();
        } else {
            return defaultValue;
        }
    }
}