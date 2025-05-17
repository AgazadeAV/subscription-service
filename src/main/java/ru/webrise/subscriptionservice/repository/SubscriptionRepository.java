package ru.webrise.subscriptionservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.webrise.subscriptionservice.dto.projection.TopServiceProjection;
import ru.webrise.subscriptionservice.model.Subscription;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, UUID> {
    List<Subscription> findAllByUserId(UUID userId);

    @Query(value = """
                SELECT service_name AS serviceName, COUNT(*) AS count
                FROM subscriptions
                GROUP BY service_name
                ORDER BY count DESC
                LIMIT 3
            """, nativeQuery = true)
    List<TopServiceProjection> findTopThreeServices();

    boolean existsByUserIdAndServiceName(UUID userId, String serviceName);
}
