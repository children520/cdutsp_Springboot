package com.xiaojun.cdutspspringboot.repository;

import com.xiaojun.cdutspspringboot.enity.CardMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardMessageRepository extends JpaRepository<CardMessage,String> {
    CardMessage findById(Integer id);
}
