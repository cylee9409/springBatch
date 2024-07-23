package tmoney.szr.step.tasklet;

import java.util.List;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tmoney.szr.service.ChunkService;

@Component
@StepScope
@Slf4j
@RequiredArgsConstructor
public class ChunkTasklet implements Tasklet {
	
	private static final int CHUNK_SIZE = 10; // Chunk 크기
	
	private ChunkService chunkService;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		int offset = 0;

        while (true) {
            List<Object> items = chunkService.readData(offset, CHUNK_SIZE);

            if (items.isEmpty()) {
                break; // 더 이상 읽을 데이터가 없으면 종료
            }

            // 데이터 처리
            for (int i = 0; i < items.size(); i++) {
                items.set(i, chunkService.processData(items.get(i)));
            }

            // 데이터 쓰기
            chunkService.writeData(items);

            offset += CHUNK_SIZE;
        }

        return RepeatStatus.FINISHED;
	}	
}
