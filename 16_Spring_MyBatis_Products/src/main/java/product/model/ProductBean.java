package product.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

public class ProductBean {
	private int num;
	
	//@Range(min=3,max=10, message="상품이름은 최소 3자리 최대 10자리 입니다.") Range는 값의 범위로 3~10이다 여기서 사용X
	//@Length(min = 3, max = 10, message = "상품 이름은 최소 3자리 최대 10자리 입니다.") 여기서 사용 ok
	@Size(min = 3, max = 10, message = "상품 이름은 최소 3자리 최대 10자리 입니다.")
	private String name;
	private String company;
	
	//type="file"을 유효성 검사시 NotNull제외 2가지 가능
	//@NotEmpty(message= "화일 선택 안함1") //유효성 걸린다ok
	//@NotNull(message= "화일 선택 안함2") //@NotNull은 유효성에 안걸린다  
	@NotBlank(message= "화일 선택 안함3") //유효성 걸린다ok
	private String image; // a.jpg
	private int stock;

	@Range(min = 3000, message="가격은 최소 3000원 이상입니다.")
	private int price;
	private String category;
	
	//@Range(min=5, max=10,message="상품에 대한 설명은 최소 5자리 최대 10자리 입니다.") Range는 값의 범위로 5~10이다 여기서 사용X
	@Length(min = 5,max = 10,message = "상품에 대한 설명은 최소 5자리 최대 10자리 입니다.")
	private String contents;
	
	private int point;
	private String inputdate;
	
	
	//db에는 없는 변수 설정함
	private MultipartFile upload; // a.jpg X MultipartFile타입이 upload변수에 담김
	
	public MultipartFile getUpload() {
		return upload;
	}
	
	public void setUpload(MultipartFile upload) { // upload=a.jpg X upload에 a.jpg파일명이 아닌 파일명 관리하는 객체주소가들어있다
		//System.out.println("setUpload(MultipartFile upload)");
		this.upload = upload;
		System.out.println("setUpload upload:" + upload);			//실제 파일명이 들어있는 객체를 upload가 관리
		System.out.println("upload.getName():"+upload.getName());	//이 메서드 사용시 upload가 오게된다
		//upload.getName()는 MultipartFile클래스의 메서드 getName()이다
		System.out.println("upload.getOriginalFilename():"+upload.getOriginalFilename());	//getOriginalFilename메서드가 실제 파일명을 가져온다
		/*
		05.gif를 insertform에서 cotroller에서 삽입시 setUpload지나가며 콘솔에위 3개가 하단과같이 출력
		setUpload upload:org.springframework.web.multipart.commons.CommonsMultipartFile@3bef9f05
		upload.getName():upload
		upload.getOriginalFilename():05.gif
		*/
		this.image = upload.getOriginalFilename();	//image에 실제 파일명을 넣었다
	}
	
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		System.out.println("setName(String name)");
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		System.out.println("setCompany(String company)");
		this.company = company;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getInputdate() {
		return inputdate;
	}
	public void setInputdate(String inputdate) {
		this.inputdate = inputdate;
	}

}
