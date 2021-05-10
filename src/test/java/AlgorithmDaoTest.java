import diary.config.ApplicationConfig;
import diary.config.SecurityConfig;
import diary.dao.algorithm.AlgorithmCardDao;
import diary.dao.algorithm.AlgorithmDao;
import diary.dao.algorithm.FileInfoDao;
import diary.dto.algorithm.Algorithm;
import diary.dto.algorithm.AlgorithmCard;
import diary.dto.algorithm.FileInfo;
import diary.dto.enums.AlgorithmType;
import diary.dto.enums.Difficulty;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ContextConfiguration(classes = {ApplicationConfig.class, SecurityConfig.class})
public class AlgorithmDaoTest {
    @Autowired
    DataSource dataSource;

    @Autowired
    AlgorithmDao algorithmDao;

    @Autowired
    AlgorithmCardDao algorithmCardDao;

    @Autowired
    FileInfoDao fileInfoDao;

    public int getAlgorithmId() {
        List<Algorithm> recentAlgorithms = algorithmDao.getRecentAlgorithms(0,1);
        return recentAlgorithms.get(0).getId();
    }

    @Test
    public void DaoTest0_AlgorithmTest() {
        Algorithm newAlgorithm = Algorithm.builder().id(-1)
                .title("test").solvedDate(LocalDateTime.now())
                .language("java").type(AlgorithmType.DP)
                .source("LeetCode").difficulty(Difficulty.MEDIDUM)
                .explanation("test").content("test")
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .fileId(1).build();
        int newId = algorithmDao.add(newAlgorithm);
        Assert.assertTrue(newId >= 0);

        Algorithm algorithm = algorithmDao.get(newId);
        Assert.assertNotNull(algorithm);
    }

    @Test
    public void DaoTest1_AlgorithmUpdate() {
        int algorithmId = getAlgorithmId();

        Algorithm newOne = Algorithm.builder().id(algorithmId)
                .title("test").solvedDate(LocalDateTime.now())
                .language("java").type(AlgorithmType.UNION_FIND)
                .source("HackerRank").difficulty(Difficulty.MEDIDUM)
                .explanation("test").content("test")
                .createDate(LocalDateTime.now())
                .modifyDate(LocalDateTime.now())
                .fileId(1).build();

        int count = algorithmDao.update(newOne);
        Assert.assertTrue(count > 0);
    }

    @Test
    public void DaoTest2_AlgorithmCardTest() {
        int algorithmId = getAlgorithmId();

        AlgorithmCard newCard = AlgorithmCard.builder().id(-1)
                .algorithmId(algorithmId).shortTitle("test")
                .shortExplanation("test").type(AlgorithmType.DP)
                .difficulty(Difficulty.MEDIDUM).solvedDate(LocalDateTime.now())
                .build();

        int newId = algorithmCardDao.add(newCard);
        Assert.assertTrue(newId >= 0);

        List<AlgorithmCard> algorithmCards = algorithmCardDao.getByAlgorithmId(algorithmId);
        Assert.assertNotEquals(algorithmCards.size(), 0);
    }

    @Test
    public void DaoTest3_AlorithmCardUpdate() {
        int algorithmId = getAlgorithmId();

        AlgorithmCard updatedCard = AlgorithmCard.builder().id(1)
                .algorithmId(algorithmId).shortTitle("test_updated")
                .shortExplanation("test_updated").type(AlgorithmType.GREEDY)
                .difficulty(Difficulty.EASY).solvedDate(LocalDateTime.now())
                .build();

        int count = algorithmCardDao.update(updatedCard);
        Assert.assertTrue(count >= 0);
    }

    @Test
    public void DaoTest4_FileInfoTest() {
        int algorithmId = getAlgorithmId();

        FileInfo newFileInfo = FileInfo.builder().id(-1)
                .algorithmId(algorithmId).fileName("test")
                .language("JAVA").build();

        int newId = fileInfoDao.add(newFileInfo);
        Assert.assertTrue(newId >= 0);

        List<FileInfo> fileInfos = fileInfoDao.getByAlgorithmId(algorithmId);
        Assert.assertNotEquals(fileInfos.size(), 0);
    }

}
