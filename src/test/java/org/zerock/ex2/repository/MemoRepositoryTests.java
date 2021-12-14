package org.zerock.ex2.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.zerock.ex2.entity.Memo;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.stream.IntStream;

@SpringBootTest
public class MemoRepositoryTests {
    @Autowired
    MemoRepository memoRepository;

    @Test
    public void testClass() {
        System.out.println(memoRepository.getClass().getName());
    }

    // 삽입
    @Test
    public void testInsertDummies() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("Sample..." + i).build();
            memoRepository.save(memo);
        });
    }

    // findById를 이용한 데이터 조회
    @Test
    public void testSelect() {
        Long mno = 100L;

        Optional<Memo> result = memoRepository.findById(mno);

        System.out.println("==================");
        if(result.isPresent()) {
            Memo memo = result.get();
            System.out.println(memo);
        }
    }

    // getById를 이용한 데이터 조회
    @Transactional
    @Test
    public void testSelect2() {
        Long mno = 100L;

        Memo memo = memoRepository.getById(mno);
        System.out.println("==================");
        System.out.println(memo);
    }

    // 수정
    @Test
    public void testUpdate() {
        Memo memo = Memo.builder().mno(98L).memoText("Update Text").build();
        memoRepository.save(memo);
    }
    
    // 삭제
    @Test
    public void testDelete() {
        Long mno = 99L;
        memoRepository.deleteById(mno); // 삭제하려는 데이터가 존재하지 않으면 Exception 발생
    }

    // Paging
    @Test
    public void testPageDefault(){
        //1~10개
        Pageable pageable = PageRequest.of(1, 10);
        Page<Memo> result = memoRepository.findAll(pageable);

        System.out.println(result);
        System.out.println("-----------------------------------");
        System.out.println("Total Pages: " + result.getTotalPages()); // 총 페이지수
        System.out.println("Total Count: " + result.getTotalElements()); // 총 레코드수
        System.out.println("Current Page: " + result.getNumber()); // 현재 페이지
        System.out.println("Page Size:"  + result.getSize()); // 페이지당 데이터 개수
        System.out.println("Has next pages:" + result.hasNext()); // 다음 페이지 존재 여부
        System.out.println("first Page? " + result.isFirst());

        System.out.println("-----------------------------------");
        for (Memo memo: result.getContent())
            System.out.println(memo);
    }
}
