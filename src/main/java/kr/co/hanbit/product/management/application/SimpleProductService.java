package kr.co.hanbit.product.management.application;

import kr.co.hanbit.product.management.domain.Product;
import kr.co.hanbit.product.management.domain.ProductDto;
import kr.co.hanbit.product.management.domain.ProductRepository;
import kr.co.hanbit.product.management.infrastructure.DatabaseProductRepository;
import kr.co.hanbit.product.management.infrastructure.ListProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleProductService {
    //private ListProductRepository listProductRepository;
    //private DatabaseProductRepository databaseProductRepository;

    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    private ValidationService validationService;

    @Autowired
    public SimpleProductService(ProductRepository productRepository
            ,ModelMapper modelMapper
            ,ValidationService validationService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        //유효성 검사
        this.validationService = validationService;
    }

    public ProductDto add(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        validationService.checkValid(product);
        Product savedProduct = productRepository.add(product);

        return modelMapper.map(savedProduct, ProductDto.class);
    }

    public ProductDto findById(Long id) {
        Product product = productRepository.findById(id);
        return modelMapper.map(product, ProductDto.class);
    }

    public List<ProductDto> findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    public List<ProductDto> findByNameContaining(String name) {
        List<Product> products = productRepository.findByNameContaining(name);
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .toList();
        return productDtos;
    }

    public ProductDto update(ProductDto productDto) {
        Product product = modelMapper.map(productDto, Product.class);
        Product updateProduct = productRepository.update(product);
        ProductDto updateProductDto = modelMapper.map(updateProduct, ProductDto.class);
        return updateProductDto;
    }

    public void delete(Long id) {
        productRepository.delete(id);
    }
}
