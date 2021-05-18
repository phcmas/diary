package diary.service.impl;

import diary.dao.algorithm.AlgorithmCardDao;
import diary.dao.algorithm.AlgorithmDao;
import diary.dao.algorithm.FileInfoDao;
import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;
import diary.dto.algorithm.FileInfo;
import diary.service.AlgorithmService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    @Autowired
    AlgorithmDao algorithmDao;

    @Autowired
    AlgorithmCardDao algorithmCardDao;

    @Autowired
    FileInfoDao fileInfoDao;

    final int CARD_LIMIT = 4;

    @Override
    public AlgorithmCard makeAlgorithmCard(Algorithm algorithm, int algorithmId) {
        AlgorithmCard newCard = AlgorithmCard.builder()
                .algorithmId(algorithmId).solvedDate(algorithm.getSolvedDate())
                .type(algorithm.getType()).difficulty(algorithm.getDifficulty())
                .build();

        String shortTitle = Utility.cutString(algorithm.getTitle(), 10);
        String shortExplanation = Utility.cutString(algorithm.getExplanation(), 50);

        newCard.setShortTitle(shortTitle);
        newCard.setShortExplanation(shortExplanation);

        return newCard;
    }

    @Override
    public Algorithm get(int id) {
        return algorithmDao.get(id);
    }

    @Override
    public FileInfo getFileInfo(int id) {
        return fileInfoDao.get(id);
    }

    @Override
    public int getFileId(int algorithmId) {
        return fileInfoDao.getByAlgorithmId(algorithmId).getId();
    }

    @Override
    public List<AlgorithmCard> getCards(int pageNum, LocalDate startDate, LocalDate endDate) {
        int start = CARD_LIMIT * (pageNum-1);
        return algorithmCardDao.getList(start, CARD_LIMIT, startDate, endDate);
    }

    public List<Integer> getPageNumbers(LocalDate startDate, LocalDate endDate) {
        int count = algorithmDao.getCount(startDate, endDate);
        return Utility.getPageList(count, CARD_LIMIT);
    }

    @Override
    public int add(Algorithm algorithm) {
        int newId = algorithmDao.add(algorithm);
        AlgorithmCard newCard = makeAlgorithmCard(algorithm, newId);
        algorithmCardDao.add(newCard);

        return newId;
    }

    @Override
    public int addFileInfo(int algorithmId, String fileName, String saveFileName, String contentType) {
        FileInfo fileInfo = FileInfo.builder().algorithmId(algorithmId)
                .fileName(fileName).saveFileName(saveFileName)
                .contentType(contentType).build();

        return fileInfoDao.add(fileInfo);
    }

    @Override
    public int update(Algorithm algorithm) {
        int algorithmId = algorithm.getId();
        int cardId = algorithmCardDao.getByAlgorithmId(algorithmId).getId();

        AlgorithmCard newCard = makeAlgorithmCard(algorithm, algorithmId);
        newCard.setId(cardId);

        algorithmCardDao.update(newCard);
        return algorithmDao.update(algorithm);
    }

    @Override
    public int updateFileInfo(int algorithmId, String fileName, String saveFileName, String contentType) {
        int fileId = fileInfoDao.getByAlgorithmId(algorithmId).getId();
        FileInfo fileInfo = FileInfo.builder().id(fileId)
                .algorithmId(algorithmId).fileName(fileName)
                .saveFileName(saveFileName).contentType(contentType).build();

        return fileInfoDao.update(fileInfo);
    }

    @Override
    public int delete(int id) {
        fileInfoDao.deleteByAlgorithmId(id);
        algorithmCardDao.deleteByAlgorithmId(id);
        return algorithmDao.delete(id);
    }

}
