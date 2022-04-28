package learn.json.handler;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

public class DataExtractor {

	
	public static void main(String[] args) throws IOException {
		ObjectMapper om = new ObjectMapper();
		om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

		File file = new File("C:/dev/kakao/email/extracted_qa3");
		
		String fileName = "C:/dev/kakao/email/extracted_qa3";
		List<String> lines = Files.readAllLines(Paths.get(fileName), Charset.forName("euc_kr"));
		lines.forEach(data -> {
			
			try {
				JsonNode jsonNode = om.readTree(data);
				String result = jsonNode.get("nginx").get("access").get("request_body").textValue().replaceAll("\\\\", "");
				JsonNode common_node = om.readTree(result).get("common_header");
				JsonNode bodyNode = om.readTree(result).get("data");
				Map<String, String> headerMap = om.readValue(common_node.toString(), Map.class);
				Map<String, String> bodyMap = om.readValue(bodyNode.toString(), Map.class);

				headerMap.putAll(bodyMap);


				MailResult finalData = om.convertValue(headerMap, MailResult.class);
				
				System.out.println(finalData.toString());
				
				
			} catch (JsonMappingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		});


	}
	
	
	@Data
	public static class MailResult{
		String guid;
		String sd_email_adr;
		String rv_email_adr;
		String email_snd_dttm;
		String rslt_tcd;
		String msg_nm;
		String rv_dttm;
		
	}
}
