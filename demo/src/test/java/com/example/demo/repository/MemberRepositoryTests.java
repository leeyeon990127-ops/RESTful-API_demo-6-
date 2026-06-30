package com.example.demo.repository;

import com.example.demo.model.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class MemberRepositoryTests {

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    public void doBeforeEach() {
        memberRepository.save(Member.builder().name("윤서준").email("SeojunYoon@hanbit.co.kr").age(10).enabled(true).build());
        memberRepository.save(Member.builder().name("윤광철").email("KwangcheolYoon@hanbit.co.kr").age(43).enabled(true).build());
        memberRepository.save(Member.builder().name("공미영").email("MiyeongKong@hanbit.co.kr").age(26).enabled(false).build());
        memberRepository.save(Member.builder().name("김도윤").email("DoyunKim@hanbit.co.kr").age(10).enabled(true).build());
    }

    @AfterEach
    public void doAfterEach() {
        memberRepository.deleteAll();
    }

    @Test
    public void testUserCase1() {
        assertThat(memberRepository.count()).isEqualTo(4);
        assertThat(memberRepository.findByName("윤서준").size()).isEqualTo(1);
        assertThat(memberRepository.findByNameAndEmail("윤서준", "SeojunYoon@hanbit.co.kr").size()).isEqualTo(1);
        assertThat(memberRepository.findByNameOrEmail("윤서준", "KwangcheolYoon@hanbit.co.kr").size()).isEqualTo(2);
        assertThat(memberRepository.findByNameContaining("윤").size()).isEqualTo(3);
        assertThat(memberRepository.findByNameLike("%영").size()).isEqualTo(1);
        assertThat(memberRepository.findByAgeGreaterThan(26).size()).isEqualTo(1);
        assertThat(memberRepository.findByAgeGreaterThanEqual(26).size()).isEqualTo(2);
        assertThat(memberRepository.findByAgeLessThan(26).size()).isEqualTo(2);
        assertThat(memberRepository.findByAgeLessThanEqual(26).size()).isEqualTo(3);
    }

    @Test
    public void testUserCase2() {
        assertThat(memberRepository.findAllByOrderByNameAsc().size()).isEqualTo(4);
        assertThat(memberRepository.findAllByOrderByNameAsc().get(0).getName()).isEqualTo("공미영");
    }

    @Test
    public void testUserCase3() {
        assertThat(memberRepository.findMemberByName("윤서준").size()).isEqualTo(1);
        assertThat(memberRepository.findMemberByNameEmail("윤서준", "SeojunYoon@hanbit.co.kr").size()).isEqualTo(1);
    }
}