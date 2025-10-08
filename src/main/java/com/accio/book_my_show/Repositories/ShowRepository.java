package com.accio.book_my_show.Repositories;

import com.accio.book_my_show.Models.Movie;
import com.accio.book_my_show.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface ShowRepository extends JpaRepository<Show,Integer> {

}
