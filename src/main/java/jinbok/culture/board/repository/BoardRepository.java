package jinbok.culture.board.repository;

import jinbok.culture.board.domain.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Page<Board> findAllByOrderByIdDesc(Pageable pageable);

    Page<Board> findBoardsByTitleContaining(String title, Pageable pageable);

    @Query("select b from Board b where b.user.username = :username")
    Page<Board> findBoardsByUsername(String username, Pageable pageable);
}
