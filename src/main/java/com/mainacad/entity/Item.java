package com.mainacad.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "article", nullable = false)
	private String article;
	
	@Column(name = "name", nullable = false)
	private String name;
	
	@Column(name = "price")
	private Integer price;
	
	@Column(name = "init_price")
	private Integer initPrice;

}
