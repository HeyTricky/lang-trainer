package com.dsr.javaschool.langtrainer.repository;

import com.dsr.javaschool.langtrainer.entity.Training;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainingRepository extends CrudRepository<Training, Long> {

    List<Training> findAllByOrderByIdDesc();

    Training findById(Long id);

}
