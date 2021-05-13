package diary.service;

import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;

import java.util.Date;
import java.util.List;

public interface AlgorithmService {
    public Algorithm get(int id);
    public List<AlgorithmCard> getCards(int pageNum, Date startDate, Date endDate);
    public int add(Algorithm algorithm);
    public int update(Algorithm algorithm);
    public int delete(int id);
    public AlgorithmCard makeAlgorithmCard(Algorithm algorithm, int algorithmId);
}
