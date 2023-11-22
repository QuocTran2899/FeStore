package com.cybersoft.festore.api;


        import com.cybersoft.festore.payload.BaseResponse;
        import com.cybersoft.festore.payload.response.ProductResponse;
        import com.cybersoft.festore.service.imp.ProductServiceImp;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.core.io.Resource;
        import org.springframework.http.*;
        import org.springframework.web.bind.annotation.*;
        import org.springframework.web.multipart.MultipartFile;

        import javax.servlet.http.HttpServletRequest;
        import java.io.IOException;
        import java.nio.file.Files;
        import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/product")
public class ApiProductController {

    @Autowired
    private ProductServiceImp productServiceImp;

    @GetMapping("")
    public ResponseEntity<?> getAllProduct(){
        List<ProductResponse> listResponse = productServiceImp.getAllProduct();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(listResponse);
        return new ResponseEntity<>(baseResponse, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<?> addProduct(@RequestParam String name,
                                        @RequestParam MultipartFile file,
                                        @RequestParam double price,
                                        @RequestParam String description,
                                        @RequestParam int idColor,
                                        @RequestParam int idSize,
                                        @RequestParam int idCategory) throws IOException {

        boolean isSuccess = productServiceImp.addProduct(name,file,price,description,idColor,idSize,idCategory);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Thêm thành công" : "Thêm thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @GetMapping("/{tenFile}")
    public ResponseEntity<?> downloadFileProduct(@PathVariable String tenFile) {
        try {
            Resource resource = productServiceImp.downloadProductFile(tenFile);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Set the appropriate content type

            byte[] imageBytes = Files.readAllBytes(resource.getFile().toPath());

            return ResponseEntity.ok().headers(headers).body(imageBytes);
        } catch (IOException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/category/{id}")
    public ResponseEntity<?> getCategoryById(HttpServletRequest request, @PathVariable int id){
        String host = request.getHeader("host");
        List<ProductResponse> listResponse = productServiceImp.getProductByCategoryId(host , id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage("");
        baseResponse.setData(listResponse);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,
                                           @RequestParam String name,
                                           @RequestParam MultipartFile file,
                                           @RequestParam double price,
                                           @RequestParam String description,
                                           @RequestParam int idColor,
                                           @RequestParam int idSize,
                                           @RequestParam int idCategory) throws IOException {
            boolean isSuccess = productServiceImp.updateProduct(id, name, file, price, description, idColor, idSize, idCategory);
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setStatusCode(200);
            baseResponse.setMessage(isSuccess ? "Cập nhật thành công" : "Cập nhật thất bại");
            baseResponse.setData(isSuccess);
            return new ResponseEntity<>(baseResponse,HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        boolean isSuccess = productServiceImp.deteleProduct(id);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setStatusCode(200);
        baseResponse.setMessage(isSuccess ? "Xoá thành công" : "Xóa thất bại");
        baseResponse.setData(isSuccess);
        return new ResponseEntity<>(baseResponse,HttpStatus.OK);
    }
}
