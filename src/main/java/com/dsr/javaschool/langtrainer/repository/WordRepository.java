package com.dsr.javaschool.langtrainer.repository;


import com.dsr.javaschool.langtrainer.entity.Training;
import com.dsr.javaschool.langtrainer.entity.Word;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {

    List<Word> findAllByOrderByEnglishAsc();

    Word findByEnglish(String en);

    Word findByRussian(String rus);

    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN 'true' ELSE 'false' END FROM Word w WHERE w.english = ?1")
    boolean existsByEnglish(String eng);

    @Query("SELECT CASE WHEN COUNT(w) > 0 THEN 'true' ELSE 'false' END FROM Word w WHERE w.russian = ?1")
    boolean existsByRussian(String rus);

    @Query(nativeQuery = true, value = "SELECT english FROM word")
    List<String> findAllEnglishWord();

    @Query(nativeQuery = true, value = "SELECT russian FROM word")
    List<String> findAllRussianWord();

    @Query(nativeQuery = true, value = "SELECT * FROM word ORDER BY CASE count_all WHEN 0 THEN 0 ELSE 1.0*count_right/count_all END")
    List<Word> findAllByPriority();

    @Query(nativeQuery = true, value = "SELECT * FROM word ORDER BY CASE count_all WHEN 0 THEN 0 ELSE 1.0*count_right/count_all END limit ?1")
    List<Word> findNByPriority(int n);

    @Query("SELECT w FROM Word w WHERE w in (SELECT wt.word FROM TrainingWord wt WHERE wt.training=?1)")
    List<Word> findAllByTraining(Training training);

    @Query("SELECT w FROM Word w WHERE w in (SELECT wt.word FROM TrainingWord wt WHERE wt.training=?1 AND wt.isRight=false )")
    List<Word> findAllWrongByTraining(Training training);

    @Query(nativeQuery = true, value = "SELECT * FROM Word WHERE id!=?1 ORDER BY RAND() limit 3")
    List<Word> findWrongOptions(Long id);


}
