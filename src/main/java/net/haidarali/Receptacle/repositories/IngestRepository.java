package net.haidarali.Receptacle.repositories;

import net.haidarali.Receptacle.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngestRepository extends JpaRepository<Post, String> {
}
