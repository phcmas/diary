package diary.service;

import diary.dto.algorithm.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    //diary_evn 파일로 fileRoot 환경 변수를 설정해야함
    public static String fileRoot = System.getenv("fileRoot");
    public FileInfo get(int id);
    public FileInfo getByAlgorithmId(int algorithmId);
    public int add(int algorithmId, MultipartFile file);
    public int delete(int id);
    public int update (int id, int algorithmId, MultipartFile file);
    public int addFileInfo(int algorithmId, MultipartFile file);
    public int deleteFileInfo(int id);
    public int updateFileInfo(int id, int algorithmId, MultipartFile file);
    public void storeFile(MultipartFile file);
    public void deleteFile(String saveFileName);
    public boolean findFile(String saveFileName);
}
