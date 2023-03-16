package kh.spring.s02.common.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.mapping.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component("fileUtil") // Bean과 같다.
@PropertySource("classpath:properties/khs2.properties")
//@Service
public class FileUtil {
	
	@Value("${local.repository}")
	private String UPLOAD_FOLDER; // final static 빼준다.
	
//	@Autowired
//	private Environment env;

//	@Bean
	public List<Map<String, String>> saveFileList(MultipartHttpServletRequest multiReq, HttpServletRequest request, String addedPath) throws Exception{
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		
		Iterator<String> iterator = multiReq.getFileNames();  // Name <input name="n1" type="file" >
		while(iterator.hasNext()) {
			String name = iterator.next();  // "n1", "n2" 파일형태로 꺼낼수는 없다.
			MultipartFile multiFile = multiReq.getFile(name); // 파일을 꺼내서 넣어준다.
			
			Map<String, String> map = new HashMap<String, String>();
			map = saveFile(multiFile, request, addedPath);
			result.add(map);
		}
		
		return result;
	}
	
	/**
	 * @param multi 저장할 파일이 들어있음.
	 * @param request
	 * @return : map - "original":original file path, "rename":saved file path 저장된 파일의 경로를 리턴해준다는 뜻.
	 */
	public Map<String, String> saveFile(MultipartFile multi, HttpServletRequest request, String addedPath) throws Exception{
		Map<String, String> result = null;
		String renameFilePath = null;
		String renameByTime = null;
		if(multi != null && !multi.equals("")) { // multi: required=false이기때문에 null이 들어올수도있다.
			result = new HashMap<String, String>();
			
			String orginalFileName = multi.getOriginalFilename();
			
			// file을 server에 특정 위치(저장할 폴더)에 저장
			String webServerRoot = request.getSession().getServletContext().getRealPath("");
			String savePath = webServerRoot + UPLOAD_FOLDER;
			if(addedPath != null) {
				savePath += addedPath;
			}
			// 저장할 폴더가 안만들어져 있다면 만들어줘야함.
			File folder = new File(savePath); // 파일형태로 만들어주기
			if(!folder.exists()) { 	// 존재하지 않는다면
				folder.mkdirs(); // 전체적으로 없는 폴더는 싹다 만들어줌.
			} 
			// 파일을 savePath 위치에 저장
			// 시간을 활용한 rename
			renameByTime = System.currentTimeMillis() + "_"+orginalFileName;
			// UUID
			//String renameByUUID = UUID.randomUUID().toString() + "_"+orginalFileName;
			
			renameFilePath = savePath + "\\" + renameByTime;
			multi.transferTo(new File(savePath + "\\" + renameByTime));
			
			result.put("original", orginalFileName);
			result.put("rename", renameByTime);
		}
		return result;
	}
	
}
