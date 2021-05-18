package diary.service;

import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;
import diary.dto.algorithm.FileInfo;

import java.time.LocalDate;
import java.util.List;

public interface AlgorithmService {
    public Algorithm get(int id);
    public FileInfo getFileInfo(int id);
    public int getFileId(int algorithmId);
    public List<Integer> getPageNumbers(LocalDate startDate, LocalDate endDate);
    public List<AlgorithmCard> getCards(int pageNum, LocalDate startDate, LocalDate endDate);
    public int add(Algorithm algorithm);
    public int addFileInfo(int algorithmId, String fileName, String saveFileName, String contentType);
    public int update(Algorithm algorithm);
    public int updateFileInfo(int algorithmId, String fileName, String saveFileName, String contentType);
    public int delete(int id);
    public AlgorithmCard makeAlgorithmCard(Algorithm algorithm, int algorithmId);
}
