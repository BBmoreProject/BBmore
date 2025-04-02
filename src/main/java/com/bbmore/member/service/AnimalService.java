package com.bbmore.member.service;

import com.bbmore.member.entity.Animal;
import com.bbmore.member.repository.AnimalRepository;
import com.bbmore.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j // 로그를 찍기 위해 추가
public class AnimalService {

    private final AnimalRepository animalRepository;
    private final MemberRepository memberRepository;

    public List<Animal> getAllAnimal() {
        return animalRepository.findAll(); // 전체 동물 목록 조회
    }

    public List<String> getAllAnimalTypes() {
        return animalRepository.findDistinctAnimalTypes();
    }

    public List<String> getAllAnimalBreeds() {
        return animalRepository.findDistinctBreeds();
    }




}

