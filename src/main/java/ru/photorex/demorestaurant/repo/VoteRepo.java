package ru.photorex.demorestaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.photorex.demorestaurant.domain.Vote;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {
}
