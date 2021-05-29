package diary.service.impl;

import diary.dao.algorithm.AlgorithmCardDao;
import diary.dao.algorithm.AlgorithmDao;
import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;
import diary.service.AlgorithmService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    @Autowired
    AlgorithmDao algorithmDao;

    @Autowired
    AlgorithmCardDao algorithmCardDao;

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
    public String getAuthor(int id) {
        return algorithmDao.getAuthor(id);
    }

    @Override
    public Algorithm get(int id) {
        return algorithmDao.get(id);
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
    @Transactional
    public int add(Algorithm algorithm) {
        algorithm.setAuthor(Utility.getCurrentUserName());
        int newId = algorithmDao.add(algorithm);
        AlgorithmCard newCard = makeAlgorithmCard(algorithm, newId);
        algorithmCardDao.add(newCard);

        return newId;
    }

    @Override
    @Transactional
    public int update(Algorithm algorithm) {
        int algorithmId = algorithm.getId();
        int cardId = algorithmCardDao.getByAlgorithmId(algorithmId).getId();

        AlgorithmCard newCard = makeAlgorithmCard(algorithm, algorithmId);
        newCard.setId(cardId);

        algorithmCardDao.update(newCard);
        return algorithmDao.update(algorithm);
    }

    @Override
    @Transactional
    public int delete(int id) {
        algorithmCardDao.deleteByAlgorithmId(id);
        return algorithmDao.delete(id);
    }

}
