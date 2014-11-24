package helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMapHelper {	
	
	public static Map<String, String> generateJSONMap(InputStream ins)
			throws IOException {

		BufferedReader br0 = new BufferedReader(new InputStreamReader(ins));
        
		String json = "";
        if(br0 != null){
            json = br0.readLine();
        }
		ObjectMapper reqMapper = new ObjectMapper();
		TypeReference<HashMap<String, String>> typeRef 
        	= new TypeReference<HashMap<String, String>>() {};
		return reqMapper.readValue(json, typeRef);
	}
}
