package doan.stores.bussiness;

import doan.stores.domain.Advertise;
import doan.stores.dto.request.AdvertiseRequest;

import java.util.List;

public interface AdvertiseService {
    List<Advertise> findAllAdvertise();

    void saveAdvertise(AdvertiseRequest request);

    Advertise findAdvertiseById(Long id);

    void deleteAdvertise(Long id);

    void changeActive(Long id, int active);

}
