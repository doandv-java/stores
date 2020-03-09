package doan.stores.bussiness.implement;

import doan.stores.bussiness.SupplyService;
import doan.stores.domain.Supply;
import doan.stores.dto.request.SupplyRequest;
import doan.stores.persistenct.SupplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Autowired
    private SupplyRepository supplyRepository;

    @Override
    public List<Supply> findSupplies() {
        return supplyRepository.findAll();
    }

    @Override
    public void saveSupply(SupplyRequest request) {
        Supply supply = new Supply();
        supply.setId(request.getId());
        supply.setName(request.getName());
        supply.setDescription(request.getDescription());
        supply.setPhone(request.getPhone());
        supply.setEmail(request.getEmail());
        supply.setAddress(request.getAddress());
        supply.setActive(request.getActive());
        supplyRepository.save(supply);
    }

    @Override
    public void deleteSupply(Long id) {

    }

    @Override
    public boolean existsSupply(String name, String nameOld) {
        return false;
    }
}
