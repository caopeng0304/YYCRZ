package com.sinosoft.ie.booster.common.minio.http;

import com.sinosoft.ie.booster.common.minio.service.MinioTemplate;
import com.sinosoft.ie.booster.common.minio.model.MinioItemVO;
import com.sinosoft.ie.booster.common.minio.model.MinioObjectVO;
import io.minio.messages.Bucket;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * minio 对外提供服务端点
 *
 * @author lengleng
 * <p>
 * minio.endpoint.enable
 */
@ConditionalOnProperty(name = "minio.endpoint.enable", havingValue = "true")
@RestController
@AllArgsConstructor
@RequestMapping("${minio.endpoint.name:/minio}")
public class MinioEndpoint {
	private final MinioTemplate template;

	/**
	 * Bucket Endpoints
	 */
	@SneakyThrows
	@PostMapping("/bucket/{bucketName}")
	public Bucket createBucker(@PathVariable String bucketName) {

		template.createBucket(bucketName);
		return template.getBucket(bucketName).get();

	}

	@SneakyThrows
	@GetMapping("/bucket")
	public List<Bucket> getBuckets() {
		return template.getAllBuckets();
	}

	@SneakyThrows
	@GetMapping("/bucket/{bucketName}")
	public Bucket getBucket(@PathVariable String bucketName) {
		return template.getBucket(bucketName).orElseThrow(() -> new IllegalArgumentException("Bucket Name not found!"));
	}

	@SneakyThrows
	@DeleteMapping("/bucket/{bucketName}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void deleteBucket(@PathVariable String bucketName) {
		template.removeBucket(bucketName);
	}

	/**
	 * Object Endpoints
	 */
	@SneakyThrows
	@PostMapping("/object/{bucketName}")
	public MinioObjectVO createObject(@RequestBody MultipartFile object, @PathVariable String bucketName) {
		String name = object.getOriginalFilename();
		template.putObject(bucketName, name, object.getInputStream(), object.getSize(), object.getContentType());
		return new MinioObjectVO(template.getObjectInfo(bucketName, name));

	}

	@SneakyThrows
	@PostMapping("/object/{bucketName}/{objectName}")
	public MinioObjectVO createObject(@RequestBody MultipartFile object, @PathVariable String bucketName, @PathVariable String objectName) {
		template.putObject(bucketName, objectName, object.getInputStream(), object.getSize(), object.getContentType());
		return new MinioObjectVO(template.getObjectInfo(bucketName, objectName));

	}

	@SneakyThrows
	@GetMapping("/object/{bucketName}/{objectName}")
	public List<MinioItemVO> filterObject(@PathVariable String bucketName, @PathVariable String objectName) {

		return template.getAllObjectsByPrefix(bucketName, objectName, true);

	}

	@SneakyThrows
	@GetMapping("/object/{bucketName}/{objectName}/{expires}")
	public Map<String, Object> getObject(@PathVariable String bucketName, @PathVariable String objectName, @PathVariable Integer expires) {
		Map<String, Object> responseBody = new HashMap<>(8);
		// Put Object info
		responseBody.put("bucket", bucketName);
		responseBody.put("object", objectName);
		responseBody.put("url", template.getObjectURL(bucketName, objectName, expires));
		responseBody.put("expires", expires);
		return responseBody;
	}

	@SneakyThrows
	@ResponseStatus(HttpStatus.ACCEPTED)
	@DeleteMapping("/object/{bucketName}/{objectName}/")
	public void deleteObject(@PathVariable String bucketName, @PathVariable String objectName) {

		template.removeObject(bucketName, objectName);
	}

}
