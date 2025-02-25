package com.yodimdasiz.yodimdasiz.repository;

import com.yodimdasiz.yodimdasiz.model.MemorialPages;
import com.yodimdasiz.yodimdasiz.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemorialPagesRepository extends JpaRepository<MemorialPages, Integer> {


}
