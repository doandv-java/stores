package doan.stores.bussiness.implement;

import doan.stores.bussiness.AdvertiseService;
import doan.stores.bussiness.ImageService;
import doan.stores.domain.Advertise;
import doan.stores.dto.request.AdvertiseRequest;
import doan.stores.persistenct.AdvertiseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AdvertiseServiceImpl implements AdvertiseService {

    private final ImageService imageService;

    private final AdvertiseRepository advertiseRepository;

    public AdvertiseServiceImpl(ImageService imageService, AdvertiseRepository advertiseRepository) {
        this.imageService = imageService;
        this.advertiseRepository = advertiseRepository;
    }

    @Override
    public List<Advertise> findAllAdvertise() {
        return advertiseRepository.findAll();
    }

    @Override
    public void saveAdvertise(AdvertiseRequest request) {
        Advertise advertise = new Advertise();
        advertise.setId(request.getId());
        advertise.setContent(request.getContent());
        advertise.setActive(request.getActive());
        String imageLink = imageService.saveImage(request.getImage(), request.getImageLink());
        advertise.setImageLink(imageLink);
        advertiseRepository.save(advertise);
    }

    @Override
    public Advertise findAdvertiseById(Long id) {
        return advertiseRepository.getOne(id);
    }

    @Override
    public void deleteAdvertise(Long id) {
        Advertise advertise = advertiseRepository.getOne(id);
        if (advertise.getActive() == 1) {
            advertise.setActive(0);
            advertiseRepository.save(advertise);
        } else {
            advertiseRepository.delete(advertise);
        }
    }

    @Override
    public void changeActive(Long id, int active) {
        Advertise advertise = advertiseRepository.getOne(id);
        advertise.setActive(active);
        advertiseRepository.save(advertise);
    }
}
