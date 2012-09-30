package com.nuvola.gxpenses.server.repos;

import com.nuvola.gxpenses.shared.domaine.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagRepos extends JpaRepository<Tag, Long> {
    @Query("select t.value from Tag t where t.user.id = ?1")
    List<String> findAllTagByUserId(Long userId);
}
