package com.example.CricketApplication.repositories;

import com.example.CricketApplication.entities.Team;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends ElasticsearchRepository<Team, String> {
}