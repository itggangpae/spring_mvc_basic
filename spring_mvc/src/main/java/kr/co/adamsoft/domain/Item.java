package kr.co.adamsoft.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Data;

//DTO 클래스를 하나의 XML 태그로 변환하기 위한 작업
//propOrder 에 포함된 항목만 순서대로 출력됨
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="", propOrder= {
	"itemid", "itemname", "price", "description", "pictureurl"})

//getter&setter, toString, 매개변수가 없는 생성자를 만들어주는 annotation
//제대로 동작을 할려면 현재 IDE 에 lombok 이 설치되어야 합니다.
//설치가 안되어 있으면 그냥 getter&setter, toString을 만들어주면 됩니다.
@Data
public class Item {
	private int itemid;
	private String itemname;
	private int price;
	private String description;
	private String pictureurl;

}
