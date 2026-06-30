package com.example.demo.repository;

import com.example.demo.model.Member;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name, String email);
    List<Member> findByNameAndEmail(String name, String email);
    List<Member> findByNameOrEmail(String name, String email);
    List<Member> findByNameContaining(String name);
    List<Member> findByNameLike(String name);
    List<Member> findByAgeGreaterThan(Integer age);
    List<Member> findByAgeGreaterThanEqual(Integer age);
    List<Member> findByAgeLessThan(Integer age);
    List<Member> findByAgeLessThanEqual(Integer age);
    List<Member> findAllByOrderByNameAsc();
    @Query("SELECT m Member m WHERE m.name = :name")
    List<Member> findMemberByName(@Param("name") String name);
    @Query("SELECT m Member m WHERE m.name = :name AND m.email = :email")
    List<Member> findMemberByNameEmail(@Param("name") String name, @Param("email") String email);
}
