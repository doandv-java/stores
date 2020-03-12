package doan.stores.bussiness;

import doan.stores.domain.Supply;
import doan.stores.dto.request.SupplyRequest;

import java.util.List;

public interface SupplyService {

    List<Supply> findSuppliesByDeleted(int deleted);

    void saveSupply(SupplyRequest request);

    void deleteSupply(Long id);

    boolean existsSupply(String name, String nameOld);

    Supply findSupplyById(Long id);

    void changeActive(Long id, int active);
}
