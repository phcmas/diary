package diary.service;

import diary.dto.algorithm.FileInfo;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    public static String fileRoot = "/home/seungkyun/Web/diary/files/";
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
