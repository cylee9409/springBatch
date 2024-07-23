package tmoney.szr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChunkService {
	
	// 데이터 읽기 메서드
    public List<Object> readData(int offset, int limit) {
        // 데이터베이스에서 데이터를 Chunk 단위로 읽어오는 로직
        return new ArrayList<>(); // 실제 데이터 반환
    }

    // 데이터 처리 메서드
    public Object processData(Object item) {
        // 데이터 처리 로직
        return item;
    }

    // 데이터 쓰기 메서드
    @Transactional
    public void writeData(List<Object> items) {
        // 데이터베이스에 데이터를 쓰는 로직
    }
}
