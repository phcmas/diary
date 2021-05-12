package diary.service;

import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;

public interface AlgorithmService {
    public Algorithm get(int id);
    public int add(Algorithm algorithm);
    public int update(Algorithm algorithm);
    public int delete(int id);
    public AlgorithmCard makeAlgorithmCard(Algorithm algorithm, int algorithmId);
}
