package doan.stores.persistenct;

import doan.stores.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Order findOrderByStatusIsAndUserIdIs(int status, Long userId);

    Order findOrderById(Long id);

    List<Order> findOrdersByUserIdIs(Long userId);

//    @Query(name = "select * FROM orders WHERE to_char(orders.create_day,'yyyy')=:year and status=:status",nativeQuery = true)
//    List<Order> findOrdersByStatusIsAndYear( int status, String year);
//
//    @Query(name = "select * FROM orders WHERE to_char(orders.create_day,'MM')=:month and status=:status",nativeQuery = true)
//    List<Order> findOrdersByStatusIsAndMonth(int status, String month);
//
//    @Query(name = "select * FROM orders WHERE to_char(orders.create_day,'yyyy-MM-dd')=:day and status=:status",nativeQuery = true)
//    List<Order> findOrdersByStatusIsAndDay( int status, String day);

    List<Order> findOrdersByStatusIs(int status);
}
