package com.aws.handson.iaac.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.aws.handson.iaac.service.ProductServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.aws.handson.iaac.dto.ProductData;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping("/Product")
public class ProductController {
    @Value("${aws.s3.bucket.upload}")
	private String s3BucketUpload;
	@Autowired
    private final ProductServices productService;
    private final AmazonS3Client s3Client;

    public ProductController(ProductServices productService, AmazonS3Client s3Client) {
        this.productService = productService;
        this.s3Client = s3Client;
    }

    @GetMapping("/allProduct")
    public List<ProductData> findAll() {
        return productService.findAll();
    }
    @GetMapping("/ProductFindId/{id}")
    public ProductData findById(@PathVariable  Long id) {
        return productService.findById(id);
    }
    @PostMapping("/ProductCreate")
    public ProductData create(@RequestBody ProductData productData) {
        return productService.create(productData);
    }
    @DeleteMapping("/PoductDeleteId/{id}")
    public boolean delete(@PathVariable Long id) {
        return productService.delete(id);
    }

  //added
    @PutMapping("/productUpdateById/{id}")
    public ProductData updateProductById(@RequestBody ProductData productData, @PathVariable("id") Long productId) {
        return productService.update(productId, productData);
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        String key = file.getOriginalFilename();
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(s3BucketUpload,key,convertMultiPartToFile(file));
            var s3Resp = s3Client.putObject(putObjectRequest);
            return key;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error during upload. " + e.getMessage();
        }
    }
    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
    @GetMapping("/image/{key}")
    public ResponseEntity<Resource> downloadImage(@PathVariable String key) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(s3BucketUpload,key);

        var objectBytes = s3Client.getObject(getObjectRequest);
        ByteArrayResource resource = new ByteArrayResource(objectBytes.getObjectContent().readAllBytes());

        return ResponseEntity
                .ok()
                .contentLength(objectBytes.getObjectContent().readAllBytes().length)
                .header("Content-type", "image/jpeg")
                .body(resource);
    }
}

