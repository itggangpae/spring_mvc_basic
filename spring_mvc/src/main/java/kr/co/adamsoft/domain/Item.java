package kr.co.adamsoft.domain;

import lombok.Data;

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
