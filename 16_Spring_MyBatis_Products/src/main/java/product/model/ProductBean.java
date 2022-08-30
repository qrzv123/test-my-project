package product.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

public class ProductBean {
	private int num;
	
	//@Range(min=3,max=10, message="��ǰ�̸��� �ּ� 3�ڸ� �ִ� 10�ڸ� �Դϴ�.") Range�� ���� ������ 3~10�̴� ���⼭ ���X
	//@Length(min = 3, max = 10, message = "��ǰ �̸��� �ּ� 3�ڸ� �ִ� 10�ڸ� �Դϴ�.") ���⼭ ��� ok
	@Size(min = 3, max = 10, message = "��ǰ �̸��� �ּ� 3�ڸ� �ִ� 10�ڸ� �Դϴ�.")
	private String name;
	private String company;
	
	//type="file"�� ��ȿ�� �˻�� NotNull���� 2���� ����
	//@NotEmpty(message= "ȭ�� ���� ����1") //��ȿ�� �ɸ���ok
	//@NotNull(message= "ȭ�� ���� ����2") //@NotNull�� ��ȿ���� �Ȱɸ���  
	@NotBlank(message= "ȭ�� ���� ����3") //��ȿ�� �ɸ���ok
	private String image; // a.jpg
	private int stock;

	@Range(min = 3000, message="������ �ּ� 3000�� �̻��Դϴ�.")
	private int price;
	private String category;
	
	//@Range(min=5, max=10,message="��ǰ�� ���� ������ �ּ� 5�ڸ� �ִ� 10�ڸ� �Դϴ�.") Range�� ���� ������ 5~10�̴� ���⼭ ���X
	@Length(min = 5,max = 10,message = "��ǰ�� ���� ������ �ּ� 5�ڸ� �ִ� 10�ڸ� �Դϴ�.")
	private String contents;
	
	private int point;
	private String inputdate;
	
	
	//db���� ���� ���� ������
	private MultipartFile upload; // a.jpg X MultipartFileŸ���� upload������ ���
	
	public MultipartFile getUpload() {
		return upload;
	}
	
	public void setUpload(MultipartFile upload) { // upload=a.jpg X upload�� a.jpg���ϸ��� �ƴ� ���ϸ� �����ϴ� ��ü�ּҰ�����ִ�
		//System.out.println("setUpload(MultipartFile upload)");
		this.upload = upload;
		System.out.println("setUpload upload:" + upload);			//���� ���ϸ��� ����ִ� ��ü�� upload�� ����
		System.out.println("upload.getName():"+upload.getName());	//�� �޼��� ���� upload�� ���Եȴ�
		//upload.getName()�� MultipartFileŬ������ �޼��� getName()�̴�
		System.out.println("upload.getOriginalFilename():"+upload.getOriginalFilename());	//getOriginalFilename�޼��尡 ���� ���ϸ��� �����´�
		/*
		05.gif�� insertform���� cotroller���� ���Խ� setUpload�������� �ֿܼ��� 3���� �ϴܰ����� ���
		setUpload upload:org.springframework.web.multipart.commons.CommonsMultipartFile@3bef9f05
		upload.getName():upload
		upload.getOriginalFilename():05.gif
		*/
		this.image = upload.getOriginalFilename();	//image�� ���� ���ϸ��� �־���
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
