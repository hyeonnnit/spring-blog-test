package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BoardNativeRepository {
    private final EntityManager em;

    public Board findById(int id){
        Query query = em.createNativeQuery("select * from board_tb where id =?", Board.class);
        query.setParameter(1,id);
        Board board = (Board) query.getSingleResult();
        return board;
    }
    public List<Board> findAll(){
        Query query = em.createNativeQuery("select * from board_tb order by id desc",Board.class);

        return query.getResultList();
    }
    @Transactional
    public void save(String title, String content, String username){
        Query query = em.createNativeQuery("insert into board_tb (title, content, username, created_at) values (?,?,?,now())");
        query.setParameter(1,title);
        query.setParameter(2,content);
        query.setParameter(3,username);
        query.executeUpdate();
    }
}
