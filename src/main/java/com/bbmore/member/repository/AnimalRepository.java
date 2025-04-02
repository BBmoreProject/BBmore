package com.bbmore.member.repository;

import com.bbmore.member.entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {


    // String 타입의 animalCode를 받을 수 있도록 수정
    List<Animal> findByAnimalCode(Integer animalCode);

    List<Animal> findAll(); // 모든 동물 목록 조회

    @Query("SELECT DISTINCT a.animalType FROM Animal a")
    List<String> findDistinctAnimalTypes();

    @Query("SELECT a.animalBreed FROM Animal a")
    List<String> findDistinctBreeds();

    Optional<Animal> findByAnimalTypeAndAnimalBreed(String animalType, String animalBreed);

    // 🟢 새로 추가한 메서드
    Optional<Animal> findByAnimalBreed(String animalBreed);
}

