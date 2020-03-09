package doan.stores.bussiness.implement;

import doan.stores.bussiness.SupplyService;
import doan.stores.domain.Supply;
import doan.stores.dto.request.SupplyRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplyServiceImpl implements SupplyService {

    @Override
    public List<Supply> findSupplies() {
        return null;
    }

    @Override
    public void saveSupply(SupplyRequest request) {

    }

    @Override
    public void deleteSupply(Long id) {

    }

    @Override
    public boolean existsSupply(String name, String nameOld) {
        return false;
    }
}
