package jinbok.culture.board.handler;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageHandler {
    public String save(MultipartFile image) throws IOException {
        String imageName = image.getOriginalFilename();
        String fullPathName = "C:/Users/Jinbok/Desktop/"+imageName; // 후에 이름을 좀 더 복잡하게 번경
        image.transferTo(new File(fullPathName));
        return fullPathName;
    }

    public String getOriginalFilename(MultipartFile image) {
        return image.getOriginalFilename();
    }
}


