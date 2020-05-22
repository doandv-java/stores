package doan.stores.bussiness.implement;

import doan.stores.bussiness.SupplyService;
import doan.stores.domain.Supply;
import doan.stores.dto.request.SupplyRequest;
import doan.stores.persistenct.SupplyRepository;
import doan.stores.utils.Constants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyRepository;

    @Override
    public List<Supply> findSuppliesByDeleted(int deleted) {
        return supplyRepository.findSuppliesByDeletedIs(deleted);
    }

    @Override
    public List<Supply> findSuppliesByDeletedAndActive(int deleted, int active) {
        return supplyRepository.findSuppliesByDeletedIsAndActiveIs(deleted, active);
    }

    @Override
    public void saveSupply(SupplyRequest request) {
        Supply supply = new Supply();
        supply.setId(request.getId());
        supply.setName(StringUtils.trimToEmpty(request.getName()).toUpperCase());
        supply.setDetail(request.getDetail());
        supply.setPhone(request.getPhone());
        supply.setEmail(request.getEmail());
        supply.setAddress(request.getAddress());
        supply.setActive(request.getActive());
        supply.setDeleted(request.getDeleted());
        supplyRepository.save(supply);
    }

    @Override
    public void deleteSupply(Long id) {
        Supply supply = supplyRepository.getOne(id);
        supply.setDeleted(Constants.DELETE.TRUE);
        supplyRepository.save(supply);
    }

    @Override
    public Supply findSupplyById(Long id) {
        return supplyRepository.getOne(id);
    }


    @Override
    public void changeActive(Long id, int active) {
        Supply supply = supplyRepository.getOne(id);
        supply.setActive(active);
        supplyRepository.save(supply);
    }

    @Override
    public boolean existsSupply(String name, String nameOld) {
        if (!StringUtils.isEmpty(name)) {
            Supply flag = supplyRepository.findSupplyByNameIsAndDeletedIs(name, Constants.DELETE.FALSE);
            if (StringUtils.isEmpty(nameOld)) {
                return flag != null;
            } else {
                boolean check = name.equals(nameOld) || flag == null;
                return !check;
            }
        }
        return false;
    }

    @Override
    public List<Long> getSupplyIdsByName(String name) {
        name = StringUtils.capitalize(StringUtils.trimToEmpty(name).toUpperCase());
        List<Supply> supplies = supplyRepository.findSuppliesByNameContainingAndDeletedIs(name, Constants.DELETE.FALSE);
        if (supplies.isEmpty()) {
            return null;
        } else {
            ArrayList<Long> ids = new ArrayList<>();
            for (Supply supply : supplies) {
                ids.add(supply.getId());
            }
            return ids;
        }
    }
}
