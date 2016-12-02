package com.ion;

import com.ion.model.FileDTO;
import com.ion.repository.FileRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.FileCopyUtils;

import java.sql.Blob;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IonTestApplicationTests {

    @Autowired
    FileRepo fileRepo;

    @Test
    public void contextLoads() throws Exception {
        Resource file = new ClassPathResource("test.txt");
        FileDTO fileDTO = new FileDTO();
        byte[] bytes = FileCopyUtils.copyToByteArray(file.getInputStream());
        fileDTO.setFilecontent(bytes);
        fileDTO.setFilename(file.getFilename());
        fileRepo.save(fileDTO);

        FileDTO fileDTOfromDB = fileRepo.findOne(fileDTO.getId());

        Blob blob = fileDTOfromDB.getFilecontent();
        byte[] bytesFromDB = blob.getBytes(1, (int) blob.length());
           //автоматическое закрытие рессурса
        //сохранение в файл
      /*  try(BufferedOutputStream stream =
                    new BufferedOutputStream(new FileOutputStream(new File(file.getFilename())))){
            stream.write(bytes);
        }*/
        //для сравнения массивов нужно использовать Array.equals
        assertTrue(Arrays.equals(bytes, bytesFromDB));
    }




}
