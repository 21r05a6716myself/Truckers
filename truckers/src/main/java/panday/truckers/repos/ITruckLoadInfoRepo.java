package panday.truckers.repos;


import org.springframework.data.jpa.repository.JpaRepository;
import panday.truckers.entity.TruckLoadInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITruckLoadInfoRepo extends JpaRepository<TruckLoadInfo, UUID>{
    List<TruckLoadInfo> findByShipperId(String shipperId);
}
