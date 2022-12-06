package com.bucwith.repository.bucket;


import com.bucwith.domain.bucket.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BucketRepository extends JpaRepository<Bucket, Integer>, JpaSpecificationExecutor<Bucket> {
    List<Bucket> findByUserId(int userId);
}

