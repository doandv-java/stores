package doan.stores.persistenct;

import doan.stores.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse findWarehouseByProductIdIs(Long productId);
}
