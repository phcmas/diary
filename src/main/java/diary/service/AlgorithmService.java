package diary.service;

import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;

import java.time.LocalDate;
import java.util.List;

public interface AlgorithmService {
    public Algorithm get(int id);
    public List<Integer> getPageNumbers(LocalDate startDate, LocalDate endDate);
    public List<AlgorithmCard> getCards(int pageNum, LocalDate startDate, LocalDate endDate);
    public int add(Algorithm algorithm);
    public int update(Algorithm algorithm);
    public int delete(int id);
    public AlgorithmCard makeAlgorithmCard(Algorithm algorithm, int algorithmId);
    public String getAuthor(int id);
}
