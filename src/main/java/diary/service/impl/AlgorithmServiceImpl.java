package diary.service.impl;

import diary.dao.algorithm.AlgorithmCardDao;
import diary.dao.algorithm.AlgorithmDao;
import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;
import diary.service.AlgorithmService;
import diary.utility.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlgorithmServiceImpl implements AlgorithmService {
    @Autowired
    AlgorithmDao algorithmDao;

    @Autowired
    AlgorithmCardDao algorithmCardDao;

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
    public int add(Algorithm algorithm) {
        int algorithmId = algorithmDao.add(algorithm);
        AlgorithmCard newCard = makeAlgorithmCard(algorithm, algorithmId);
        algorithmCardDao.add(newCard);

        return algorithmId;
    }

    @Override
    public int update(Algorithm algorithm) {
        int algorithmId = algorithm.getId();
        AlgorithmCard newCard = makeAlgorithmCard(algorithm, algorithmId);

        algorithmCardDao.update(newCard);
        return algorithmDao.update(algorithm);
    }

    @Override
    public int delete(int id) {
        algorithmCardDao.deleteByAlgorithmId(id);
        return algorithmDao.delete(id);
    }

}
