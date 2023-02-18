package pl.server.api;

import com.fasterxml.jackson.databind.JsonNode;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.server.model.Product;
import pl.server.model.User;
import pl.server.repository.UserRepository;
import pl.server.service.CategoryService;
import pl.server.service.LocalStorageService;
import pl.server.service.ProductService;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController()
@RequestMapping("/products")
@RequiredArgsConstructor()
public class ProductController {

    private final ProductService productService;
    private final LocalStorageService localStorageService;

    private final UserRepository Userservice;

    @PostMapping(path = "", consumes = "multipart/form-data")
    public String saveProduct(@RequestParam("user") String user,@RequestParam("product") String data, @RequestPart("file") MultipartFile file) throws IOException {

        Product product = new ObjectMapper().readValue(data, Product.class);
        //User uservendor = new ObjectMapper().readValue(user, User.class);
        //product.setUservendor(uservendor);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(user);

        Long id = jsonNode.get("id").asLong();

        User vendor=Userservice.findUserById(id);


        //vendor.getProducts().add(product);

        //Userservice.save(vendor);

        System.out.println("vendor list"+vendor.getProducts());


        System.out.println("Product"+product);


        productService.save(product);


        return this.localStorageService.uploadFile(file,product.getName());
    }

    @GetMapping(path = "/categories/{category}")
    public List<Product> getAllProductsByCategoryName(@PathVariable(value="category") String category){
        return productService.findAllByCategoryName(category);
    }

    @GetMapping(path = "/{id}")
    public Product getProductById(@RequestParam(value="id") Long id){
        System.out.println(id);

        return productService.findProductById(id);
    }

    @GetMapping(path = "", params = "name")
    public Boolean isProductExists(@RequestParam(value="name") String name){
        return productService.existsByName(name);
    }

    @GetMapping(path = "")
    public List<Product> getAllProducts(){
        return productService.findAll();
    }
    @PutMapping(path = "/{id}", consumes = "multipart/form-data")
    public void updateProduct(@RequestParam("product") String data, @RequestPart("file") MultipartFile file) throws IOException {
        Product product  = new ObjectMapper().readValue(data, Product.class);

        if(file != null){
            localStorageService.uploadFile(file,product.getName());
        }
        productService.save(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable("id") Long id){
        Product product = productService.findProductById(id);
        localStorageService.deleteFileFromLocalStorage(product.getFileName());
        productService.delete(product);
    }
}
