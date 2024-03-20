package shop.mtcoding.blog.board;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;

@DataJpaTest
@Import(BoardNativeRepository.class)
public class BoardNativeRepositoryTest {

    @Autowired
    private BoardNativeRepository boardNativeRepository;

    @Test
    public void updateById_test(){
        int id = 1;
        String title = "제목5";
        String content = "내용5";
        String username = "bori";

        boardNativeRepository.update(title,content,username,id);

        Board board = boardNativeRepository.findById(id);
        System.out.println("updateById_test/username: "+ board.getUsername());
        Assertions.assertThat(board.getUsername()).isEqualTo("bori");

    }
    @Test
    public void findById_test(){
        int id = 1;

        Board board = boardNativeRepository.findById(id);
        System.out.println("findById_test: "+board);
        System.out.println("findById_test/username: "+board.getUsername());

        Assertions.assertThat(board.getUsername()).isEqualTo("ssar");
    }
    @Test
    public void findAll_test(){
        List<Board> boardList = boardNativeRepository.findAll();
        System.out.println("findAll_test: "+boardList);
        System.out.println("findAll_test/size: "+boardList.size());
        System.out.println("findAll_test/username: "+boardList.get(1).getUsername());

        Assertions.assertThat(boardList.size()).isEqualTo(4);
        Assertions.assertThat(boardList.get(1).getUsername()).isEqualTo("cos");
    }

}
