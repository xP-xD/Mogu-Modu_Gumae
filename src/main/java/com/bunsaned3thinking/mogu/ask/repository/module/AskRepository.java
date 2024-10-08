package com.bunsaned3thinking.mogu.ask.repository.module;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bunsaned3thinking.mogu.ask.entity.Ask;

public interface AskRepository extends JpaRepository<Ask, Long> {
	boolean existsByUserUidAndPostId(Long uid, Long postId);

	List<Ask> findByUserUid(Long uid);

	List<Ask> findByPostId(Long postId);

	Optional<Ask> findByUserUidAndPostId(Long uid, Long postId);
}

