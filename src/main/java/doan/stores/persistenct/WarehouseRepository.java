package doan.stores.persistenct;

import doan.stores.domain.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse findWarehouseByProductIdIs(Long productId);
    @Query(value="select * from warehouse ORDER BY quantity ASC LIMIT 3",nativeQuery = true)
    List<Warehouse> top3Warehouse();


}
