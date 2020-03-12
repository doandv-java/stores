package doan.stores.persistenct;

import doan.stores.domain.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplyRepository extends JpaRepository<Supply, Long> {
    Supply findSupplyByNameIsAndDeletedIs(String name, int deleted);

    List<Supply> findSuppliesByDeletedIs(int deleted);
}
